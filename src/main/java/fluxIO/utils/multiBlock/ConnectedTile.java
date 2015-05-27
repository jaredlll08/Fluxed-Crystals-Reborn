package fluxIO.utils.multiBlock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ConnectedTile extends TileEntity {

	public BlockCoord block;
	public BlockCoord masterBlock;

	public ConnectedTile() {
	}

	public ConnectedTile(BlockCoord block, BlockCoord masterBlock) {
		this.block = block;
		this.masterBlock = masterBlock;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		block.writeToNBT(tag);
		masterBlock.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		block.readFromNBT(tag);
		masterBlock.readFromNBT(tag);
	}

	public BlockCoord getBlock() {
		return block;
	}

	public void setBlock(BlockCoord block) {
		this.block = block;
	}

	public BlockCoord getMasterBlock() {
		return masterBlock;
	}

	public void setBaseblock(BlockCoord masterBlock) {
		this.masterBlock = masterBlock;
	}

}
