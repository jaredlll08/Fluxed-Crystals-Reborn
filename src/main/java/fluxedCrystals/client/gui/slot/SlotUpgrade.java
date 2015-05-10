package fluxedCrystals.client.gui.slot;

import fluxedCrystals.init.FCItems;
import fluxedCrystals.util.IUpgrade;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUpgrade extends Slot
{

	public SlotUpgrade(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	public int getSlotStackLimit() {
		return 1;
	}

	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof IUpgrade && !stack.isItemEqual(new ItemStack(FCItems.upgradeRangeAdvanced)) && !stack.isItemEqual(new ItemStack(FCItems.upgradeRangeBasic)) && !stack.isItemEqual(new ItemStack(FCItems.upgradeRangeGreater));
	}
}
