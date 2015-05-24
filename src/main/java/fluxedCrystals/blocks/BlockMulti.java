package fluxedCrystals.blocks;

import java.util.ArrayList;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.util.BlockConnected;
import fluxedCrystals.util.TilebigCube;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMulti extends Block implements ITileEntityProvider {
	public ArrayList<BlockConnected> connectedBlocks = new ArrayList<BlockConnected>(26);

	public BlockMulti() {
		super(Material.iron);

	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		for (BlockConnected block : connectedBlocks) {
			block.destroy(world);
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {

		for (int i = 0; i < 3; i++) {
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x + 1, y + i, z, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x - 1, y + i, z, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x, y + i, z + 1, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x, y + i, z - 1, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x + 1, y + i, z - 1, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x - 1, y + i, z + 1, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x + 1, y + i, z + 1, this));
			connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x - 1, y + i, z - 1, this));
		}
		connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x, y + 1, z, this));
		connectedBlocks.add(new BlockConnected(FCBlocks.hidden, x, y + 2, z, this));
		if (canPlaceBlocks(world))
			for (BlockConnected block : connectedBlocks) {
				block.placeBlock(world);
			}

		return meta;
	}

	public boolean canPlaceBlocks(World world) {
		for (BlockConnected block : connectedBlocks) {
			if (!block.canPlaceblock(world)) {
				return false;
			}
		}

		return true;

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TilebigCube();
	}
}
