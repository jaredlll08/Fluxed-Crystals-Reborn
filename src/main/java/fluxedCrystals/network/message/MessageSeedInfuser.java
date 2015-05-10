package fluxedCrystals.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageSeedInfuser implements IMessage, IMessageHandler<MessageSeedInfuser, IMessage>
{
	public int x, y, z;
	private int data;

	public MessageSeedInfuser () {
	}

	public MessageSeedInfuser (int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public MessageSeedInfuser (int x, int y, int z, int data) {
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
	public IMessage onMessage(MessageSeedInfuser message, MessageContext ctx) {
		int x = message.x, y = message.y, z = message.z;
		if (ctx.side.isServer()) {
			TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
			if (te instanceof TileEntitySeedInfuser) {
				TileEntitySeedInfuser infuser = (TileEntitySeedInfuser) te;
				if (infuser.getStackInSlot(1) != null && infuser.getStackInSlot(1).stackSize > 0) {
					infuser.setInfusing(true);
				}
				int index = infuser.getRecipeIndex();
				if (index != -1) {
					return new MessageSeedInfuser(x, y, z, index);
				}
			}
		} else {
			TileEntity te = FluxedCrystals.proxy.getClientWorld().getTileEntity(x, y, z);
			if (te instanceof TileEntitySeedInfuser) {
				((TileEntitySeedInfuser) te).setRecipeIndex(message.data);
				te.getWorldObj().markBlockForUpdate(x, y, z);
			}
		}
		return null;
	}
}