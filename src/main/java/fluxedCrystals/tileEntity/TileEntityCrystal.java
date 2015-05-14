package fluxedCrystals.tileEntity;

import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.blocks.crystal.CrystalBase;
import fluxedCrystals.compat.waila.IWailaInfo;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlock;
import fluxedCrystals.util.ITileSoil;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.cbcore.LangUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tterrag.core.common.util.BlockCoord;

import java.util.List;

public class TileEntityCrystal extends TileEntity implements IWailaInfo {

	private int idx = 0;
	private int ticksgrown = 0;
	private boolean harvested = false;
	private ITileSoil power;
	private BlockCrystal crystal;

	public boolean isHarvested() {
		return harvested;
	}

	public void setHarvested(boolean harvested) {
		this.harvested = harvested;
	}

	public int getIdx() {

		return idx;
	}

	public void setIdx(int idx) {

		this.idx = idx;
	}

	public int getTicksgrown() {
		return ticksgrown;
	}

	public void setTicksgrown(int ticksgrown) {
		this.ticksgrown = ticksgrown;
	}

	public ITileSoil getPower() {
		return power;
	}

	public void setPower(ITileSoil power) {
		this.power = power;
	}

	public void updateEntity() {

		if (power == null && worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof ITileSoil)
			power = (ITileSoil) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
		if (SeedRegistry.getInstance().getSeedByID(idx) != null && power != null) {
			ticksgrown++;
			if (ticksgrown > SeedRegistry.getInstance().getSeedByID(idx).growthTime / power.getSpeed()) {
				worldObj.getBlock(xCoord, yCoord, zCoord).updateTick(worldObj, xCoord, yCoord, zCoord, worldObj.rand);
				ticksgrown = 0;
			}

			if (worldObj.getTotalWorldTime() % (worldObj.rand.nextInt(200) + 1) == 0) {
				worldObj.getBlock(xCoord, yCoord, zCoord).updateTick(worldObj, xCoord, yCoord, zCoord, worldObj.rand);
			}
		}

	}

	public boolean growPlant(World world, boolean night) {
		if (world != null) if (world.getBlock(xCoord, yCoord, zCoord) instanceof CrystalBase) {
			return ((CrystalBase) world.getBlock(xCoord, yCoord, zCoord)).growCrop(world, xCoord, yCoord, zCoord, world.rand, night);
		}
		return false;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

	public int getIndex() {

		return idx;
	}

	public void init(int itemDamage) {
		this.idx = itemDamage;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("FCIndex", idx);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		idx = tag.getInteger("FCIndex");
	}

	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldBlock != newBlock;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void getWailaInfo(List<String> tooltip, int x, int y, int z, World world) {
		float growthValue = (world.getBlockMetadata(x, y, z) / 7.0F) * 100.0F;
		if (growthValue < 100.0)
			tooltip.add(String.format("%s : %.0f %%", LangUtil.translateG("hud.msg.growth"), growthValue));
		else
			tooltip.add(String.format("%s : %s", LangUtil.translateG("hud.msg.growth"), LangUtil.translateG("hud.msg.mature")));

	}

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return new ItemStack(FCItems.shardRough, 1, getIndex());
	}

	public TileEntityPowerBlock getPowerTile(World world, BlockCoord coord) {
		if (world.getTileEntity(coord.x, coord.y - 1, coord.z) != null && world.getTileEntity(coord.x, coord.y - 1, coord.z) instanceof TileEntityPowerBlock) {
			return (TileEntityPowerBlock) world.getTileEntity(coord.x, coord.y - 1, coord.z);
		}
		return null;
	}

}
