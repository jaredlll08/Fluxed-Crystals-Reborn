package fluxedCrystals;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxedCrystals.command.CommandFC;
import fluxedCrystals.handler.ConfigurationHandler;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.proxy.IProxy;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.LogHelper;
import tterrag.core.common.Lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, name = Reference.MOD_NAME, guiFactory = Reference.GUI_FACTORY_CLASS)
public class FluxedCrystals
{

	public static final CreativeTabFluxedCrystals tab = new CreativeTabFluxedCrystals();
	public static File configDir = null;
	public static int crystalRenderID;
	public static final Lang lang = new Lang(Reference.MOD_ID);
	public static FluxedCrystals instance;
	public static int seedInfuserRenderID;
	public static int glassRenderID;
	public static int chunkRenderID;
	public static boolean thaumcraftThere;
	public static List<ModContainer> activeMods = new ArrayList<ModContainer>();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static IProxy proxy;

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{

		event.registerServerCommand(new CommandFC());

	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		configDir = new File(event.getSuggestedConfigurationFile().getParentFile().getAbsolutePath() + File.separator + Reference.MOD_ID);
		ConfigurationHandler.init(new File(configDir.getAbsolutePath() + File.separator + Reference.MOD_ID + ".cfg"));
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		PacketHandler.init();

		SeedRegistry.getInstance();

		SeedRegistry.getInstance().LoadAllFromDisk();

		FCItems.preInit();
		FCBlocks.preInit();

		proxy.preInit();

		if (Loader.isModLoaded("Thaumcraft"))
		{

			thaumcraftThere = true;

		}
		else
		{

			thaumcraftThere = false;

		}

		LogHelper.info("Pre Initialization Complete!");

	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event)
	{

		FCItems.initialize();
		FCBlocks.initialize();
		

		proxy.initialize();

		LogHelper.info("Initialization Complete!");

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

		FCItems.postInit();
		FCBlocks.postInit();
		

		proxy.postInit();

		LogHelper.info("Post Initialization Complete!");

	}

	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event)
	{

		SeedRegistry.getInstance().SaveAll();

	}

	public SeedRegistry getSeedRegistry()
	{

		return SeedRegistry.getInstance();

	}

}
