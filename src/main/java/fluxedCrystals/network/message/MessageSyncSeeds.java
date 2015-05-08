package fluxedCrystals.network.message;


import fluxedCrystals.registry.SeedData;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.CompressionHelper;
import fluxedCrystals.util.LogHelper;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncSeeds implements IMessage, IMessageHandler<MessageSyncSeeds, IMessage>
{

	private SeedData seedData;

	public MessageSyncSeeds()
	{

	}

	public MessageSyncSeeds(SeedData seedData)
	{

		this.seedData = seedData;

	}

	@Override
	public void fromBytes(ByteBuf buf)
	{

		byte[] compressedString = null;

		int readableBytes = buf.readInt();

		if (readableBytes > 0)
		{

			compressedString = buf.readBytes(readableBytes).array();

		}

		if (compressedString != null)
		{

			String uncompressedString = CompressionHelper.decompressStringFromByteArray(compressedString);

			this.seedData = SeedData.createFromJson(uncompressedString);

		}

	}

	@Override
	public void toBytes(ByteBuf buf)
	{

		byte[] compressedString = null;

		if (seedData != null)
		{

			compressedString = CompressionHelper.compressStringToByteArray(seedData.toJson());

		}

		if (compressedString != null)
		{

			buf.writeInt(compressedString.length);
			buf.writeBytes(compressedString);

		}
		else
		{

			buf.writeInt(0);

		}

	}

	@Override
	public IMessage onMessage(MessageSyncSeeds message, MessageContext ctx)
	{

		if (message.seedData != null)
		{

			SeedRegistry.getInstance().setSeedByID(message.seedData.getEntityID(), message.seedData, false);

			LogHelper.info("Client successfully received seedData for:  [" + message.seedData.getEntityID() + "]  " + message.seedData.getName());

		}
		else
		{

			LogHelper.info("Client failed to receive seedData from server");

		}

		return null;

	}

}
