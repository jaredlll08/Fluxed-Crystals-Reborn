package fluxIO.network;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxIO.api.generators.GeneratorBase;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageGenerator implements IMessage, IMessageHandler<MessageGenerator, IMessage> {

	private int x;
	private int y;
	private int z;
	private int generationTimer;
	private int generationTimerDefault;
	private int energy;
	public MessageGenerator() {

	}

	public MessageGenerator(GeneratorBase tile) {

		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.generationTimer = tile.generationTimer;
		this.generationTimerDefault = tile.generationTimerDefault;
		this.energy = tile.getEnergyStored();
	}	

	@Override
	public void fromBytes(ByteBuf buf) {

		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.generationTimer = buf.readInt();
		this.generationTimerDefault = buf.readInt();
		this.energy = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		buf.writeInt(generationTimer);
		buf.writeInt(generationTimerDefault);
		buf.writeInt(energy);

	}

	@Override
	public IMessage onMessage(MessageGenerator message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof GeneratorBase) {

			((GeneratorBase) tileEntity).generationTimer = message.generationTimer;
			((GeneratorBase) tileEntity).generationTimerDefault = message.generationTimerDefault;
			((GeneratorBase) tileEntity).setEnergyStored(message.energy);

		}

		return null;

	}

}
