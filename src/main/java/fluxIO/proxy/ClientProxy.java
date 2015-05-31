package fluxIO.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import fluxIO.client.gui.GUIHandler;
import fluxIO.client.render.RenderLavaGenerator;
import fluxIO.client.render.RenderTank;
import fluxIO.tileEntity.TileEntityLavaGenerator;
import fluxIO.tileEntity.fluids.TileEntityFluidTank;
import fluxIO.tileEntity.fluids.TileEntityTank;

public class ClientProxy extends CommonProxy {
	public void initGuis() {
		new GUIHandler();
	}
	
	@Override
	public void initRenderers() {
	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLavaGenerator.class, new RenderLavaGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTank.class, new RenderTank());
		
	}
}
