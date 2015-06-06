package fluxIO.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxIO.FluxIO;
import fluxIO.tileEntity.TileEntityCoalGenerator;

public class BlockCoalGenerator extends BlockContainer {

	public BlockCoalGenerator() {
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {

			player.openGui(FluxIO.instance, 0, world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCoalGenerator();
	}
}
