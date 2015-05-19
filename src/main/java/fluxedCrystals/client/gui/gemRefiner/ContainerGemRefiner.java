package fluxedCrystals.client.gui.gemRefiner;

import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import cpw.mods.fml.common.Loader;
import fluxedCrystals.client.gui.slot.SlotCustom;
import fluxedCrystals.client.gui.slot.SlotIBindable;
import fluxedCrystals.client.gui.slot.SlotUpgrade;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.items.ItemShardSmooth;
import fluxedCrystals.items.Upgrade;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ContainerGemRefiner extends Container {

	private TileEntityGemRefiner tile;
	private int lastStoredEnergy = -1;

	private static final int SMOOTH_SHARD_SLOT = 0;
	private static final int OUTPUT_SLOT = SMOOTH_SHARD_SLOT + 1;
	private static final int UPGRADE_SLOT_1 = OUTPUT_SLOT + 1, UPGRADE_SLOT_2 = UPGRADE_SLOT_1 + 1, UPGRADE_SLOT_3 = UPGRADE_SLOT_2 + 1;
	private static final int RANGE_SLOT = UPGRADE_SLOT_3 + 1;
	private static final int BLOOD_SLOT = RANGE_SLOT + 1;
	private static final int INV_START = BLOOD_SLOT+1, INV_END = INV_START+26,
			HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	public ContainerGemRefiner(InventoryPlayer invPlayer, TileEntityGemRefiner manager) {

		this.tile = manager;

		addSlotToContainer(new Slot(manager, SMOOTH_SHARD_SLOT, 46, 37));
		addSlotToContainer(new SlotCustom(manager, OUTPUT_SLOT, 114, 37, 64));
		addSlotToContainer(new SlotUpgrade(manager, UPGRADE_SLOT_1, 147, 8));
		addSlotToContainer(new SlotUpgrade(manager, UPGRADE_SLOT_2, 147, 26));
		addSlotToContainer(new SlotUpgrade(manager, UPGRADE_SLOT_3, 147, 44));

		addSlotToContainer(new SlotCustom(manager, RANGE_SLOT, 13, 62, 1, new ItemStack(FCItems.upgradeRangeBasic), new ItemStack(FCItems.upgradeRangeAdvanced), new ItemStack(FCItems.upgradeRangeGreater)));
		addSlotToContainer(new SlotIBindable(manager, BLOOD_SLOT, 147, 62));

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
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
	{

		ItemStack itemStack = null;

		Slot slot = (Slot)this.inventorySlots.get(slotNumber);

		if (slot != null && slot.getHasStack())
		{

			ItemStack itemstack1 = slot.getStack();
			itemStack = itemstack1.copy();

			// This is a slot in the gui transfer to inventory
			if (slotNumber < INV_START)
			{

				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true))
				{

					return null;

				}

				slot.onSlotChange(itemstack1, itemStack);

			}
			else
			{

				// If it is a rough shard lets move that to the ROUGH_SHARD_SLOT

				if (itemstack1.getItem() instanceof ItemShardSmooth)
				{

					if (!this.mergeItemStack(itemstack1, SMOOTH_SHARD_SLOT, SMOOTH_SHARD_SLOT + 1, false))
					{
						return null;
					}

				}
				else if (itemstack1.getItem() instanceof Upgrade)
				{

					// Assuming only 1 upgrade per slot?????

					ItemStack itemStack2 = new ItemStack(itemstack1.getItem(), 1);

					// If this is a range upgrade it goes in the RANGE_SLOT

					if (itemStack2.getItem() == FCItems.upgradeRangeBasic || itemStack2.getItem() == FCItems.upgradeRangeAdvanced || itemStack2.getItem() == FCItems.upgradeRangeGreater)
					{

						if (!((Slot)this.inventorySlots.get(RANGE_SLOT)).getHasStack())
						{

							if (!this.mergeItemStack(itemStack2, RANGE_SLOT, RANGE_SLOT + 1, false))
							{
								return null;
							}
							else
							{

								// We need to take 1 away from the original stack in the slot since we just moved one up

								itemstack1.stackSize--;

							}

						}

					}
					else
					{

						// Not a range upgrade, check the other slots

						if (!((Slot)this.inventorySlots.get(UPGRADE_SLOT_1)).getHasStack())
						{

							if (!this.mergeItemStack(itemStack2, UPGRADE_SLOT_1, UPGRADE_SLOT_1 + 1, false))
							{
								return null;
							}
							else
							{

								// We need to take 1 away from the original stack in the slot since we just moved one up

								itemstack1.stackSize--;

							}

						}
						else if (!((Slot)this.inventorySlots.get(UPGRADE_SLOT_2)).getHasStack())
						{

							if (!this.mergeItemStack(itemStack2, UPGRADE_SLOT_2, UPGRADE_SLOT_2 + 1, false))
							{
								return null;
							}
							else
							{

								// We need to take 1 away from the original stack in the slot since we just moved one up

								itemstack1.stackSize--;

							}

						}
						else if (!((Slot)this.inventorySlots.get(UPGRADE_SLOT_3)).getHasStack())
						{

							if (!this.mergeItemStack(itemStack2, UPGRADE_SLOT_3, UPGRADE_SLOT_3 + 1, false))
							{
								return null;
							}
							else
							{

								// We need to take 1 away from the original stack in the slot since we just moved one up

								itemstack1.stackSize--;

							}

						}
						else
						{
							return null;
						}

					}

				} else if (Loader.isModLoaded("AWWayofTime") && itemstack1.getItem() instanceof IBindable)
				{

					// Assuming only 1 upgrade per slot?????

					ItemStack itemStack2 = new ItemStack(itemstack1.getItem(), 1);

					if (!((Slot)this.inventorySlots.get(BLOOD_SLOT)).getHasStack())
					{

						if (!this.mergeItemStack(itemStack2, BLOOD_SLOT, BLOOD_SLOT + 1, false))
						{
							return null;
						}
						else
						{

							// We need to take 1 away from the original stack in the slot since we just moved one up

							itemstack1.stackSize--;

						}

					}

				}
				else
				{

					// Since we aren't sure of the other 2 slots we assume whatever makes it this far is junk

					return null;

				}

			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemStack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);

		}

		return itemStack;

	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if (!tile.getWorldObj().isRemote) {
			int energyStored = tile.getEnergyStored();
			// send energy to players viewing this GUI
			if (energyStored != lastStoredEnergy) {
				for (ICrafting c : (List<ICrafting>) crafters)
					c.sendProgressBarUpdate(this, 0, energyStored);
				lastStoredEnergy = energyStored;
			}
		}
	}

	@Override
	public void updateProgressBar(int bar, int value) {
		if (bar == 0) tile.setEnergyStored(value);
	}
}
