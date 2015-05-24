package fluxIO.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import fluxIO.api.Registry;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxIO.tileEntity.TileEntityTrashGenerator;

public class BlockTrashGenerator extends BlockContainer {

	public BlockTrashGenerator() {
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntityTrashGenerator tile = (TileEntityTrashGenerator) world.getTileEntity(x, y, z);

			if (player.getCurrentEquippedItem() != null)
				tile.addInventorySlotContents(0, player.getCurrentEquippedItem());

			player.addChatComponentMessage(new ChatComponentText("Energy:" + tile.getEnergyStored()));
			if (tile.getStackInSlot(0) != null)
				player.addChatComponentMessage(new ChatComponentText("Items:" + tile.getStackInSlot(0).getDisplayName() + ":" + tile.getStackInSlot(0).stackSize));

		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTrashGenerator();
	}
}
