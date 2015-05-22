package fluxedCrystals.client.gui.seedInfuser;

import fluxedCrystals.client.gui.slot.SlotInfuser;
import fluxedCrystals.client.gui.slot.SlotSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSeedInfuser extends Container
{

	private static final int SEED_SLOT = 0;
	private static final int INFUSE_SLOT = SEED_SLOT + 1;
	private static final int INV_START = INFUSE_SLOT + 1, INV_END = INV_START + 26, HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

	public ContainerSeedInfuser (InventoryPlayer invPlayer, TileEntitySeedInfuser manager) {

		addSlotToContainer(new SlotSeed(manager, SEED_SLOT, 9, 9));
		addSlotToContainer(new SlotInfuser(manager, INFUSE_SLOT, 151, 9));

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
		}

	}

	@Override
	public boolean canInteractWith (EntityPlayer p_75145_1_) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot (EntityPlayer player, int slotNumber) {

		ItemStack itemStack = null;

		Slot slot = (Slot) this.inventorySlots.get(slotNumber);

		if (slot != null && slot.getHasStack()) {

			ItemStack itemstack1 = slot.getStack();
			itemStack = itemstack1.copy();

			// This is a slot in the gui transfer to inventory
			if (slotNumber < INV_START) {

				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {

					return null;

				}

				slot.onSlotChange(itemstack1, itemStack);

			}
			else {

				// If we get here we need to transfer into the gui

				if (itemstack1.getItem() instanceof ItemUniversalSeed && !((Slot) this.inventorySlots.get(SEED_SLOT)).getHasStack()) {

					// This gets a bit funny since we can only put 1 universal seed in there, but the slot takes 64 (ignores the custom slot we have)

					ItemStack itemStack2 = new ItemStack(itemstack1.getItem(), 1);

					if (!this.mergeItemStack(itemStack2, SEED_SLOT, SEED_SLOT + 1, false)) {
						return null;
					}
					else {

						// We need to take 1 away from the original stack in the slot since we just moved one up

						itemstack1.stackSize--;

					}

				}
				else {

					boolean isUsable = false;

					for (int i : RecipeRegistry.getAllSeedInfuserRecipes().keySet()) {

						if (RecipeRegistry.getSeedInfuserRecipeByID(i).matchesIngredient(itemstack1)) {

							isUsable = true;

							break;

						}

					}

					if (isUsable) {

						if (!this.mergeItemStack(itemstack1, INFUSE_SLOT, INFUSE_SLOT + 1, false)) {
							return null;
						}

					}
					else {
						return null;
					}

				}

			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemStack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);

		}

		return itemStack;

	}

}
