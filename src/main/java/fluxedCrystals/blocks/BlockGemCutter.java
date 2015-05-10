package fluxedCrystals.blocks;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGemCutter extends Block implements ITileEntityProvider
{
	public BlockGemCutter () {
		super(Material.anvil);
		this.setHardness(2.0F);
		setHarvestLevel("pickaxe", 2);
		setBlockName(Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.GEM_CUTTER);
		this.setCreativeTab(FluxedCrystals.tab);
		this.setBlockTextureName(Textures.Blocks.GEM_CUTTER);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {
			player.openGui(FluxedCrystals.instance, 7, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGemCutter();
	}

	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		TileEntityGemCutter tile = (TileEntityGemCutter) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null)
					dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
			}
		}
		
	}

}
