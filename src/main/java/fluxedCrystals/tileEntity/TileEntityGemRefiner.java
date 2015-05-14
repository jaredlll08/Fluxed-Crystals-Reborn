package fluxedCrystals.tileEntity;

import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.RecipeGemRefiner;
import fluxedCrystals.recipe.RecipeRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaReceiver;

import java.util.EnumSet;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileEntityGemRefiner extends TileEnergyBase implements IInventory, IManaReceiver, ISidedInventory {

	private static int[] slotsAll = {0, 1, 2, 3, 4, 5, 6};
	private final int[] UPGRADE_SLOTS = {2, 3, 4};
	public ItemStack[] items;
	private int refined = 0;
	// -1 if not currently refining
	private int recipeIndex;
	private int mana;
	private int MAX_MANA;
	private boolean RF = true;
	private int energy = 0;

	public TileEntityGemRefiner() {
		super(10000);
		MAX_MANA = getMaxStorage();
		mana = 0;
		items = new ItemStack[7];

	}

	public int getRecipeIndex() {
		return recipeIndex;
	}

	public void setRecipeIndex(int recipeIndex) {
		this.recipeIndex = recipeIndex;
	}

	public int getRefined() {
		return refined;
	}

	public void updateEntity() {
		super.updateEntity();
		if (getStackInSlot(0) != null) if (worldObj != null && !worldObj.isRemote && getRecipeIndex() >= 0) {
			if (storage.getEnergyStored() > 0) {
				if (!isUpgradeActive(FCItems.upgradeMana) && !isUpgradeActive(FCItems.upgradeLP) && !isUpgradeActive(FCItems.upgradeEssentia)) {
					if (getStackInSlot(1) != null) {
						if (worldObj.getTotalWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency() && getStackInSlot(1).stackSize < getStackInSlot(1).getMaxStackSize()) {
							storage.extractEnergy(refineShard(), false);
							return;
						}
					} else {
						if (worldObj.getTotalWorldTime() % getSpeed() == 0 && storage.getEnergyStored() >= getEffeciency()) {
							storage.extractEnergy(refineShard(), false);
							return;
						}
					}
				}
			}
		}
	}

	public boolean isUpgradeActive(Item upgradeItem) {
		for (int slot : UPGRADE_SLOTS) {
			ItemStack stack = getStackInSlot(slot);
			if (stack != null && stack.getItem() == upgradeItem) {
				return true;
			}
		}
		return false;
	}

	public int getSpeed() {
		int speed = 100;
		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null && item.getItem() == FCItems.upgradeSpeed) {
				speed -= 20;
			}
		}
		return speed;
	}

	public int getEffeciency() {
		int eff = 250;
		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null) {
				if (item.getItem() == FCItems.upgradeSpeed) {
					eff += 30;
				}
				if (item.getItem() == FCItems.upgradeEffeciency) {
					eff -= 25;
				}
			}
		}

		if (eff <= 0) {
			eff = 1;
		}
		return eff;
	}

	@Override
	public void closeInventory() {

	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);

			}
		}

		return itemstack;
	}

	@Override
	public String getInventoryName() {
		return "Gem Refiner";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return items[par1];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, item);
		return item;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (stack == null) {
			return false;
		}
		switch (slot) {
			default:
				return false;

			case 0:

				for (int i : RecipeRegistry.getAllGemRefinerRecipes().keySet()) {

					RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(i);

					if (recipe.getInput().isItemEqual(stack)) {

						return true;

					}

				}
			case 1:
				return false;

		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		boolean changedItem;
		if (items[i] == null || itemstack == null)
			changedItem = (items[i] == null) != (itemstack == null); // non-null to null, or vice versa
		else changedItem = !items[i].isItemEqual(itemstack);

		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}

		if (i == 0 && changedItem) updateCurrentRecipe();
	}

	public boolean addInventorySlotContents(int i, ItemStack itemstack) {
		if (items[i] != null) {

			if (items[i].isItemEqual(itemstack)) {
				items[i].stackSize += itemstack.stackSize;
			}
			if (items[i].stackSize > getInventoryStackLimit()) {
				items[i].stackSize = getInventoryStackLimit();
			}
		} else {
			setInventorySlotContents(i, itemstack);
		}
		return false;
	}

	/* NBT */
	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
		refined = tags.getInteger("refined");
		setRecipeIndex(tags.getInteger("recipeIndex"));
		mana = tags.getInteger("mana");
		updateCurrentRecipe();
	}

	public void readInventoryFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = nbttaglist.getCompoundTagAt(iter);
			byte slotID = tagList.getByte("Slot");
			if (slotID >= 0 && slotID < items.length) {
				items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeInventoryToNBT(tags);
		tags.setInteger("refined", refined);
		tags.setInteger("recipeIndex", getRecipeIndex());
		tags.setInteger("mana", mana);
	}

	public void writeInventoryToNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int iter = 0; iter < items.length; iter++) {
			if (items[iter] != null) {
				NBTTagCompound tagList = new NBTTagCompound();
				tagList.setByte("Slot", (byte) iter);
				items[iter].writeToNBT(tagList);
				nbttaglist.appendTag(tagList);
			}
		}

		tags.setTag("Items", nbttaglist);
	}

	public boolean addItemToSlot(int slotNumber, ItemStack stack) {
		boolean returnBool = false;
		if (stack != null) {
			if (getStackInSlot(slotNumber) == null || (getStackInSlot(slotNumber).isItemEqual(stack)) && (getStackInSlot(slotNumber).getMaxStackSize() - getStackInSlot(slotNumber).stackSize - stack.stackSize) > 0) {
				ItemStack out = stack.copy();
				out.stackSize = stack.stackSize;
				if (getStackInSlot(slotNumber) != null) {
					out.stackSize += getStackInSlot(slotNumber).stackSize;
				}
				setInventorySlotContents(slotNumber, out);
				returnBool = true;
			}
		}

		return returnBool;
	}

	public int refineShard() {
		int energyUsed = 0;

		if (getRecipeIndex() != -1 && getStackInSlot(0) != null) {

			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0)) && recipe.getInputamount() <= getStackInSlot(0).stackSize) {
				refined++;
				ItemStack out = recipe.getOutput().copy();
				out.stackSize = recipe.getOutputAmount();
				if (out.getItemDamage() == 32767) out.setItemDamage(0);

				if (addItemToSlot(1, out)) {
					decrStackSize(0, recipe.getInputamount());
					refined = 0;
					energyUsed = (150 * recipe.getInputamount());
				}
			}
		}

		return energyUsed;
	}

	public boolean refine() {

		if (getRecipeIndex() != -1 && getStackInSlot(0) != null) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					storage.extractEnergy(250, false);
					if (refined == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						refined = 0;
						setRecipeIndex(-1);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		return false;
	}

	public boolean refineMana() {
		if (getRecipeIndex() != -1 && getStackInSlot(0) != null) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					mana -= 250;
					if (refined == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						refined = 0;
						setRecipeIndex(-1);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		return false;
	}

	public boolean refineLP() {
		if (getRecipeIndex() != -1 && getStackInSlot(0) != null) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					SoulNetworkHandler.syphonFromNetwork(getStackInSlot(6), 250 / 4);
					if (refined == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						refined = 0;
						setRecipeIndex(-1);

					}
				}
				return true;
			}
		}
		refined = 0;
		setRecipeIndex(-1);
		return false;
	}

	public boolean refineEssentia() {
		if (getRecipeIndex() != -1 && getStackInSlot(0) != null) {
			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(recipeIndex);
			if (recipe.matchesExact(getStackInSlot(0))) {
				if (getStackInSlot(1) == null || getStackInSlot(1).isItemEqual(recipe.getOutput())) {
					decrStackSize(0, 1);
					refined++;
					if (refined == recipe.getInputamount()) {
						ItemStack out = recipe.getOutput().copy();
						out.stackSize = recipe.getOutputAmount();
						addInventorySlotContents(1, out);
						refined = 0;
						setRecipeIndex(-1);

					}
				}
				return true;
			}
		}
		refined = 0;
		return false;
	}

	public void updateCurrentRecipe() {
		setRecipeIndex(-1);
		if (getStackInSlot(0) != null) {

			for (int i : RecipeRegistry.getAllGemRefinerRecipes().keySet()) {

				RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(i);

				if (recipe.matchesExact(getStackInSlot(0))) {
					setRecipeIndex(i);
					break;
				}

			}

		}

	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	@Override
	public int getCurrentMana() {
		return mana;
	}

	@Override
	public boolean isFull() {
		return mana == MAX_MANA;
	}

	@Override
	public void recieveMana(int mana) {
		if (!isFull()) {
			this.mana += mana;
		}
		if (getCurrentMana() > MAX_MANA) {
			this.mana = MAX_MANA;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return slotsAll;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		if (isItemValidForSlot(slot, stack)) {

			for (int i : RecipeRegistry.getAllGemRefinerRecipes().keySet()) {

				RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(i);

				if (recipe.getInput().isItemEqual(stack)) {
					return true;
				}

			}

		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot != 0 && slot != 2 && slot != 3 && slot != 4;
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return true;
	}
}