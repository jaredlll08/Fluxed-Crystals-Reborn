package fluxIO.blocks.tank;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import fluxIO.blocks.FluxBlocks;
import fluxIO.tileEntity.fluids.TileEntityFluidTank;

public class BlockTank extends Block implements ITileEntityProvider {

	public BlockTank() {
		super(Material.glass);
		setHardness(2f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.setBlock(x, y + 1, z, FluxBlocks.tankFluid);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, player, stack);
		byte rot = 0;
		int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			rot = 2;
		}

		if (l == 1) {
			rot = 3;
		}

		if (l == 2) {
			rot = 4;
		}

		if (l == 3) {
			rot = 1;
		}
		world.setBlockMetadataWithNotify(x, y, z, rot, 3);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if (player.getCurrentEquippedItem() != null) {
				ItemStack current = player.getCurrentEquippedItem();
				TileEntityFluidTank tank = (TileEntityFluidTank) world.getTileEntity(x, y, z);
				FluidStack currentFluid = tank.tank.getFluid();
				int currentFluidAmount = tank.tank.getFluidAmount();
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);

				boolean hasFluid = currentFluid != null;
				if (current != null) {
					if (hasFluid) {
						if (FluidContainerRegistry.isFilledContainer(current)) {
							Fluid containerFluid = FluidContainerRegistry.getFluidForFilledItem(current).getFluid();
							int containerFluidAmount = FluidContainerRegistry.getFluidForFilledItem(current).amount;
							if (32000 - (containerFluidAmount + currentFluidAmount) >= 0) {
								if (currentFluid.getFluid() == containerFluid) {
									tank.tank.setFluid(new FluidStack(containerFluid, currentFluidAmount + containerFluidAmount));
									player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(current));
									tank.markDirty();
								}
							}
						}

						if (FluidContainerRegistry.isEmptyContainer(current)) {
							FluidStack available = tank.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid;

							if (available != null) {
								ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

								liquid = FluidContainerRegistry.getFluidForFilledItem(filled);

								if (liquid != null) {
									if (!player.capabilities.isCreativeMode) {
										if (current.stackSize > 1) {
											if (!player.inventory.addItemStackToInventory(filled)) {
												return false;
											} else {
												player.inventory.setInventorySlotContents(player.inventory.currentItem, consumeItem(current));
											}
										} else {
											player.inventory.setInventorySlotContents(player.inventory.currentItem, consumeItem(current));
											player.inventory.setInventorySlotContents(player.inventory.currentItem, filled);
										}
									}

									tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);

									return true;
								}
							}
						}
					}
					if (!hasFluid) {
						if (FluidContainerRegistry.isFilledContainer(current)) {
							Fluid containerFluid = FluidContainerRegistry.getFluidForFilledItem(current).getFluid();
							int containerFluidAmount = FluidContainerRegistry.getFluidForFilledItem(current).amount;
							if (32000 - (containerFluidAmount + currentFluidAmount) >= 0) {
								tank.tank.setFluid(new FluidStack(containerFluid, currentFluidAmount + containerFluidAmount));
								player.inventory.setInventorySlotContents(player.inventory.currentItem, FluidContainerRegistry.drainFluidContainer(current));
								tank.markDirty();
							}
						}
					}
					player.addChatComponentMessage(new ChatComponentText(hasFluid + ":" + currentFluidAmount));
				}
			}

		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFluidTank();
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		TileEntityFluidTank tank = (TileEntityFluidTank) world.getTileEntity(x, y, z);
		tank.onBlockBreak();
		if (world.getBlock(x, y + 1, z) != null)
			world.setBlockToAir(x, y + 1, z);
		super.onBlockHarvested(world, x, y, z, meta, player);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		TileEntityFluidTank tank = (TileEntityFluidTank) world.getTileEntity(x, y, z);
		tank.onBlockBreak();
		if (world.getBlock(x, y + 1, z) != null)
			world.setBlockToAir(x, y + 1, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	public static ItemStack consumeItem(ItemStack stack) {
		if (stack.stackSize == 1) {
			if (stack.getItem().hasContainerItem(stack)) {
				return stack.getItem().getContainerItem(stack);
			} else {
				return null;
			}
		} else {
			stack.splitStack(1);

			return stack;
		}
	}

}
