package fluxedCrystals.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.network.message.MessageSyncSeed;
import fluxedCrystals.network.message.MessageSyncSeeds;
import fluxedCrystals.reference.Reference;

public class PacketHandler
{

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.LOWERCASE_MOD_ID);

	public static void init()
	{

		INSTANCE.registerMessage(MessageSyncSeed.class, MessageSyncSeed.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageSyncSeeds.class, MessageSyncSeeds.class, 1, Side.CLIENT);

	}

}
