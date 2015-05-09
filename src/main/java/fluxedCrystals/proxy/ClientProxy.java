package fluxedCrystals.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.gui.RenderCrystal;

public class ClientProxy extends CommonProxy
{

	@Override
	public ClientProxy getClientProxy()
	{

		return this;

	}
	
	@Override
	public void registerRenderers() {
		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderCrystal renderCrystal = new RenderCrystal();
		RenderingRegistry.registerBlockHandler(renderCrystal);
		
		
	}

}
