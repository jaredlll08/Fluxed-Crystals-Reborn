package fluxIO.api.generators;

import java.util.EnumSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import fluxIO.tileEntity.TileEnergyBase;

public class FluidGeneratorBase extends TileEnergyBase implements IFluidTank {

	protected FluidStack fluid;
	protected int capacity;
	protected TileEntity tile;

	public FluidGeneratorBase(int capacity, int energyStorage) {
		this(null, capacity, energyStorage);
	}

	public FluidGeneratorBase(FluidStack stack, int capacity, int energyStorage) {
		super(energyStorage);
		this.fluid = stack;
		this.capacity = capacity;
	}

	public FluidGeneratorBase(Fluid fluid, int amount, int capacity, int energyStorage) {
		this(new FluidStack(fluid, amount), capacity, energyStorage);
	}

	public FluidGeneratorBase readFluidFromNBT(NBTTagCompound nbt) {
		if (!nbt.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
			setFluid(fluid);
		} else {
			setFluid(null);
		}
		return this;
	}

	public NBTTagCompound writeFluidToNBT(NBTTagCompound nbt) {
		if (fluid != null) {
			fluid.writeToNBT(nbt);
		} else {
			nbt.setString("Empty", "");
		}
		return nbt;
	}

	public void setFluid(FluidStack fluid) {
		this.fluid = fluid;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		if (fluid == null) {
			return 0;
		}
		return fluid.amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		if (!doFill) {
			if (fluid == null) {
				return Math.min(capacity, resource.amount);
			}

			if (!fluid.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(capacity - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			fluid = new FluidStack(resource, Math.min(capacity, resource.amount));

			if (tile != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, this, fluid.amount));
			}
			return fluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}
		int filled = capacity - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = capacity;
		}

		if (tile != null) {
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, this, filled));
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}

		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				fluid = null;
			}

			if (tile != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, this, drained));
			}
		}
		return stack;
	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}

}
