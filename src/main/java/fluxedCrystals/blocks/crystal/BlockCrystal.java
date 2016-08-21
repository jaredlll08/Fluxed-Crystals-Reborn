package fluxedCrystals.blocks.crystal;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.items.tools.ItemScythe;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.soil.*;
import fluxedCrystals.util.DamageSourceCrystal;
import fluxedCrystals.util.IPowerSoil;
import fluxedCrystals.util.ITileSoil;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.*;

public class BlockCrystal extends CrystalBase implements ITileEntityProvider, IWailaInfo {

	public BlockCrystal() {
		setHardness(0.05F);
		setTickRandomly(true);
		setBlockTextureName(Textures.Blocks.CRYSTAL);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);

		if (SeedRegistry.getInstance().getSeedByID(crop.getIdx()).isSharp) {
			if (!world.isRemote) {
				if (entity instanceof EntityPlayer) {
					entity.attackEntityFrom(new DamageSourceCrystal(), 1.0f);
				}
			}
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		
		if (!world.isRemote) {
			TileEntityCrystal crystal = (TileEntityCrystal) world.getTileEntity(x, y, z);
			int index = crystal.getIdx();
				if (world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof ITileSoil) {
					ITileSoil soil = (ITileSoil) world.getTileEntity(x, y - 1, z);
					if (crystal != null && soil != null) {
						if (world.getBlockMetadata(x, y, z) < 7) {
						if (crystal.getTicksgrown() >= SeedRegistry.getInstance().getSeedByID(crystal.getIdx()).growthTime / soil.getSpeed()) {
							if (soil.getStoredEnergy() >= soil.getUpgradeDrain(index) && growCrop(world, x, y, z, rand, true)) {
								crystal.setTicksgrown(0);
								soil.drainEnergy(soil.getUpgradeDrain(index));
							}
						}
						}
						if (world.getBlockMetadata(x, y, z) == 7 && soil.isUpgradeActive(FCItems.upgradeAutomation)) {
							doDrop(crystal, world, x, y, z, 0, false);
							world.setBlockMetadataWithNotify(x, y, z, 0, 3);
							//soil.drainEnergy(250);
						}
					}
				}
			//
		}
	}

	private void doDrop(TileEntityCrystal crop, World world, int x, int y, int z, int itemMultiplier, boolean seed) {
		if (seed) {
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).seedReturn, crop.getIndex()));
		}
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.shardRough, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIndex()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIndex()).dropMin) + itemMultiplier, crop.getIndex()));
		if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop() != null) {
			if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).weigthedDropChance == world.rand.nextInt(9) + 1) {
				dropBlockAsItem(world, x, y, z, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop());
			}
		}
	}

	public void setData(ItemStack seed, IBlockAccess world, int x, int y, int z) {
		TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(x, y + 1, z);
		if (tile != null) {
			tile.init(seed.getItemDamage());
		}
	}

	@Override
	public void getWailaInfo(List<String> tooltip, int x, int y, int z, World world) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof IWailaInfo) {
			((IWailaInfo) te).getWailaInfo(tooltip, x, y, z, world);
		}
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity te = accessor.getTileEntity();
		return ((IWailaInfo) te).getWailaStack(accessor, config);
	}

	public Item getItem(World world, int x, int y, int z) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);

		return new ItemStack(FCItems.seed, 1, crop.getIndex()).getItem();
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		Seed seed = SeedRegistry.getInstance().getSeedByID(crop.getIndex());
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack != null && stack.getItem() instanceof ItemScythe) {
				if (stack.getItem() == FCItems.scytheWood) {
					if (world.rand.nextInt(4) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
					stack.damageItem(1, player);
				}
				if (stack.getItem() == FCItems.scytheStone) {
					if (world.rand.nextInt(3) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
					stack.damageItem(1, player);
				}
				if (stack.getItem() == FCItems.scytheIron) {
					if (world.rand.nextInt(2) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
					stack.damageItem(1, player);
				}
				if (stack.getItem() == FCItems.scytheGold) {
					if (world.rand.nextInt(1) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
					stack.damageItem(1, player);
				}
				if (stack.getItem() == FCItems.scytheDiamond) {
					dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					stack.damageItem(1, player);
				}
			} else {
				dropCropDrops(world, x, y, z, 0, false);
			}
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
		if (world.getBlockMetadata(x, y, z) >= 7) {
			if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop() != null) {
				if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).weigthedDropChance == world.rand.nextInt(9) + 1) {
					dropBlockAsItem(world, x, y, z, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop());
				}
			}
		}

		// if (!crop.isHarvested()) {
		// dropCropDrops(world, x, y, z, 0, false);
		// }
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
		Seed seed = SeedRegistry.getInstance().getSeedByID(crop.getIndex());
		dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, seed.seedReturn, seed.seedID));
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack != null && stack.getItem() instanceof ItemScythe) {
				if (stack.getItem() == FCItems.scytheWood) {
					if (world.rand.nextInt(4) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
				}
				if (stack.getItem() == FCItems.scytheStone) {
					if (world.rand.nextInt(3) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
				}
				if (stack.getItem() == FCItems.scytheIron) {
					if (world.rand.nextInt(2) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
				}
				if (stack.getItem() == FCItems.scytheGold) {
					if (world.rand.nextInt(1) == 0) {
						dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
					} else {
						dropCropDrops(world, x, y, z, 0, false);
					}
				}
				if (stack.getItem() == FCItems.scytheDiamond) {
					dropCropDrops(world, x, y, z, seed.getDropAmount(), false);
				}
			} else {
				dropCropDrops(world, x, y, z, 0, false);
			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		}
	}

	private void dropCropDrops(World world, int x, int y, int z, int fortune, boolean seed) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			doDrop(crop, world, x, y, z, 0, seed);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) instanceof IPowerSoil;
	}

}
