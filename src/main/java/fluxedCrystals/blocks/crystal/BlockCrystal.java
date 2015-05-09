package fluxedCrystals.blocks.crystal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.book.registries.RecipeRegistry;
import fluxedCrystals.api.CrystalBase;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;

public class BlockCrystal extends CrystalBase implements ITileEntityProvider
{

	public BlockCrystal()
	{
		setHardness(0.05F);
		setTickRandomly(true);

	}

//	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
//	{
//
//		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
//		if (RecipeRegistry.(crop.getIndex()))
//		{
//
//			if (!world.isRemote && world.getWorldTime() % 5 == 0)
//			{
//
//				if (entity instanceof EntityPlayer)
//				{
//
//					((EntityPlayer) entity).attackEntityFrom(new DamageSourceCrystal(), world.getBlockMetadata(x, y, z));
//
//				}
//
//			}
//
//		}
//
//	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrystal();
	}

}
