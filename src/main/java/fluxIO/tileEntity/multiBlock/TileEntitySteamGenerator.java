package fluxIO.tileEntity.multiBlock;

import java.util.EnumSet;

import net.minecraftforge.common.util.ForgeDirection;
import fluxIO.tileEntity.TileEnergyBase;


public class TileEntitySteamGenerator extends TileEnergyBase{

	public TileEntitySteamGeneratorConnected connected;
	public TileEntitySteamGenerator() {
		super(20000);
	}
	
	public void setConnected(TileEntitySteamGeneratorConnected connected){
		this.connected = connected;
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
