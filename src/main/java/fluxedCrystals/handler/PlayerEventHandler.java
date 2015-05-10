package fluxedCrystals.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSyncSeeds;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerEventHandler
{

	@SubscribeEvent
	public void onPlayerLoggedIn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
	{

		if (event.player != null)
		{

			PacketHandler.INSTANCE.sendTo(new MessageSyncSeeds(SeedRegistry.getInstance().getSeedMap()), (EntityPlayerMP) event.player);

		}

	}

}
