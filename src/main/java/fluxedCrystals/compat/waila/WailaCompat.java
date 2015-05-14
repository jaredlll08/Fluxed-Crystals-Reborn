package fluxedCrystals.compat.waila;

import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

public class WailaCompat implements IWailaDataProvider {
	public static final WailaCompat INSTANCE = new WailaCompat();

	public static void load(IWailaRegistrar registrar) {
		registrar.registerStackProvider(INSTANCE, BlockCrystal.class);
		registrar.registerBodyProvider(INSTANCE, BlockCrystal.class);
	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		Block block = accessor.getBlock();
		TileEntityCrystal crystal = (TileEntityCrystal) accessor.getTileEntity();

		return new ItemStack(FCItems.seed, 1, crystal.getIndex());

	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		TileEntity te = accessor.getTileEntity();
		Block block = accessor.getBlock();

		if (te == null) {
			return currenttip;
		}

		if (block instanceof IWailaInfo) {
			MovingObjectPosition pos = accessor.getPosition();
			((IWailaInfo) block).getWailaInfo(currenttip, pos.blockX, pos.blockY, pos.blockZ, accessor.getWorld());
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
		return tag;
	}

}
