package fluxIO.tileEntity.tank;

import fluxIO.tileEntity.util.TileEntityFluidTank;

public class TileEntityTankIO extends TileEntityFluidTank {

	public int capacity = 16000;
	public TileEntityTankIO() {
		super(16000);
		setCapacity(capacity);
	}

}
