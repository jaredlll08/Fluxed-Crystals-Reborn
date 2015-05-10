package fluxedCrystals.blocks.crystal;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityPowerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import tterrag.core.common.util.BlockCoord;
import tterrag.core.common.util.TTEntityUtils;

import java.util.Random;

public class BlockCrystal extends CrystalBase implements ITileEntityProvider {

	public BlockCrystal() {
		setHardness(0.05F);
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		TileEntityCrystal crystal = (TileEntityCrystal) world.getTileEntity(x, y, z);
		TileEntityPowerBlock power = (TileEntityPowerBlock) world.getTileEntity(x, y - 1, z);
		int index = crystal.getIdx();
		if (world.getBlockMetadata(x, y, z) < 7) {
			if (crystal != null && power != null) {
				if (crystal.getTicksgrown() >= SeedRegistry.getInstance().getSeedByID(crystal.getIdx()).growthTime / power.getSpeed()) {
					if (power.getEnergyStored() >= power.getUpgradeDrain(index) && growCrop(world, x, y, z, rand, true)) {
						crystal.setTicksgrown(0);
						power.storage.extractEnergy(power.getUpgradeDrain(index), false);
					}
				}
			}
		}
		// if (world.getBlockMetadata(x, y, z) == 7 && power.isUpgradeActive(new
		// ItemStack(FCItems.upgradeAutomation)) && power.getEnergyStored() >=
		// 250) {
		// doDrop(crystal, world, x, y, z, 0, false);
		// world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		// power.storage.extractEnergy(250, false);
		// }
	}

	private void doDrop(TileEntityCrystal crop, World world, int x, int y, int z, int itemMultiplier, boolean seed) {

		if (seed)
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, 1, crop.getIndex()));
		// if (ConfigProps.normalShardRecipes) {
		// dropBlockAsItem(world, x, y, z, new ItemStack(type.smoothShard, (new
		// Random().nextInt(type.getDropMax())+type.getDropMin()) +
		// itemMultiplier));
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shardRough, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + itemMultiplier), crop.getIndex()));
		try {
			if (SeedRegistry.getInstance().getSeedByID(crop.getIdx()).entityID > 0) {
				int id = SeedRegistry.getInstance().getSeedByID(crop.getIdx()).entityID;

				if (id == 48 || id == 49 || id == 1 || id == 8 || id == 9 || id == 21) {

				} else if (id == 22) {
					TTEntityUtils.spawnFirework(new BlockCoord(crop), world.provider.dimensionId);
				} else {
					Entity ent = EntityList.createEntityByID(id, world);
					ent.setLocationAndAngles(x + .5, y + .5, z + .5, 0, 0);
					world.spawnEntityInWorld(ent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (SeedRegistry.getInstance().getSeedByID(crop.getIdx()).getWeightedDrop() != null) {
			if (SeedRegistry.getInstance().getSeedByID(crop.getIdx()).weigthedDropChance == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, SeedRegistry.getInstance().getSeedByID(crop.getIdx()).getWeightedDrop());
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
//			if (stack != null && stack.getItem() instanceof ItemScythe) {
//				if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
//					if (world.rand.nextInt(4) == 0) {
//						dropCropDrops(world, x, y, z, (new Random().nextInt(type.getDropMax())+type.getDropMin()), false);
//					}
//				}
//				if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
//					if (world.rand.nextInt(3) == 0) {
//						dropCropDrops(world, x, y, z, (new Random().nextInt(type.getDropMax())+type.getDropMin()), false);
//					}
//				}
//				if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
//					if (world.rand.nextInt(2) == 0) {
//						dropCropDrops(world, x, y, z, (new Random().nextInt(type.getDropMax())+type.getDropMin()), false);
//					}
//				}
//				if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
//					if (world.rand.nextInt(1) == 0) {
//						dropCropDrops(world, x, y, z,(new Random().nextInt(type.getDropMax())+type.getDropMin()), false);
//					}
//				}
//				if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
//					dropCropDrops(world, x, y, z, (new Random().nextInt(type.getDropMax())+type.getDropMin()), false);
//				}
//			} else {
				dropCropDrops(world, x, y, z, 0, false);
//			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			return true;
		}
		return false;
	}

	@Override
	public int getRenderType() {

		return FluxedCrystals.crystalRenderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getMobilityFlag() {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrystal();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7)
			if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop() != null) {
				if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).weigthedDropChance == world.rand.nextInt(9) + 1) {
					dropBlockAsItem(world, x, y, z, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop());
				}
			}

		if (!crop.isHarvested()) {
			dropCropDrops(world, x, y, z, 0, true);
		}
		world.setBlockToAir(x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		super.onNeighborBlockChange(world, x, y, z, block);
		if (!canBlockStay(world, x, y, z)) {
			onBlockHarvested(world, x, y, z, world.getBlockMetadata(x, y, z), null);
			world.setBlock(x, y, z, Blocks.air);
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		// TODO implement getSeedReturn
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, 1, crop.getIndex()));
		if (world.getBlockMetadata(x, y, z) >= 7) {
			if (player != null && player.getCurrentEquippedItem() != null) {
				ItemStack stack = player.getCurrentEquippedItem();
				// if (stack != null && stack.getItem() instanceof ItemScythe) {
				// if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
				// if (world.rand.nextInt(4) == 0) {
				// dropCropDrops(world, x, y, z, (new
				// Random().nextInt(type.getDropMax())+type.getDropMin()),
				// true);
				// } else {
				// dropCropDrops(world, x, y, z, 0, true);
				// }
				// }
				// if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
				// if (world.rand.nextInt(3) == 0) {
				// dropCropDrops(world, x, y, z, (new
				// Random().nextInt(type.getDropMax())+type.getDropMin()),
				// true);
				// } else {
				// dropCropDrops(world, x, y, z, 0, true);
				// }
				// }
				// if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
				// if (world.rand.nextInt(2) == 0) {
				// dropCropDrops(world, x, y, z, (new
				// Random().nextInt(type.getDropMax())+type.getDropMin()),
				// true);
				// } else {
				// dropCropDrops(world, x, y, z, 0, true);
				// }
				// }
				// if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
				// if (world.rand.nextInt(1) == 0) {
				// dropCropDrops(world, x, y, z, (new
				// Random().nextInt(type.getDropMax())+type.getDropMin()),
				// true);
				// } else {
				// dropCropDrops(world, x, y, z, 0, true);
				// }
				// }
				// if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond)))
				// {
				// dropCropDrops(world, x, y, z, (new
				// Random().nextInt(type.getDropMax())+type.getDropMin()),
				// true);
				// }
				//
				// crop.setHarvested(true);
				// } else {
				dropCropDrops(world, x, y, z, 0, true);
				crop.setHarvested(true);
			}
			// }
		}
	}

	public void dropCropDrops(World world, int x, int y, int z, int fortune, boolean seed) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			doDrop(crop, world, x, y, z, 0, seed);
		}
	}
}
