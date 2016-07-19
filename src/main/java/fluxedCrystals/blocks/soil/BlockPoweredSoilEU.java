package fluxedCrystals.blocks.soil;

import fluxedCrystals.items.Upgrade;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.tileEntity.soil.TileSoilEU;
import fluxedCrystals.tileEntity.soil.TileSoilEUStripped;
import fluxedCrystals.util.IPowerSoil;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;

public class BlockPoweredSoilEU extends Block implements ITileEntityProvider, IPowerSoil
{

	public BlockPoweredSoilEU () {

		super(Material.grass);
		this.setHardness(1.0F);
		this.setHarvestLevel("shovel", 1);
		this.setBlockTextureName(Textures.Blocks.POWEREDSOILEU);
		setStepSound(Block.soundTypeGrass);
	}

	public ArrayList<ItemStack> getDrops (World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}

	@Override
	public TileEntity createNewTileEntity (World p_149915_1_, int p_149915_2_) {
		if(Loader.isModLoaded("IC2")){
		return new TileSoilEU();}else{
			return new TileSoilEUStripped();	
		}
	}

	public void onBlockPreDestroy (World world, int x, int y, int z, int meta) {
		TileSoilEU tile = (TileSoilEU) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null) {
					dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
				}
			}
		}
	}

	public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileSoilEU tile = (TileSoilEU) world.getTileEntity(x, y, z);
		if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof Upgrade) {
			if (tile.addUpgrade(player.inventory.getCurrentItem())) {
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
			}

		}

		if (player.isSneaking() && player.inventory.getCurrentItem() == null) {
			ItemStack stack = tile.removeUpgrade();
			if (stack != null) {
				dropBlockAsItem(world, x, y, z, stack);
			}
		}

		return false;
	}

}
