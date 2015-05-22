package fluxedCrystals.handler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fluxedCrystals.registry.MutationRegistry;
import fluxedCrystals.registry.SeedRegistry;

public class RegistrySerializationHandler
{

	@SubscribeEvent
	public void onServerTick (TickEvent.ServerTickEvent event) {

		if (event.phase == TickEvent.Phase.END) {

			if (FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getWorldTime() % 600 == 0) {

				SeedRegistry.getInstance().Save();
				MutationRegistry.getInstance().Save();

			}

		}

	}

}
