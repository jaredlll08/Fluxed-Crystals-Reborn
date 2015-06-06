package fluxIO.tileEntity;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import fluxIO.api.Registry;
import fluxIO.api.generators.GeneratorBase;

public class TileEntityCoalGenerator extends GeneratorBase {

	public TileEntityCoalGenerator() {
		super(50000, 1);
	}

	public boolean canGenerateEnergy(ItemStack stack) {
		return Registry.BasicCoalGenerator.containsItemStack(stack);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateEnergy(World world, int x, int y, int z, int generationTimer) {
		if (!world.isRemote) {
			for (EntityPlayer player : (List<EntityPlayer>) world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - 5, y - 5, z - 5, x + 5, y + 5, z + 5))) {
				player.addChatComponentMessage(new ChatComponentText("Energy:" + getEnergyStored()));
				if (getStackInSlot(0) != null) player.addChatComponentMessage(new ChatComponentText("Items:" + getStackInSlot(0).getDisplayName() + ":" + getStackInSlot(0).stackSize));
				player.addChatComponentMessage(new ChatComponentText("Timer:" + generationTimer));
				player.addChatComponentMessage(new ChatComponentText("Timer Default:" + generationTimerDefault));
			}
			if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
				this.storage.receiveEnergy(40, false);
			}
		}
	}

	@Override
	public String getInventoryName() {
		return "Coal Generator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int p_102007_3_) {
		return slot == 0 && canGenerateEnergy(stack);
	}

	@Override
	public boolean isItemValidForSlot(int slotNumber, ItemStack stack) {
		for (String str : OreDictionary.getOreNames()) {
			if (str.toLowerCase().contains("coal")) {
				for (ItemStack item : OreDictionary.getOres(str)) {
					if (item.isItemEqual(stack)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

	@Override
	public int getGenerationTime(ItemStack stack) {
		return Registry.BasicCoalGenerator.getBurnTime(stack);
	}

}
