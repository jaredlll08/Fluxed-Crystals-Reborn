package fluxedCrystals.blocks.crystal;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.items.ItemScythe;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockLP;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockMana;
import fluxedCrystals.util.DamageSourceCrystal;
import fluxedCrystals.util.IPowerSoil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
			if (!world.isRemote)
				if (entity instanceof EntityPlayer) entity.attackEntityFrom(new DamageSourceCrystal(), 1.0f);
		}
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		TileEntityCrystal crystal = (TileEntityCrystal) world.getTileEntity(x, y, z);
		int index = crystal.getIdx();
		if (world.getBlockMetadata(x, y, z) < 7) {
			if (world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof TileEntityPowerBlock) {
				TileEntityPowerBlock power = (TileEntityPowerBlock) world.getTileEntity(x, y - 1, z);

				if (crystal != null && power != null) {
					if (crystal.getTicksgrown() >= SeedRegistry.getInstance().getSeedByID(crystal.getIdx()).growthTime / power.getSpeed()) {
						if (power.getEnergyStored() >= power.getUpgradeDrain(index) && growCrop(world, x, y, z, rand, true)) {
							crystal.setTicksgrown(0);
							power.storage.extractEnergy(power.getUpgradeDrain(index), false);
						}
					}
				}
				if (world.getBlockMetadata(x, y, z) == 7 && power.isUpgradeActive(FCItems.upgradeAutomation) && power.getEnergyStored() >= 250) {
					doDrop(crystal, world, x, y, z, 0, false);
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
					power.storage.extractEnergy(250, false);
				}
			}
			if (world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof TileEntityPowerBlockMana) {
				TileEntityPowerBlockMana power = (TileEntityPowerBlockMana) world.getTileEntity(x, y - 1, z);

				if (crystal != null && power != null) {
					if (crystal.getTicksgrown() >= SeedRegistry.getInstance().getSeedByID(crystal.getIdx()).growthTime / power.getSpeed()) {
						if (power.getCurrentMana() >= power.getUpgradeDrain(index) && growCrop(world, x, y, z, rand, true)) {
							crystal.setTicksgrown(0);
							power.recieveMana(-power.getUpgradeDrain(index));
						}
					}
				}
				if (world.getBlockMetadata(x, y, z) == 7 && power.isUpgradeActive(FCItems.upgradeAutomation) && power.getCurrentMana() >= 250) {
					doDrop(crystal, world, x, y, z, 0, false);
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
					power.recieveMana(-250);
				}
			}

			if (world.getTileEntity(x, y - 1, z) != null && world.getTileEntity(x, y - 1, z) instanceof TileEntityPowerBlockLP) {
				TileEntityPowerBlockLP power = (TileEntityPowerBlockLP) world.getTileEntity(x, y - 1, z);

				if (crystal != null && power != null) {
					if (crystal.getTicksgrown() >= SeedRegistry.getInstance().getSeedByID(crystal.getIdx()).growthTime / power.getSpeed()) {
						if (power.drainEnergy(power.getUpgradeDrain(index)/4) && growCrop(world, x, y, z, rand, true)) {
							crystal.setTicksgrown(0);
						}
					}
				}
				if (world.getBlockMetadata(x, y, z) == 7 && power.isUpgradeActive(FCItems.upgradeAutomation) && power.drainEnergy(250)) {
					doDrop(crystal, world, x, y, z, 0, false);
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				}
			}
		}
	}

	private void doDrop(TileEntityCrystal crop, World world, int x, int y, int z, int itemMultiplier, boolean seed) {
		if (seed)
			dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).seedReturn, crop.getIndex()));
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
		if (world.getBlockMetadata(x, y, z) >= 7) {
			ItemStack stack = player.getCurrentEquippedItem();
			if (stack != null && stack.getItem() instanceof ItemScythe) {
				if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {
					if (world.rand.nextInt(4) == 0) {
						dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {
					if (world.rand.nextInt(3) == 0) {
						dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {
					if (world.rand.nextInt(2) == 0) {
						dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {
					if (world.rand.nextInt(1) == 0) {
						dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), false);
					}
				}
				if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {
					dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), false);
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
		if (world.getBlockMetadata(x, y, z) >= 7)
			if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop() != null) {
				if (SeedRegistry.getInstance().getSeedByID(crop.getIndex()).weigthedDropChance == world.rand.nextInt(9) + 1) {
					dropBlockAsItem(world, x, y, z, SeedRegistry.getInstance().getSeedByID(crop.getIndex()).getWeightedDrop());
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

		// dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.seed,
		// SeedRegistry.getInstance().getSeedByID(crop.getIdx()).seedReturn,
		// crop.getIndex()));

		if (world.getBlockMetadata(x, y, z) >= 7) {

			if (player != null && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemScythe) {

				ItemStack stack = player.getCurrentEquippedItem();

				if (stack != null) {

					if (stack.isItemEqual(new ItemStack(FCItems.scytheWood))) {

						if (world.rand.nextInt(4) == 0) {

							dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), true);
							crop.setHarvested(true);
							return;
						} else {

							dropCropDrops(world, x, y, z, 0, true);
							crop.setHarvested(true);
							return;
						}

					}

					if (stack.isItemEqual(new ItemStack(FCItems.scytheStone))) {

						if (world.rand.nextInt(3) == 0) {

							dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), true);
							crop.setHarvested(true);
							return;
						} else {

							dropCropDrops(world, x, y, z, 0, true);
							crop.setHarvested(true);
							return;
						}
					}

					if (stack.isItemEqual(new ItemStack(FCItems.scytheIron))) {

						if (world.rand.nextInt(2) == 0) {

							dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), true);
							crop.setHarvested(true);
							return;
						} else {

							dropCropDrops(world, x, y, z, 0, true);
							crop.setHarvested(true);
							return;
						}

					}

					if (stack.isItemEqual(new ItemStack(FCItems.scytheGold))) {

						if (world.rand.nextInt(1) == 0) {

							dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), true);
							crop.setHarvested(true);
							return;
						} else {

							dropCropDrops(world, x, y, z, 0, true);
							crop.setHarvested(true);
							return;
						}

					}

					if (stack.isItemEqual(new ItemStack(FCItems.scytheDiamond))) {

						dropCropDrops(world, x, y, z, (new Random().nextInt(SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMax) + SeedRegistry.getInstance().getSeedByID(crop.getIdx()).dropMin), true);
						crop.setHarvested(true);
						return;
					}

					crop.setHarvested(true);

				}

			} else {

				dropCropDrops(world, x, y, z, 0, true);
				crop.setHarvested(true);

			}
		}

	}

	public void dropCropDrops(World world, int x, int y, int z, int fortune, boolean seed) {
		TileEntityCrystal crop = (TileEntityCrystal) world.getTileEntity(x, y, z);
		if (world.getBlockMetadata(x, y, z) >= 7) {
			doDrop(crop, world, x, y, z, 0, seed);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z) {
		return world.getBlock(x, y - 1, z) instanceof IPowerSoil;
	}

}
