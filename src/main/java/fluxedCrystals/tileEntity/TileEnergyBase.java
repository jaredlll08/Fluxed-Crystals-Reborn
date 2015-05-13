package fluxedCrystals.tileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import fluxedCrystals.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.EnumSet;

public abstract class TileEnergyBase extends TileEntity implements IEnergyHandler
{
	public EnergyStorage storage;
	protected int capacity;

	public TileEnergyBase (int cap) {
		super();
		init(cap);
	}

	public double getEnergyColor() {
		double energy = storage.getEnergyStored();
		double maxEnergy = storage.getMaxEnergyStored();
		return energy / maxEnergy;
	}

	private void init(int cap) {
		storage = new EnergyStorage(cap);
	}

	public abstract EnumSet<ForgeDirection> getValidOutputs();

	public abstract EnumSet<ForgeDirection> getValidInputs();

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			pushEnergy();
		}
	}

	protected void pushEnergy() {
		for (ForgeDirection dir : getValidOutputs()) {
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (tile instanceof IEnergyHandler) {
				IEnergyHandler ieh = (IEnergyHandler) tile;
				storage.extractEnergy(ieh.receiveEnergy(dir, storage.extractEnergy(getOutputSpeed(), true), false), false);
			}
		}
	}

	/* I/O Handling */

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		if (getValidOutputs().contains(from)) {
			int ret = storage.extractEnergy(maxExtract, true);
			if (!simulate) {
				storage.extractEnergy(ret, false);
			}
			return ret;
		}
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if (getValidInputs().contains(from)) {
			int ret = storage.receiveEnergy(maxReceive, true);
			if (!simulate) {
				storage.receiveEnergy(ret, false);
			}
			return ret;
		}
		return 0;
	}

	@Override
	public final boolean canConnectEnergy(ForgeDirection from) {
		return getValidInputs().contains(from) || getValidOutputs().contains(from);
	}

	/* IEnergyHandler basic impl */

	@Override
	public final int getEnergyStored(ForgeDirection from) {
		return getEnergyStored();
	}

	@Override
	public final int getMaxEnergyStored(ForgeDirection from) {
		return getMaxStorage();
	}

	/* IWailaAdditionalInfo */

	/* getters & setters */

	public int getEnergyStored() {
		return storage.getEnergyStored();
	}

	public void setEnergyStored(int energy) {
		storage.setEnergyStored(energy);
	}

	public int getMaxStorage() {
		return storage.getMaxEnergyStored();
	}

	public void setMaxStorage(int storage) {
		this.storage.setCapacity(storage);
	}

	public int getOutputSpeed() {
		return storage.getMaxExtract();
	}

	public int getMaxOutputSpeed() {
		return getOutputSpeed();
	}

	public void setOutputSpeed(int outputSpeed) {
		this.storage.setMaxExtract(outputSpeed);
	}

	public int getInputSpeed() {
		return storage.getMaxReceive();
	}

	public void setInputSpeed(int inputSpeed) {
		this.storage.setMaxReceive(inputSpeed);
	}

	/* Read/Write NBT */

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);

	}
}