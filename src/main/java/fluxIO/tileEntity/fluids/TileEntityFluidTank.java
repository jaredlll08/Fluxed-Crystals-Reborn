package fluxIO.tileEntity.fluids;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxIO.network.MessageFluid;
import fluxIO.network.PacketHandler;

public class TileEntityFluidTank extends TileEntity implements IFluidHandler {

	public FluidTank tank;
	public int prevAmount;
	public FluidStack prevFluid;
	public int initPer;

	public TileEntityFluidTank() {
		tank = new FluidTank(32000);
		prevAmount = 0;
		initPer = 40;
	}

	public void onBlockBreak() {
		if (tank.getFluidAmount() > 0) {
			FluidEvent.fireEvent(new FluidEvent.FluidSpilledEvent(tank.getFluid(), worldObj, xCoord, yCoord, zCoord));
		}
	}

	@Override
	public double getMaxRenderDistanceSquared() {
		return 8192.0D;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (tank.getFluidAmount() > 0 && !(tank.getFluidAmount() == prevAmount)) {
				prevAmount = tank.getFluidAmount();
				markDirty();
			}
			if (initPer >= 0 && tank.getFluid() != null) {
				initPer--;
				if (initPer <= 0) {
					markDirty();
				}
			}

		}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		PacketHandler.INSTANCE.sendToAllAround(new MessageFluid(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double) this.xCoord, (double) this.yCoord, (double) this.zCoord, 128d));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagCompound tankTag = new NBTTagCompound();
		tank.writeToNBT(tankTag);
		nbt.setTag("tank", tankTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagCompound tankTag = nbt.getCompoundTag("tank");
		this.tank.readFromNBT(tankTag);
	}

	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		FluidStack resourceCopy = resource.copy();
		int totalUsed = 0;

		FluidStack liquid = tank.getFluid();
		if (liquid != null && liquid.amount > 0 && !liquid.isFluidEqual(resourceCopy)) {
			return 0;
		}


		while (resourceCopy.amount > 0) {
			int used = tank.fill(resourceCopy, doFill);
			resourceCopy.amount -= used;
			if (used > 0) {
				markDirty();
			}

			totalUsed += used;
			
		}

		return totalUsed;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxEmpty, boolean doDrain) {
		markDirty();
		FluidStack output = tank.drain(maxEmpty, doDrain);

		return output;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null) {
			return null;
		}
		if (!resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {
		FluidTank compositeTank = new FluidTank(tank.getCapacity());


		int capacity = tank.getCapacity();

		if (tank.getFluid() != null) {
			compositeTank.setFluid(tank.getFluid().copy());
		} else {
			return new FluidTankInfo[]{compositeTank.getInfo()};
		}

	

			FluidStack liquid = tank.getFluid();
			if (liquid == null || liquid.amount == 0) {
				
			} else {
				compositeTank.getFluid().amount += liquid.amount;
			}

			capacity += tank.getCapacity();

		compositeTank.setCapacity(capacity);
		return new FluidTankInfo[]{compositeTank.getInfo()};
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		Fluid tankFluid = getFluidType();
		return tankFluid == null || tankFluid == fluid;
	}
	public Fluid getFluidType() {
		return tank.getFluid() != null ? tank.getFluid().getFluid() : null;
	}
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		Fluid tankFluid = getFluidType();
		return tankFluid != null && tankFluid == fluid;
	}
}
