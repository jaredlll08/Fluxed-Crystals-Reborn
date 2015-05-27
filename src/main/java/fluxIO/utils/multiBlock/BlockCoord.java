package fluxIO.utils.multiBlock;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BlockCoord {

	public Block block;
	public int x;
	public int y;
	public int z;

	public BlockCoord(Block block, int x, int y, int z) {
		this.block = block;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public BlockCoord(TileEntity tile) {
		if (tile != null) {
			this.block = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
			this.x = tile.xCoord;
			this.y = tile.yCoord;
			this.z = tile.zCoord;
		}
	}
	

	public void readFromNBT(NBTTagCompound tag) {
		this.x = tag.getInteger("posX");
		this.y = tag.getInteger("posY");
		this.z = tag.getInteger("posZ");

	}

	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("posX", x);
		tag.setInteger("posY", y);
		tag.setInteger("posZ", z);

	}
}
