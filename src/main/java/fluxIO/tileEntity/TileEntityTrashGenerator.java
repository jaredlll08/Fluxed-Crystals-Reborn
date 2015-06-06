package fluxIO.tileEntity;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import fluxIO.api.Registry;
import fluxIO.api.Registry.TrashGenerator;
import fluxIO.api.generators.GeneratorBase;

public class TileEntityTrashGenerator extends GeneratorBase {

	public TileEntityTrashGenerator() {
		super(20000, 1);
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
				this.storage.receiveEnergy(10, false);
			}
		}
	}

	@Override
	public String getInventoryName() {
		return "Trash Generator";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[] { 0 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int p_102007_3_) {

		return slot == 0 && TrashGenerator.canTrash(stack);
	}

	@Override
	public boolean isItemValidForSlot(int slotNumber, ItemStack stack) {
		return Registry.TrashGenerator.canTrash(stack);
	}

	@Override
	public boolean canGenerateEnergy(ItemStack stack) {
		return TrashGenerator.canTrash(stack);
	}

	@Override
	public int getGenerationTime(ItemStack stack) {
		return 40;
	}

}
