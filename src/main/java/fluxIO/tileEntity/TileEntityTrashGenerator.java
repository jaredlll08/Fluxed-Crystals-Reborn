package fluxIO.tileEntity;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import fluxIO.api.Registry;
import fluxIO.api.generators.GeneratorBase;

public class TileEntityTrashGenerator extends GeneratorBase {
	private static int maxEnergy = 10000;

	private int generationTimer = -1;
	private int generationTimerDefault = -1;

	public TileEntityTrashGenerator() {
		super(maxEnergy, 1);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!worldObj.isRemote) {
			if (generationTimerDefault < 0 && generationTimer < 0 && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
				if (getStackInSlot(0) != null) {

					if (Registry.TrashGenerator.canTrash(getStackInSlot(0))) {
						System.out.println("hi");
						generationTimer = 80;
						generationTimerDefault = 80;
					}
					decrStackSize(0, 1);
				}
			}
			if (generationTimerDefault >= 0 && getEnergyStored() < getMaxStorage()) {
				generationTimer--;
				generateEnergy(worldObj, xCoord, yCoord, zCoord, generationTimer);
			}
			if (generationTimer < 0 && generationTimerDefault >= 0) {
				generationTimer = -1;
				generationTimerDefault = -1;
			}
		}
	}

	@Override
	public void generateEnergy(World world, int x, int y, int z, int generationTimer) {
		if (!world.isRemote) {
			for (EntityPlayer player : (List<EntityPlayer>) world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - 5, y - 5, z - 5, x + 5, y + 5, z + 5))) {
				player.addChatComponentMessage(new ChatComponentText("Energy:" + getEnergyStored()));
				if (getStackInSlot(0) != null)
					player.addChatComponentMessage(new ChatComponentText("Items:" + getStackInSlot(0).getDisplayName() + ":" + getStackInSlot(0).stackSize));
				player.addChatComponentMessage(new ChatComponentText("Timer:" + generationTimer));
				player.addChatComponentMessage(new ChatComponentText("Timer Default:" + generationTimerDefault));

			}

			if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
				this.storage.receiveEnergy(10, false);
			}
		}
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

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return true;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}

	@Override
	public String getInventoryName() {
		return "Trash Generator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public EnumSet<ForgeDirection> getValidOutputs() {
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public EnumSet<ForgeDirection> getValidInputs() {
		return EnumSet.allOf(ForgeDirection.class);
	}

}
