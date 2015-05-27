package fluxIO.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fluxIO.tileEntity.multiBlock.TileEntitySteamGeneratorConnected;
import fluxIO.utils.multiBlock.BlockCoord;

public class BlockSteamGenerator extends Block {

	protected BlockSteamGenerator() {
		super(Material.iron);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != null && world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == this) {
				TileEntitySteamGeneratorConnected connected = new TileEntitySteamGeneratorConnected();
				connected.setBlock(new BlockCoord(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ), x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));
				connected.setBaseblock(new BlockCoord(world.getTileEntity(x, y, z)));
				world.setTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, connected);
			}	
		}
		return true;
	}

}
