package fluxIO.tileEntity.misc;

import fluxIO.tileEntity.util.TileEntityFluidTank;


public class TileEntityTank extends TileEntityFluidTank{

	public TileEntityFluidTank master;
	public TileEntityTank() {
		super(16000);
	}
	
	public TileEntityFluidTank getMaster(){
		return master;
	}
	
	public void setMaster(TileEntityFluidTank master){
		this.master = master;
	}
	

}
