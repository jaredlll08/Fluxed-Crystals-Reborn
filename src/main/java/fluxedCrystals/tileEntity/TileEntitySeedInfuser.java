package fluxedCrystals.tileEntity;

import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.recipe.RecipeSeedInfuser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntitySeedInfuser extends TileEntity implements IInventory
{

	public ItemStack[] items;

	private boolean infusing = false;
	private int infused = 0;
	private int recipeIndex;

	public TileEntitySeedInfuser () {
		items = new ItemStack[3];
	}

	public int getRecipeIndex () {
		return recipeIndex;
	}

	public void setRecipeIndex (int recipeIndex) {
		this.recipeIndex = recipeIndex;
	}

	public boolean isInfusing () {
		return infusing;
	}

	public void setInfusing (boolean infusing) {

		this.infusing = infusing;
		System.out.println("0|" + infusing);
		setRecipeIndex(-1);
		System.out.println("1|" + getRecipeIndex());

		if (getStackInSlot(0) != null && getStackInSlot(1) != null) {
			System.out.println("2|" + (getStackInSlot(0) != null) + "|" + (getStackInSlot(1) != null));

			for (RecipeSeedInfuser recipe : RecipeRegistry.getAllSeedInfuserRecipes().values()) {
				System.out.println("3|" + recipe.getIndex());


				if (recipe.matches(getStackInSlot(0), getStackInSlot(1)) || recipe.matchesExact(getStackInSlot(0), getStackInSlot(1))) {
					System.out.println("4|" + recipe.getIndex());

					setRecipeIndex(recipe.getIndex());
					break;

				}

			}

		}

	}

	public int getInfused () {
		return infused;
	}

	public void updateEntity () {
		if (infusing && worldObj.getTotalWorldTime() % 20 == 0 && !worldObj.isRemote) {
			infuseSeed();
		}
	}

	@Override
	public void closeInventory () {

	}

	@Override
	public ItemStack decrStackSize (int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			}
			else {
				itemstack = itemstack.splitStack(count);

			}
		}

		return itemstack;
	}

	@Override
	public String getInventoryName () {
		return "Seed Infuser";
	}

	@Override
	public int getInventoryStackLimit () {
		return 64;
	}

	@Override
	public int getSizeInventory () {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot (int par1) {

		return items[par1];
	}

	@Override
	public ItemStack getStackInSlotOnClosing (int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, item);
		return item;
	}

	@Override
	public boolean hasCustomInventoryName () {
		return false;
	}

	@Override
	public boolean isItemValidForSlot (int slot, ItemStack stack) {
		return false;
	}

	@Override
	public boolean isUseableByPlayer (EntityPlayer arg0) {
		return true;
	}

	@Override
	public void openInventory () {

	}

	@Override
	public void setInventorySlotContents (int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	/* NBT */
	@Override
	public void readFromNBT (NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
		infusing = tags.getBoolean("infusing");
		infused = tags.getInteger("infused");
		setRecipeIndex(tags.getInteger("recipeIndex"));
	}

	public void readInventoryFromNBT (NBTTagCompound tags) {
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
	public void writeToNBT (NBTTagCompound tags) {
		super.writeToNBT(tags);
		writeInventoryToNBT(tags);
		tags.setBoolean("infusing", infusing);
		tags.setInteger("infused", infused);
		tags.setInteger("recipeIndex", getRecipeIndex());
	}

	public void writeInventoryToNBT (NBTTagCompound tags) {
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

	public boolean addItemToSlot (int slotNumber, ItemStack stack) {

		boolean returnBool = false;

		if (stack != null) {

			if (getStackInSlot(slotNumber).isItemEqual(new ItemStack(FCItems.universalSeed)) || (getStackInSlot(slotNumber).isItemEqual(stack)) && (getStackInSlot(slotNumber).getMaxStackSize() - getStackInSlot(slotNumber).stackSize - stack.stackSize) > 0) {

				ItemStack out = stack.copy();

				if (getStackInSlot(slotNumber) != null && getStackInSlot(slotNumber).isItemEqual(stack)) {
					out.stackSize += getStackInSlot(slotNumber).stackSize;
				}

				setInventorySlotContents(slotNumber, out);

				returnBool = true;

			}

		}

		return returnBool;
	}

	public boolean infuseSeed () {
		System.out.println("0:" + getRecipeIndex());
		if (getRecipeIndex() != -1) {
			System.out.println("1:" + getRecipeIndex());
			RecipeSeedInfuser recipe = RecipeRegistry.getSeedInfuserRecipeByID(getRecipeIndex());
			System.out.println("2:" + recipe.getIngredient().getDisplayName() + "|" + recipe.getOutput().getDisplayName());

			if (getStackInSlot(0) != null && getStackInSlot(0).getItem() == FCItems.universalSeed && getStackInSlot(1) != null) {
				System.out.println("3: not null");

				if ((recipe.matches(getStackInSlot(0), getStackInSlot(1)) || recipe.matchesExact(getStackInSlot(0), getStackInSlot(1))) && getStackInSlot(1).stackSize >= recipe.getInputamount()) {
					System.out.println("4: it matches and infused");

					setInventorySlotContents(0, new ItemStack(FCItems.seed, 1, getRecipeIndex()));
					decrStackSize(1, recipe.getInputamount());
					infusing = false;
					infused = 0;
					setRecipeIndex(-1);
					return true;

				}

			}

		}

		infused = 0;
		setRecipeIndex(-1);
		infusing = false;
		return false;

	}

}
