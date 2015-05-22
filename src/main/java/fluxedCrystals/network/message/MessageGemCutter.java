package fluxedCrystals.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageGemCutter implements IMessage, IMessageHandler<MessageGemCutter, IMessage>
{

	private int x;
	private int y;
	private int z;
	private byte state;
	private int needCycleTime;
	private int itemCycleTime;
	private int deviceCycleTime;

	public MessageGemCutter () {

	}

	public MessageGemCutter (TileEntityGemCutter tileEntityGemCutter) {

		this.x = tileEntityGemCutter.xCoord;
		this.y = tileEntityGemCutter.yCoord;
		this.z = tileEntityGemCutter.zCoord;

		this.state = tileEntityGemCutter.state;

		this.needCycleTime = tileEntityGemCutter.needCycleTime;
		this.itemCycleTime = tileEntityGemCutter.itemCycleTime;
		this.deviceCycleTime = tileEntityGemCutter.deviceCycleTime;

	}

	@Override
	public void fromBytes (ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.state = buf.readByte();

		this.needCycleTime = buf.readInt();
		this.itemCycleTime = buf.readInt();
		this.deviceCycleTime = buf.readInt();

	}

	@Override
	public void toBytes (ByteBuf buf) {

		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		buf.writeByte(this.state);

		buf.writeInt(this.needCycleTime);
		buf.writeInt(this.itemCycleTime);
		buf.writeInt(this.deviceCycleTime);

	}

	@Override
	public IMessage onMessage (MessageGemCutter message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityGemCutter) {

			((TileEntityGemCutter) tileEntity).state = message.state;
			((TileEntityGemCutter) tileEntity).needCycleTime = message.needCycleTime;
			((TileEntityGemCutter) tileEntity).itemCycleTime = message.itemCycleTime;
			((TileEntityGemCutter) tileEntity).deviceCycleTime = message.deviceCycleTime;

		}

		return null;

	}

}
