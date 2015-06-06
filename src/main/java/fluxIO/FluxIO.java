package fluxIO;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fluxIO.api.GeneratorData;
import fluxIO.blocks.FluxBlocks;
import fluxIO.network.PacketHandler;
import fluxIO.proxy.CommonProxy;

@Mod(modid = ModProps.modid, name = ModProps.name, version = ModProps.version)
public class FluxIO {

	public static final CreativeTabFluxIO tab = new CreativeTabFluxIO();
	@Instance(ModProps.modid)
	public static FluxIO instance;
	@SidedProxy(clientSide = "fluxIO.proxy.ClientProxy", serverSide = "fluxIO.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.init();
		FluxBlocks.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.initGuis();
		proxy.initRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		GeneratorData.init();
	}

}
