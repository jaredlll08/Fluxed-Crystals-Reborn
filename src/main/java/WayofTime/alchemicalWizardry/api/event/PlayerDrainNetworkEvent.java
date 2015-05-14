package WayofTime.alchemicalWizardry.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class PlayerDrainNetworkEvent extends SoulNetworkEvent {
	public final EntityPlayer player; //Player that activated the event

	public PlayerDrainNetworkEvent(EntityPlayer player, String ownerNetwork, int drainAmount) {
		super(ownerNetwork, drainAmount);
		this.player = player;
	}

	public EntityPlayer getPlayer() {
		return player;
	}
}
