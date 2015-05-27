package fluxIO.network;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageCoalGenerator implements IMessage, IMessageHandler<MessageCoalGenerator, IMessage> {

	private int x;
	private int y;
	private int z;
	private int generationTimer;
	private int generationTimerDefault;

	public MessageCoalGenerator() {

	}

	public MessageCoalGenerator(TileEntityCoalGenerator tile) {

		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.generationTimer = tile.generationTimer;
		this.generationTimerDefault = tile.generationTimerDefault;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.generationTimer = buf.readInt();
		this.generationTimerDefault = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		buf.writeInt(generationTimer);
		buf.writeInt(generationTimerDefault);

	}

	@Override
	public IMessage onMessage(MessageCoalGenerator message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityCoalGenerator) {

			((TileEntityCoalGenerator) tileEntity).generationTimer = message.generationTimer;
			((TileEntityCoalGenerator) tileEntity).generationTimerDefault = message.generationTimerDefault;

		}

		return null;

	}

}
