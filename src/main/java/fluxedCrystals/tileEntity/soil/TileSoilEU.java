package fluxedCrystals.tileEntity.soil;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.blocks.crystal.CrystalBase;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.util.ITileSoil;

/**
 * Created by Jared on 11/2/2014.
 */
public class TileSoilEU extends TileEntity implements ISidedInventory, ITileSoil, IEnergySink {

	private final int[] UPGRADE_SLOTS = { 0, 1, 2 };
	public ItemStack[] items;
	public double energy;
	public double maxEnergy;
	public boolean addedToEnet;
	public boolean addedToWorld;
	public TileSoilEU() {
		super();
		items = new ItemStack[3];
		maxEnergy = 10000 / 4;
		energy = 0;
	}

	public boolean canUpdate() {
		return true;
	}

	public boolean growPlant(World world, boolean night) {
		if (world != null) {
			if (world.getBlock(xCoord, yCoord + 1, zCoord) instanceof CrystalBase) {
				return ((CrystalBase) world.getBlock(xCoord, yCoord + 1, zCoord)).growCrop(world, xCoord, yCoord + 1, zCoord, world.rand, night);
			}
		}
		return false;
	}

	public BlockCrystal getCrop(World world) {
		return world.getBlock(xCoord, yCoord + 1, zCoord) != null && world.getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockCrystal ? (BlockCrystal) world.getBlock(xCoord, yCoord + 1, zCoord) : null;
	}

	public TileEntityCrystal getCropTile(World world) {
		return world.getTileEntity(xCoord, yCoord + 1, zCoord) != null && world.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof TileEntityCrystal ? (TileEntityCrystal) world.getTileEntity(xCoord, yCoord + 1, zCoord) : null;
	}

	/* NBT */
	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);
		readInventoryFromNBT(tags);
		energy = tags.getDouble("energy");
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
		tags.setDouble("energy", energy);
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
		return "Power Soil EU";
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
		return false;
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
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}

	public boolean addUpgrade(ItemStack stack) {
		ItemStack upgrade = stack.copy();
		upgrade.stackSize = 1;

		for (int slot : UPGRADE_SLOTS) {
			if (getStackInSlot(slot) == null) {
				setInventorySlotContents(slot, upgrade);
				return true;
			}
		}
		return false;
	}

	public ItemStack removeUpgrade() {
		for (int slot : UPGRADE_SLOTS) {
			ItemStack stack = getStackInSlot(slot);
			if (stack != null) {
				setInventorySlotContents(slot, null);
				return stack;
			}
		}
		return null;
	}

	public int getSpeed() {
		int speed = 8;
		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null && item.getItem() == FCItems.upgradeSpeed) {
				speed += 2;
			}
		}
		return speed;
	}

	public int getEffeciency() {
		int eff = 0;
		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null && item.getItem() == FCItems.upgradeEffeciency) {
				eff += 15;
			}
		}
		if (eff <= 0) {
			eff = 1;
		}
		return eff;
	}

	public boolean isUpgradeActive(Item upgradeItem) {
		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null && item.getItem() == upgradeItem) {
				return true;
			}
		}
		return false;
	}

	public int getUpgradeDrain(int idx) {
		int energy = SeedRegistry.getInstance().getSeedByID(idx).powerPerStage;

		for (int slot : UPGRADE_SLOTS) {
			ItemStack item = getStackInSlot(slot);
			if (item != null) {
				if (item.isItemEqual(new ItemStack(FCItems.upgradeNight))) {
					energy += energy / 15;
				}
				if (item.isItemEqual(new ItemStack(FCItems.upgradeSpeed))) {
					energy += energy / 12;
				}
			}
		}

		if (isUpgradeActive(FCItems.upgradeEffeciency)) {
			energy /= getEffeciency();
		}

		return energy;
	}

	@Override
	public int getStoredEnergy() {
		return (int) energy;
	}

	@Override
	public boolean canDrainEnergy(int energy) {
		return this.energy + energy <= maxEnergy;
	}

	@Override
	public void drainEnergy(int energy) {
		this.energy -= energy;
	}

	@Override
	public int scaleEnergy(int energy) {
		return energy / 4;
	}

	public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
		return true;
	}

	public double getDemandedEnergy() {
		return 32.0D;
	}

	public int getSinkTier() {
		return 1;
	}

	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		double leftOver = amount;
		if (amount + this.energy <= this.maxEnergy) {
			this.energy = ((int) (this.energy + amount));
			leftOver = 0.0D;
		}
		if (amount + this.energy > this.maxEnergy) {
			this.energy = this.maxEnergy;
			leftOver = this.energy + amount - this.maxEnergy;
		}
		return leftOver;
	}

	public boolean drainEnergy(double amount) {
		return this.energy - injectEnergy(null, -amount, 0.0D) >= 0.0D;
	}
	public void updateEntity()
	  {
	    super.updateEntity();
	    if ((!this.worldObj.isRemote) && (!this.addedToWorld))
	    {
	      this.addedToWorld = true;
	      this.addedToEnet = true;
	      MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	    }
	  
	  }
	  
	  public void validate()
	  {
	    super.validate();
	    if (!this.addedToEnet) {
	      this.addedToWorld = false;
	    }
	  }
	  
	  public void invalidate()
	  {
	    if (this.addedToEnet)
	    {
	      if (!this.worldObj.isRemote) {
	        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	      }
	      this.addedToEnet = false;
	    }
	    super.invalidate();
	  }
}
