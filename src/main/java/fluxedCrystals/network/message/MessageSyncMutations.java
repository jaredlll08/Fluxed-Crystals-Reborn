package fluxedCrystals.network.message;

import com.google.gson.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import fluxedCrystals.registry.*;
import fluxedCrystals.util.CompressionHelper;
import fluxedCrystals.util.JsonTools;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.HashMap;
import java.util.Map;

public class MessageSyncMutations implements IMessage, IMessageHandler<MessageSyncMutations, IMessage>
{

	private static Map<MutablePair<Seed, Seed>, Mutation> mutationMap;

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public MessageSyncMutations () {

		mutationMap = new HashMap<MutablePair<Seed, Seed>, Mutation>();

	}

	public MessageSyncMutations (Map<MutablePair<Seed, Seed>, Mutation> mutationMap) {

		MessageSyncMutations.mutationMap = mutationMap;

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

			JsonParser parser = new JsonParser();

			JsonObject jsonObject = parser.parse(uncompressedString).getAsJsonObject();

			for (Mutation mutation : JsonTools.jsontoList_mutations(jsonObject)) {

				mutationMap.put(new MutablePair<Seed, Seed>(SeedRegistry.getInstance().getSeedFromName(mutation.seed1), SeedRegistry.getInstance().getSeedFromName(mutation.seed2)), new Mutation(mutation.outputSeed, mutation.seed1, mutation.seed2));

			}

		}

	}

	@Override
	public void toBytes (ByteBuf buf) {

		byte[] compressedString = null;

		if (mutationMap != null) {

			String tmpString = gson.toJson(mutationMap);

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
	public IMessage onMessage (MessageSyncMutations message, MessageContext ctx) {

		if (mutationMap != null) {

			for (Map.Entry<MutablePair<Seed, Seed>, Mutation> entry : mutationMap.entrySet()) {

				MutationRegistry.getInstance().addMutation(entry.getValue().outputSeed, entry.getValue().seed1, entry.getValue().seed2);

			}

		}

		return null;

	}

}
