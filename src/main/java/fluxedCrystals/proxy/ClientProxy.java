package fluxedCrystals.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.render.RenderCrystal;
import fluxedCrystals.client.render.SeedInfuserRenderer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{

	@Override
	public ClientProxy getClientProxy()
	{

		return this;

	}
	
	@Override
	public void registerRenderers()
	{

		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderCrystal renderCrystal = new RenderCrystal();
		RenderingRegistry.registerBlockHandler(renderCrystal);

		FluxedCrystals.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());

	}

	@Override
	public World getClientWorld()
	{

		return FMLClientHandler.instance().getClient().theWorld;

	}

	@Override
	public boolean isServer()
	{

		return false;

	}

	@Override
	public boolean isClient()
	{

		return true;

	}

}
