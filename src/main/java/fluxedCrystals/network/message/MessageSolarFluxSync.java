package fluxedCrystals.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.util.ClientUtils;
import io.netty.buffer.ByteBuf;

public class MessageSolarFluxSync implements IMessage, IMessageHandler<MessageSolarFluxSync, IMessage> {
	public int x, y, z;
	public int stored;

	public MessageSolarFluxSync () {
	}

	public MessageSolarFluxSync (int x, int y, int z, int stored) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.stored = stored;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.stored = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(stored);
	}

	@Override
	public IMessage onMessage(MessageSolarFluxSync message, MessageContext ctx) {
		//ClientUtils.updateSolarflux(message);
		return null;
	}
}