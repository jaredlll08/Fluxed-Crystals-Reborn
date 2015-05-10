package fluxedCrystals.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.tileEntity.TileEnergyBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import tterrag.core.common.util.BlockCoord;

public class MessageEnergyStorage implements IMessage, IMessageHandler<MessageEnergyStorage, IMessage>
{

	private int x;
	private int y;
	private int z;
	private int storedEnergy;

	public MessageEnergyStorage() {
	}

	public MessageEnergyStorage(TileEnergyBase ent) {
		BlockCoord bc = new BlockCoord(ent);
		x = bc.x;
		y = bc.y;
		z = bc.z;
		storedEnergy = ent.getEnergyStored();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(storedEnergy);

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		storedEnergy = buf.readInt();
	}

	@Override
	public IMessage onMessage(MessageEnergyStorage message, MessageContext ctx) {
		EntityPlayer player = FluxedCrystals.proxy.getClientPlayer();
		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEnergyBase) {
			TileEnergyBase me = (TileEnergyBase) te;
			me.setEnergyStored(message.storedEnergy);
		}
		return null;
	}

}