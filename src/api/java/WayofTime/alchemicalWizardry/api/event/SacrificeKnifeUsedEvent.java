package WayofTime.alchemicalWizardry.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class SacrificeKnifeUsedEvent extends Event
{
	public final EntityPlayer player;
	public boolean shouldDrainHealth;
	public boolean shouldFillAltar;
	public final int healthDrained;
	
	public SacrificeKnifeUsedEvent(EntityPlayer player, boolean shouldDrainHealth, boolean shouldFillAltar, int hp)
	{
		this.player = player;
		this.shouldDrainHealth = shouldDrainHealth;
		this.shouldFillAltar = shouldFillAltar;
		this.healthDrained = hp;
	}
}
