package fluxedCrystals.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockConnected extends Block {
	private Block block;
	private int x;
	private int y;
	private int z;
	private Block baseBlock;

	public BlockConnected() {
		super(Material.air);
	}

	public BlockConnected(Block block, int x, int y, int z, Block baseBlock) {

		super(block.getMaterial());
		this.block = block;
		this.x = x;
		this.y = y;
		this.z = z;
		this.baseBlock = baseBlock;
	}

	public void placeBlock(World world) {
		world.setBlock(x, y, z, block);
	}

	public boolean canPlaceblock(World world) {
		return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		
	}

	public void destroy(World world) {
		block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
		world.setBlockToAir(x, y, z);
	}

	public Block getBlock() {
		return block;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Block getBaseBlock() {
		return baseBlock;
	}

}
