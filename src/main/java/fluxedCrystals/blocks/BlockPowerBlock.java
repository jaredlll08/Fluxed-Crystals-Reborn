package fluxedCrystals.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;

public class BlockPowerBlock extends Block  implements ITileEntityProvider
{

	public BlockPowerBlock() {

		super(Material.grass);
		this.setHardness(1.0F);
		setHarvestLevel("shovel", 1);
		setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + Names.Blocks.POWEREDSOIL);
	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowerBlock();
	}

	// public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
	// {
	// TileEntityPowerBlock tile = (TileEntityPowerBlock) world.getTileEntity(x,
	// y, z);
	// if (tile != null) {
	// for (int i = 0; i < tile.getSizeInventory(); i++) {
	// if (tile.getStackInSlot(i) != null)
	// dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
	// }
	// }
	// }

}
