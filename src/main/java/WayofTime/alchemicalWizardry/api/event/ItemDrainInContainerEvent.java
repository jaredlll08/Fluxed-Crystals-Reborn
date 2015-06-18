package WayofTime.alchemicalWizardry.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.item.ItemStack;

@Cancelable
public class ItemDrainInContainerEvent extends SoulNetworkEvent
{
	public ItemStack stack;
	public ItemDrainInContainerEvent(ItemStack stack, String ownerNetwork, int drainAmount) 
	{
		super(ownerNetwork, drainAmount);
		this.stack = stack;
	}
}
