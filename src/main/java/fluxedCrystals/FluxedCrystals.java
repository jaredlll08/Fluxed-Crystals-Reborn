package fluxedCrystals;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import fluxedCrystals.client.gui.GUIHandler;
import fluxedCrystals.command.CommandFC;
import fluxedCrystals.handler.ConfigurationHandler;
import fluxedCrystals.handler.RecipeHandler;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.nei.FluxedCrystalsNEIConfig;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.proxy.IProxy;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.LogHelper;
import tterrag.core.common.Lang;

import java.io.File;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, name = Reference.MOD_NAME, guiFactory = Reference.GUI_FACTORY_CLASS)
public class FluxedCrystals {

	public static final CreativeTabFluxedCrystals tab = new CreativeTabFluxedCrystals();
	public static File configDir = null;
	public static int crystalRenderID;
	public static final Lang lang = new Lang(Reference.MOD_ID);

	@Mod.Instance("fluxedCrystals")
	public static FluxedCrystals instance;

	public static int seedInfuserRenderID;
	public static int glassRenderID;
	public static int chunkRenderID;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static IProxy proxy;

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		event.registerServerCommand(new CommandFC());

	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + File.separator + Reference.MOD_ID);
		ConfigurationHandler.init(new File(configDir.getAbsolutePath() + File.separator + Reference.MOD_ID + ".cfg"));
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		PacketHandler.init();

		SeedRegistry.getInstance();

		SeedRegistry.getInstance().Load();

		FCItems.preInit();
		FCBlocks.preInit();

		proxy.preInit();

		if (Loader.isModLoaded("NotEnoughItems") && event.getSide() == Side.CLIENT)
		{
			new FluxedCrystalsNEIConfig().loadConfig();
		}

		FMLInterModComms.sendMessage("Waila", "register", "fluxedCrystals.compat.waila.WailaCompat.load");

		LogHelper.info("Pre Initialization Complete!");

	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event)
	{

		FCItems.initialize();
		FCBlocks.initialize();

		proxy.initialize();
		proxy.registerRenderers();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());

		LogHelper.info("Initialization Complete!");

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		FCItems.postInit();
		FCBlocks.postInit();
		RecipeHandler.postInit();

		proxy.postInit();

		LogHelper.info("Post Initialization Complete!");

	}

	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event)
	{

		SeedRegistry.getInstance().Save();

	}

	public SeedRegistry getSeedRegistry()
	{

		return SeedRegistry.getInstance();

	}

}
