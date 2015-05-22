package fluxedCrystals.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.network.message.*;
import fluxedCrystals.reference.Reference;

public class PacketHandler
{

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

	private static int id = 0;

	public static void init () {

		INSTANCE.registerMessage(MessageBiome.class, MessageBiome.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSeedInfuser.class, MessageSeedInfuser.class, id++, Side.SERVER);
		INSTANCE.registerMessage(MessageSolarFluxSync.class, MessageSolarFluxSync.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSyncSeed.class, MessageSyncSeed.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSyncSeeds.class, MessageSyncSeeds.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageGemRefiner.class, MessageGemRefiner.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageGemCutter.class, MessageGemCutter.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSyncMutation.class, MessageSyncMutation.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageSyncMutations.class, MessageSyncMutations.class, id++, Side.CLIENT);

	}

}
