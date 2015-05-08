package fluxedCrystals.api.solarFlux;

import fluxedCrystals.network.message.MessageSolarFluxSync;
import fluxedCrystals.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public abstract class TileSolarFlux extends TileEntity {
	public int maxEnergy;
	public int energyStored;
	public int energyRate;
	public int range;

	public TileSolarFlux (int maxEnergy, int energyRate, int range) {
		this.maxEnergy = maxEnergy;
		this.energyRate = energyRate;
		this.energyStored = 0;
		this.range = range;
	}

	public void setEnergy(int energy) {
		this.energyStored = energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public int getEnergyStored() {
		return energyStored;
	}

	public int getEnergyRate() {
		return energyRate;
	}

	public int getRange() {
		return range;
	}

	public abstract void generateEnergy(World world, int x, int y, int z, boolean night, boolean canSeeSky);

	public abstract void sendEnergy(World world, int x, int y, int z, int range);

	public abstract boolean canRecieveEnergy();

	public int recieveEnergy(int energy, boolean simulate) {
		if (!simulate) {
			energyStored += energy;
			if (energyStored > maxEnergy)
				energyStored = maxEnergy;
			if (energyStored < 0) {
				energyStored = 0;
			}
			PacketHandler.INSTANCE.sendToDimension(new MessageSolarFluxSync(xCoord, yCoord, zCoord, getEnergyStored()), worldObj.provider.dimensionId);
		}
		return maxEnergy - (energy + energyStored);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		int oldEnergy = energyStored;
		generateEnergy(worldObj, xCoord, yCoord, zCoord, !worldObj.provider.isDaytime(), worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord));
		if (new Random().nextInt(energyRate) == 0)
			sendEnergy(worldObj, xCoord, yCoord, zCoord, range);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("solarFluxEnergy", energyStored);
		tag.setInteger("solarFluxRange", range);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		energyStored = tag.getInteger("solarFluxEnergy");
		range = tag.getInteger("solarFluxRange");
	}

}
