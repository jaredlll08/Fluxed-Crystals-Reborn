package fluxedCrystals.proxy;

import net.minecraft.world.World;

public interface IProxy
{

	void preInit ();
	void initialize ();
	void postInit ();
	ClientProxy getClientProxy();
	World getClientWorld();
	void registerRenderers();
	boolean isClient();
	boolean isServer();

}
