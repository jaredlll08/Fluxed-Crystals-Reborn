package fluxIO.tileEntity;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import fluxIO.api.Registry;
import fluxIO.api.generators.FluidGeneratorBase;

public class TileEntityLavaGenerator extends FluidGeneratorBase {
	private static int maxEnergy = 100000;
	private static int capacity = 8000;

	private int generationTimer = -1;
	private int generationTimerDefault = -1;

	public TileEntityLavaGenerator() {
		super(capacity, maxEnergy);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (generationTimerDefault < 0 && generationTimer < 0 && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
				if (getFluid() != null) {

					if (Registry.LavaGenerator.canUse(getFluid().getFluid())) {
						generationTimer = 300;
						generationTimerDefault = 300;
						drain(250, true);
					}

				}
			}
			if (generationTimerDefault >= 0 && getEnergyStored() < getMaxStorage()) {
				generationTimer--;
				generateEnergy(worldObj, xCoord, yCoord, zCoord, generationTimer);
			}
			if (generationTimer < 0 && generationTimerDefault >= 0) {
				generationTimer = -1;
				generationTimerDefault = -1;
			}
		}
	}

	public void generateEnergy(World world, int x, int y, int z, int timer) {
		if (!world.isRemote) {
			for (EntityPlayer player : (List<EntityPlayer>) world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - 5, y - 5, z - 5, x + 5, y + 5, z + 5))) {
				player.addChatComponentMessage(new ChatComponentText("Energy:" + getEnergyStored()));
				if (getFluid() != null)
					player.addChatComponentMessage(new ChatComponentText("Fluid:" + getFluid().getLocalizedName() + ":" + getFluidAmount()));
				player.addChatComponentMessage(new ChatComponentText("Timer:" + generationTimer));
				player.addChatComponentMessage(new ChatComponentText("Timer Default:" + generationTimerDefault));

			}

			if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
				this.storage.receiveEnergy(40, false);
			}
		}
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		System.out.println("Filled!");
		return super.fill(resource, doFill);

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeFluidToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readFluidFromNBT(nbt);
	}
}
