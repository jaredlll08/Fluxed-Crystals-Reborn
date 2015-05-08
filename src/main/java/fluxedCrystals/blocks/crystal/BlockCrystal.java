package fluxedCrystals.blocks.crystal;

import fluxedCrystals.api.CrystalBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockCrystal extends CrystalBase implements ITileEntityProvider, IWailaInfo
{

	public BlockCrystal()
	{

		setHardness(0.05F);
		setTickRandomly(true);

	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{

		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);

		if (RecipeRegistry.getIsSharp(crop.getIndex()))
		{

			if (!world.isRemote && world.getWorldTime() % 5 == 0)
			{

				if (entity instanceof EntityPlayer)
				{

					((EntityPlayer) entity).attackEntityFrom(new DamageSourceCrystal(), world.getBlockMetadata(x, y, z));

				}

			}

		}

	}

}
