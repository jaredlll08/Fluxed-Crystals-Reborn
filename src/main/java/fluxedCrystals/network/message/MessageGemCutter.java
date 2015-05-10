package fluxedCrystals.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageGemCutter implements IMessage, IMessageHandler<MessageGemCutter, IMessage>
{
	public int x, y, z;
	private int data;

	public MessageGemCutter () {
	}

	public MessageGemCutter (int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageGemCutter (int x, int y, int z, int data) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.data = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(data);
	}

	@Override
	public IMessage onMessage(MessageGemCutter message, MessageContext ctx) {
		int x = message.x, y = message.y, z = message.z;

		if (ctx.side.isServer()) {
			TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
			if (te instanceof TileEntityGemCutter) {
				TileEntityGemCutter refiner = (TileEntityGemCutter) te;
				if (refiner.getStackInSlot(0) != null && refiner.getStackInSlot(0).stackSize > 0) {
					refiner.setRefining(true);
				}
				int index = refiner.getRecipeIndex();
				if (index >= 0) {
					return new MessageGemCutter(x, y, z, index);
				}
			}
		} else {
			TileEntity te = FluxedCrystals.proxy.getClientWorld().getTileEntity(x, y, z);
			if (te instanceof TileEntityGemCutter) {
				TileEntityGemCutter refiner = (TileEntityGemCutter) te;
				if (refiner.getStackInSlot(0) != null && refiner.getStackInSlot(0).stackSize > 0) {
					refiner.setRefining(true);{
				    te.getWorldObj().markBlockForUpdate(x, y, z);
				}
				int index = refiner.getRecipeIndex();
				if (index >= 0) 
					return new MessageGemCutter(x, y, z, index);
				}
			}
		}
		return null;
	}
}