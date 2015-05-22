package fluxedCrystals.network.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxedCrystals.registry.Mutation;
import fluxedCrystals.registry.MutationRegistry;
import fluxedCrystals.util.CompressionHelper;
import io.netty.buffer.ByteBuf;

public class MessageSyncMutation implements IMessage, IMessageHandler<MessageSyncMutation, IMessage>
{

	private Mutation mutation;

	public MessageSyncMutation () {

	}

	public MessageSyncMutation (Mutation mutation) {

		this.mutation = mutation;

	}

	@Override
	public void fromBytes (ByteBuf buf) {

		byte[] compressedString = null;

		int readableBytes = buf.readInt();

		if (readableBytes > 0) {

			compressedString = buf.readBytes(readableBytes).array();

		}

		if (compressedString != null) {

			String uncompressedString = CompressionHelper.decompressStringFromByteArray(compressedString);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			this.mutation = gson.fromJson(uncompressedString, Mutation.class);

		}

	}

	@Override
	public void toBytes (ByteBuf buf) {

		byte[] compressedString = null;

		if (mutation != null) {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			String tmpString = gson.toJson(mutation);

			compressedString = CompressionHelper.compressStringToByteArray(tmpString);

		}

		if (compressedString != null) {

			buf.writeInt(compressedString.length);
			buf.writeBytes(compressedString);

		}
		else {

			buf.writeInt(0);

		}

	}

	@Override
	public IMessage onMessage (MessageSyncMutation message, MessageContext ctx) {

		if (message.mutation != null) {

			MutationRegistry.getInstance().addMutation(message.mutation.outputSeed, message.mutation.seed1, message.mutation.seed2);

		}

		return null;

	}

}
