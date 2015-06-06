package fluxIO.blocks.tank;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import fluxIO.blocks.FluxBlocks;

public class BlockTankFluid extends Block implements ITileEntityProvider {

	public BlockTankFluid() {
		super(Material.glass);
		setHardness(2f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		return world.getBlock(x, y - 1, z).onBlockActivated(world, x, y - 1, z, player, meta, hitX, hitY, hitZ);
	}

	@Override
	public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
		return false;
	}

	@Override
	public int getMixedBrightnessForBlock(IBlockAccess p_149677_1_, int p_149677_2_, int p_149677_3_, int p_149677_4_) {
		return 1;
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
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(FluxBlocks.tank);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		super.onBlockHarvested(world, x, y - 1, z, meta, player);
		world.getBlock(x, y - 1, z).onBlockHarvested(world, x, y - 1, z, meta, player);
		if (!player.capabilities.isCreativeMode)
			world.getBlock(x, y - 1, z).dropBlockAsItem(world, x, y - 1, z, 0, 0);
		world.setBlockToAir(x, y - 1, z);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTopTank();
	}

	public class TileEntityTopTank extends TileEntity implements IFluidHandler {

		@Override
		public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).fill(from, resource, doFill);
		}

		@Override
		public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).drain(from, resource, doDrain);
		}

		@Override
		public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).drain(from, maxDrain, doDrain);
		}

		@Override
		public boolean canFill(ForgeDirection from, Fluid fluid) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).canFill(from, fluid);
		}

		@Override
		public boolean canDrain(ForgeDirection from, Fluid fluid) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).canDrain(from, fluid);
		}

		@Override
		public FluidTankInfo[] getTankInfo(ForgeDirection from) {
			return ((IFluidHandler) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).getTankInfo(from);
		}

	}
}
