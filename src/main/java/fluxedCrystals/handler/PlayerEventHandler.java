package fluxedCrystals.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSyncSeeds;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumChatFormatting;

public class PlayerEventHandler {

	@SubscribeEvent
	public void chat(net.minecraftforge.event.entity.player.PlayerEvent.NameFormat e){
		if(e.displayname.equalsIgnoreCase("Jaredlll08")){
			e.displayname = EnumChatFormatting.BLUE + "Jared" + EnumChatFormatting.RESET;
		}
		else if (e.displayname.equalsIgnoreCase("namroc_smith")) {
			e.displayname = EnumChatFormatting.RED + "Namroc" + EnumChatFormatting.RESET;
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {

		if (event.player != null) {

			PacketHandler.INSTANCE.sendTo(new MessageSyncSeeds(SeedRegistry.getInstance().getSeedMap()), (EntityPlayerMP) event.player);

		}

	}

}
