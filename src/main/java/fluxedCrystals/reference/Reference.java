package fluxedCrystals.reference;

public class Reference
{

	public static final String MOD_ID = "fluxedcrystals";
	public static final String LOWERCASE_MOD_ID = MOD_ID.toLowerCase();        // Not needed with this MOD_ID, but here so we can use it incase the MOD_ID changes
	public static final String MOD_NAME = "FluxedCrystals";
	public static final String VERSION = "@VERSION@";

	public static final String CLIENT_PROXY = "fluxedCrystals.proxy.ClientProxy";
	public static final String SERVER_PROXY = "fluxedCrystals.proxy.ServerProxy";
	public static final String GUI_FACTORY_CLASS = "fluxedCrystals.client.gui.GuiFactory";

	public static final String DEPENDENCIES = "after:ThermalFoundation;after:EnderIO;after:AWWayofTime;after:botania;after:NotEnoughItems;after:Waila;after:AgriCraft;after:MineTweaker3;required-after:Forge@[10.13.3.1387,)";

}
