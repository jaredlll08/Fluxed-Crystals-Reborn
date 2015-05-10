package fluxedCrystals.blocks.crystal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public abstract class CrystalBase extends Block {

	public IIcon[] icons;

	public CrystalBase() {

		super(Material.plants);

		float f = 0.5f;

		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab(null);
		this.setStepSound(soundTypeGrass);
		this.disableStats();

	}

	public boolean growCrop(World world, int x, int y, int z, Random rand, boolean night) {

		if (world.getBlockLightValue(x, y + 1, z) >= 9 || night) {

			int l = world.getBlockMetadata(x, y, z);

			if (l < 7) {

				++l;
				world.setBlockMetadataWithNotify(x, y, z, l, 3);
				return true;

			}

		}

		return false;

	}

	/**
	 * The type of render function that is called for this block
	 */

	public int getRenderType() {

		return FluxedCrystals.crystalRenderID;

	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {

		this.icons = new IIcon[8];
		this.blockIcon = icon.registerIcon(Reference.LOWERCASE_MOD_ID + ":crop_stage_7");
		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = icon.registerIcon(Reference.LOWERCASE_MOD_ID + ":crop_stage_" + i);
		}

	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta > 7) {

			meta = 7;
		}
		return this.icons[meta];

	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (world.getBlockMetadata(x, y, z) < 0 || world.getBlockMetadata(x, y, z) >= 7) {
			return getIcon(side, 7);
		}
		return getIcon(side, world.getBlockMetadata(x, y, z));
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {

		return world.getBlock(x, y - 1, z) == FCBlocks.poweredSoil;

	}

	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {

		return false;

	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {

		return false;

	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt except
	 * gets checked often with plants.
	 */

	public boolean canBlockStay(World world, int x, int y, int z) {

		return world.getBlock(x, y - 1, z) == FCBlocks.poweredSoil;

	}

	public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {

	}

}
