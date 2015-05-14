package fluxedCrystals.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import fluxedCrystals.handler.PlayerEventHandler;
import fluxedCrystals.handler.SeedRegistrySerializationHandler;
import fluxedCrystals.util.Events;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy {


	public void preInit() {

	}

	public void initialize() {

		PlayerEventHandler playerEventHandler = new PlayerEventHandler();
		Events playerEventHandlerOther = new Events();

		FMLCommonHandler.instance().bus().register(new SeedRegistrySerializationHandler());

		MinecraftForge.EVENT_BUS.register(playerEventHandler);
		FMLCommonHandler.instance().bus().register(playerEventHandler);

		MinecraftForge.EVENT_BUS.register(playerEventHandlerOther);
		FMLCommonHandler.instance().bus().register(playerEventHandlerOther);

	}

	public void postInit() {

	}

}
