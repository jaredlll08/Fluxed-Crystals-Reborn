package fluxIO.client.gui.slot;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SlotTileDep extends Slot {

	private int slotMax;
	private IInventory tile;

	public SlotTileDep(IInventory inventory, int number, int x, int y) {
		super(inventory, number, x, y);
		this.tile = inventory;
		this.slotMax = tile.getInventoryStackLimit();
	}

	public int getSlotStackLimit() {
		return slotMax;
	}

	public boolean isItemValid(ItemStack stack) {
		return tile.isItemValidForSlot(this.slotNumber, stack);
	}
	@Override
	public IIcon getBackgroundIconIndex() {
		return Items.coal.getIconFromDamage(0);
	}
}
