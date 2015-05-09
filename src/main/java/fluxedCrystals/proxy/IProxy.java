package fluxedCrystals.proxy;

public interface IProxy
{

	void preInit ();
	void initialize ();
	void postInit ();

	ClientProxy getClientProxy();

	void registerRenderers();
}
