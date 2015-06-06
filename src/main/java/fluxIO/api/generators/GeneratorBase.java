package fluxIO.api.generators;

import java.util.EnumSet;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import fluxIO.network.MessageGenerator;
import fluxIO.network.PacketHandler;
import fluxIO.tileEntity.TileEnergyBase;

public abstract class GeneratorBase extends TileEnergyBase implements ISidedInventory {

	public ItemStack[] items;
	private int maxEnergy;
	public int generationTimer = -1;
	public int generationTimerDefault = -1;

	public GeneratorBase(int cap, int inventorySize) {
		super(cap);
		maxEnergy = cap;
		items = new ItemStack[inventorySize];

	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (generationTimerDefault < 0 && generationTimer < 0 && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
				if (getStackInSlot(0) != null) {

					if (canGenerateEnergy(getStackInSlot(0))) {
						generationTimer = getGenerationTime(getStackInSlot(0));
						generationTimerDefault = getGenerationTime(getStackInSlot(0));
						decrStackSize(0, 1);
						markDirty();
					}
					
				}
			}
			if (generationTimerDefault >= 0 && getEnergyStored() < getMaxStorage()) {
				generationTimer--;
				generateEnergy(worldObj, xCoord, yCoord, zCoord, generationTimer);
				markDirty();
			}
			if (generationTimer < 0 && generationTimerDefault >= 0) {
				generationTimer = -1;
				generationTimerDefault = -1;
				markDirty();
			}
		}
	}

	@Override
	public void markDirty() {

		super.markDirty();

		PacketHandler.INSTANCE.sendToAllAround(new MessageGenerator(this), new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, (double) this.xCoord, (double) this.yCoord, (double) this.zCoord, 128d));

		worldObj.func_147451_t(xCoord, yCoord, zCoord);

	}

	public abstract void generateEnergy(World world, int x, int y, int z, int timer);

	public abstract boolean canGenerateEnergy(ItemStack stack);

	public abstract int getGenerationTime(ItemStack stack);

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
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	public ItemStack addInventorySlotContents(int i, ItemStack itemstack) {
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
		return null;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeInventoryToNBT(nbt);
		nbt.setInteger("generationTimer", generationTimer);
		nbt.setInteger("generationTimerDefault", generationTimerDefault);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readInventoryFromNBT(nbt);
		generationTimer = nbt.getInteger("generationTimer");
		generationTimerDefault = nbt.getInteger("generationTimerDefault");
	}

	public void readInventoryFromNBT(NBTTagCompound tags) {
		NBTTagList nbttaglist = tags.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int iter = 0; iter < nbttaglist.tagCount(); iter++) {
			NBTTagCompound tagList = (NBTTagCompound) nbttaglist.getCompoundTagAt(iter);
			byte slotID = tagList.getByte("Slot");
			if (slotID >= 0 && slotID < items.length) {
				items[slotID] = ItemStack.loadItemStackFromNBT(tagList);
			}
		}
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

	public void closeInventory() {

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 64;
	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.allOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		markDirty();
		if (getValidOutputs().contains(from)) {
			int ret = storage.extractEnergy(maxExtract, true);
			if (!simulate) {
				storage.extractEnergy(ret, false);
			}
			return ret;
		}
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		markDirty();
		if (getValidInputs().contains(from)) {
			int ret = storage.receiveEnergy(maxReceive, true);
			if (!simulate) {
				storage.receiveEnergy(ret, false);
			}
			return ret;
		}
		return 0;
	}

}
