package fluxIO.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fluxIO.ModProps;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModProps.modid);

	private static int id = 0;

	public static void init() {
		INSTANCE.registerMessage(MessageEnergyUpdate.class, MessageEnergyUpdate.class, id++, Side.CLIENT);

	}
}