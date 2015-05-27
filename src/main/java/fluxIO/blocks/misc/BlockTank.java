package fluxIO.blocks.misc;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import fluxIO.tileEntity.misc.TileEntityTank;

public class BlockTank extends Block implements ITileEntityProvider {

	protected BlockTank() {
		super(Material.glass);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		if (world.getBlock(tileX, tileY, tileZ) != null && world.getTileEntity(tileX, tileY, tileZ) != null && world.getTileEntity(tileX, tileY, tileZ) instanceof TileEntityTank) {
			TileEntityTank fluidTank = (TileEntityTank) world.getTileEntity(tileX, tileY, tileZ);
			TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);

			if (tile.getMaster() == null) {
				fluidTank.setMaster(tile);
			} else if (tile.getMaster() != null) {
				fluidTank.setMaster(tile.getMaster());
			}
			
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return null;
	}

}
