package WayofTime.alchemicalWizardry.api.event;

import WayofTime.alchemicalWizardry.api.rituals.IMasterRitualStone;
import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@Cancelable
public class RitualActivatedEvent extends RitualEvent
{
	public final EntityPlayer player;
	public final ItemStack crystalStack;
	public int crystalTier;
	
	public RitualActivatedEvent(IMasterRitualStone mrs, String ownerKey, String ritualKey, EntityPlayer player, ItemStack activationCrystal, int crystalTier) 
	{
		super(mrs, ownerKey, ritualKey);
		
		this.player = player;
		this.crystalStack = activationCrystal;
		this.crystalTier = crystalTier;
	}
}
