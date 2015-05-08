package fluxedCrystals.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import fluxedCrystals.handler.PlayerEventHandler;
import fluxedCrystals.handler.SeedRegistrySerializationHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy
{

	public void preInit()
	{

	}

	public void initialize()
	{

		PlayerEventHandler playerEventHandler = new PlayerEventHandler();

		FMLCommonHandler.instance().bus().register(new SeedRegistrySerializationHandler());

		MinecraftForge.EVENT_BUS.register(playerEventHandler);
		FMLCommonHandler.instance().bus().register(playerEventHandler);

	}

	public void postInit()
	{

	}

}
