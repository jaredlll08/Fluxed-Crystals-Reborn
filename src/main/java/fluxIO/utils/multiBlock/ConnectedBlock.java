package fluxIO.utils.multiBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ConnectedBlock extends Block {

	public ConnectedBlock() {
		super(Material.air);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		return ((ConnectedTile) world.getTileEntity(x, y, z)).getBlock().block.onBlockActivated(world, x, y, z, player, meta, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		((ConnectedTile) world.getTileEntity(x, y, z)).getBlock().block.onBlockHarvested(world, x, y, z, meta, player);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		((ConnectedTile) world.getTileEntity(x, y, z)).getBlock().block.breakBlock(world, x, y, z, block, meta);
	}
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return ((ConnectedTile)world.getTileEntity(x, y, z)).getMasterBlock().block==null;
	}
}
