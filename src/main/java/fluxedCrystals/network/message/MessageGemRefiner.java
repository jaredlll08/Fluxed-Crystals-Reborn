package fluxedCrystals.network.message;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageGemRefiner implements IMessage, IMessageHandler<MessageGemRefiner, IMessage> {

	private int x;
	private int y;
	private int z;
	private byte state;
	private int needCycleTime;
	private int itemCycleTime;
	private int deviceCycleTime;

	public MessageGemRefiner() {

	}

	public MessageGemRefiner(TileEntityGemRefiner tileEntityGemRefiner) {

		this.x = tileEntityGemRefiner.xCoord;
		this.y = tileEntityGemRefiner.yCoord;
		this.z = tileEntityGemRefiner.zCoord;

		this.state = tileEntityGemRefiner.state;

		this.needCycleTime = tileEntityGemRefiner.needCycleTime;
		this.itemCycleTime = tileEntityGemRefiner.itemCycleTime;
		this.deviceCycleTime = tileEntityGemRefiner.deviceCycleTime;

	}

	@Override
	public void fromBytes(ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.state = buf.readByte();

		this.needCycleTime = buf.readInt();
		this.itemCycleTime = buf.readInt();
		this.deviceCycleTime = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		buf.writeByte(this.state);

		buf.writeInt(this.needCycleTime);
		buf.writeInt(this.itemCycleTime);
		buf.writeInt(this.deviceCycleTime);

	}

	@Override
	public IMessage onMessage(MessageGemRefiner message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityGemRefiner) {

			((TileEntityGemRefiner) tileEntity).state = message.state;
			((TileEntityGemRefiner) tileEntity).needCycleTime = message.needCycleTime;
			((TileEntityGemRefiner) tileEntity).itemCycleTime = message.itemCycleTime;
			((TileEntityGemRefiner) tileEntity).deviceCycleTime = message.deviceCycleTime;

		}

		return null;

	}

}
