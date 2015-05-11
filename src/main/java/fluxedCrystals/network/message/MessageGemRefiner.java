package fluxedCrystals.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageGemRefiner implements IMessage, IMessageHandler<MessageGemRefiner, IMessage>
{
	public int x, y, z;
	private int data;

	public MessageGemRefiner () {
	}

	public MessageGemRefiner (int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageGemRefiner (int x, int y, int z, int data) {
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
	public IMessage onMessage(MessageGemRefiner message, MessageContext ctx) {
		int x = message.x, y = message.y, z = message.z;

		if (ctx.side.isServer()) {
			TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
			if (te instanceof TileEntityGemRefiner) {
				TileEntityGemRefiner refiner = (TileEntityGemRefiner) te;
				if (refiner.getStackInSlot(0) != null && refiner.getStackInSlot(0).stackSize > 0) {
					refiner.setRefining(true);
				}
				int index = refiner.getRecipeIndex();
				if (index >= 0) {
					return new MessageGemRefiner(x, y, z, index);
				}
			}
		} else {
			TileEntity te = FluxedCrystals.proxy.getClientWorld().getTileEntity(x, y, z);
			if (te instanceof TileEntityGemRefiner) {
				TileEntityGemRefiner refiner = (TileEntityGemRefiner) te;
				if (refiner.getStackInSlot(0) != null && refiner.getStackInSlot(0).stackSize > 0) {
					refiner.setRefining(true);
				    te.getWorldObj().markBlockForUpdate(x, y, z);
				}
				int index = refiner.getRecipeIndex();
				if (index >= 0) {
					return new MessageGemRefiner(x, y, z, index);
				}
			}
		}
		return null;
	}
}