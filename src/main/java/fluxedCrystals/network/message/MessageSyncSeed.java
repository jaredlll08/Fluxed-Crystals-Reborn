package fluxedCrystals.network.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.CompressionHelper;
import io.netty.buffer.ByteBuf;

public class MessageSyncSeed implements IMessage, IMessageHandler<MessageSyncSeed, IMessage> {

	private Seed seed;

	public MessageSyncSeed() {

	}

	public MessageSyncSeed(Seed seed) {

		this.seed = seed;

	}

	@Override
	public void fromBytes(ByteBuf buf) {

		byte[] compressedString = null;

		int readableBytes = buf.readInt();

		if (readableBytes > 0) {

			compressedString = buf.readBytes(readableBytes).array();

		}

		if (compressedString != null) {

			String uncompressedString = CompressionHelper.decompressStringFromByteArray(compressedString);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			this.seed = gson.fromJson(uncompressedString, Seed.class);

		}

	}

	@Override
	public void toBytes(ByteBuf buf) {

		byte[] compressedString = null;

		if (seed != null) {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String tmpString = gson.toJson(seed);

			compressedString = CompressionHelper.compressStringToByteArray(tmpString);

		}

		if (compressedString != null) {

			buf.writeInt(compressedString.length);
			buf.writeBytes(compressedString);

		} else {

			buf.writeInt(0);

		}

	}

	@Override
	public IMessage onMessage(MessageSyncSeed message, MessageContext ctx) {

		if (message.seed != null) {

			SeedRegistry.getInstance().addSeed(message.seed);

		}

		return null;

	}

}
