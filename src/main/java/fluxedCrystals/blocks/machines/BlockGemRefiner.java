package fluxedCrystals.blocks.machines;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGemRefiner extends Block implements ITileEntityProvider
{
	public BlockGemRefiner () {
		super(Material.anvil);
		this.setHardness(2.0F);
		setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName(Textures.Blocks.GEM_REFINER);
	}

	public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(FluxedCrystals.instance, 6, world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity (World p_149915_1_, int p_149915_2_) {
		return new TileEntityGemRefiner();
	}

	public void onBlockPreDestroy (World world, int x, int y, int z, int meta) {
		TileEntityGemRefiner tile = (TileEntityGemRefiner) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null) {
					dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
				}
			}
		}

	}
}
