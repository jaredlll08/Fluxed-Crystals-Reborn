package fluxIO.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("");

	private static int id = 0;

	public static void init() {

		INSTANCE.registerMessage(MessageCoalGenerator.class, MessageCoalGenerator.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(MessageFluid.class, MessageFluid.class, id++, Side.CLIENT);
		

	}

}
