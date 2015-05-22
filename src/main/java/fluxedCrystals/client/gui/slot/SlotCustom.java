package fluxedCrystals.client.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class SlotCustom extends Slot
{

	private int slotMax;
	private ArrayList<ItemStack> validItems = new ArrayList<ItemStack>();

	public SlotCustom (IInventory inventory, int number, int x, int y, int slotMax, ItemStack... validItems) {
		super(inventory, number, x, y);
		Collections.addAll(this.validItems, validItems);
		this.slotMax = slotMax;
	}

	public int getSlotStackLimit () {

		return slotMax;
	}

	public boolean isItemValid (ItemStack stack) {
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
