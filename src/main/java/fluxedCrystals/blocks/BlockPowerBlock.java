package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockPowerBlock extends Block implements ITileEntityProvider
{

	protected BlockPowerBlock()
	{

		super(Material.grass);
		this.setHardness(1.0F);
		setHarvestLevel("shovel", 1);

	}

	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		TileEntityPowerBlock tile = (TileEntityPowerBlock) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null)
					dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
			}
		}
	}

}
