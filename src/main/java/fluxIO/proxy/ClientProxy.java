package fluxIO.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import fluxIO.client.gui.GUIHandler;
import fluxIO.client.render.RenderLavaGenerator;
import fluxIO.tileEntity.TileEntityLavaGenerator;

public class ClientProxy extends CommonProxy {
	public void initGuis() {
		new GUIHandler();
	}
	
	@Override
	public void initRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLavaGenerator.class, new RenderLavaGenerator());
	}
}
