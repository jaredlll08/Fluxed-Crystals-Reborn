package fluxedCrystals.network.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.CompressionHelper;
import fluxedCrystals.util.JsonTools;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;

public class MessageSyncSeeds implements IMessage, IMessageHandler<MessageSyncSeeds, IMessage> {

	private static HashMap<Integer, Seed> seedMap;

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public MessageSyncSeeds() {

		seedMap = new HashMap<Integer, Seed>();

	}

	public MessageSyncSeeds(HashMap<Integer, Seed> seedMap) {

		MessageSyncSeeds.seedMap = seedMap;

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

			JsonParser parser = new JsonParser();

			JsonObject jsonObject = parser.parse(uncompressedString).getAsJsonObject();

			for (Seed seed : JsonTools.jsontoList(jsonObject)) {

				seedMap.put(seed.seedID, seed);

			}

		}

	}

	@Override
	public void toBytes(ByteBuf buf) {

		byte[] compressedString = null;

		if (seedMap != null) {

			String tmpString = gson.toJson(seedMap);

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
	public IMessage onMessage(MessageSyncSeeds message, MessageContext ctx) {

		if (seedMap != null) {

			for (int i : seedMap.keySet()) {

				SeedRegistry.getInstance().addSeed(seedMap.get(i));

			}

		}

		return null;

	}

}
