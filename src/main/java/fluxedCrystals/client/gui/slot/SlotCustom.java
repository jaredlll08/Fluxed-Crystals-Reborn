package fluxedCrystals.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class SlotCustom extends Slot
{

	ArrayList<ItemStack> validItems = new ArrayList<ItemStack>();
	public int slotMax;

	public SlotCustom(IInventory inventory, int number, int x, int y, int slotMax, ItemStack... validItems) {
		super(inventory, number, x, y);
		for (ItemStack stack : validItems) {
			this.validItems.add(stack);
		}
		this.slotMax = slotMax;
	}

	public int getSlotStackLimit(){
		
		return slotMax;
	}
	public boolean isItemValid(ItemStack stack) {
		if (validItems.isEmpty()) {
			return false;
		}

		for (ItemStack items : validItems) {
			if (items.isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}
}
