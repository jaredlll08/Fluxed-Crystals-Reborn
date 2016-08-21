package fluxedCrystals.blocks.soil;

import fluxedCrystals.items.Upgrade;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlock;
import fluxedCrystals.util.IPowerSoil;
import fluxedCrystals.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

public class BlockPowerBlock extends Block implements ITileEntityProvider, IPowerSoil 
{
	public boolean isWorking = false;

	
	@SideOnly(Side.CLIENT)
	public static IIcon OffIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon OnIcon;

		
		
	

	@Override
	public void registerBlockIcons(IIconRegister register) {
	    super.registerBlockIcons(register);
	    OffIcon = register.registerIcon(Textures.Blocks.POWERED_SOIL_OFF);
	    OnIcon = register.registerIcon(Textures.Blocks.POWERED_SOIL_ON);

	}
	@Override
	public IIcon getIcon(int side, int meta) {//meta dont matter
		if(this.isWorking) {
	    	return OnIcon;
	    }else{
	        return OffIcon;

	    }
	  }
	
	public BlockPowerBlock () {
		
		
		super(Material.grass);
		this.setHardness(1.0F);
		this.setHarvestLevel("shovel", 1);
		this.setBlockTextureName(Textures.Blocks.POWERED_SOIL_OFF); //This is how the texture is currently set...
		setStepSound(Block.soundTypeGrass); //also shouldnt this be dirt?
	}

	public ArrayList<ItemStack> getDrops (World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(this));
		return stack;
	}

	@Override
	public TileEntity createNewTileEntity (World p_149915_1_, int p_149915_2_) {
		return new TileEntityPowerBlock();
	}

	public void onBlockPreDestroy (World world, int x, int y, int z, int meta) {
		TileEntityPowerBlock tile = (TileEntityPowerBlock) world.getTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				if (tile.getStackInSlot(i) != null) {
					dropBlockAsItem(world, x, y, z, tile.getStackInSlot(i));
				}
			}
		}
	}

	public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		TileEntityPowerBlock tile = (TileEntityPowerBlock) world.getTileEntity(x, y, z);
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
