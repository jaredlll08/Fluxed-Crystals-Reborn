package fluxedCrystals.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.client.gui.crystalTablet.GuiCrystalTablet;
import fluxedCrystals.client.render.RenderCrystal;
import fluxedCrystals.client.render.SeedInfuserRenderer;
import fluxedCrystals.handler.ClientEventHandler;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.recipe.RecipeSeedInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {


	@Override
	public ClientProxy getClientProxy() {

		return this;

	}

	@Override
	public void registerRenderers() {

		FluxedCrystals.crystalRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderCrystal renderCrystal = new RenderCrystal();
		RenderingRegistry.registerBlockHandler(renderCrystal);

		FluxedCrystals.seedInfuserRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new SeedInfuserRenderer());

	}

	@Override
	public World getClientWorld() {

		return FMLClientHandler.instance().getClient().theWorld;

	}

	@Override
	public boolean isServer() {

		return false;

	}

	@Override
	public boolean isClient() {

		return true;

	}

	@Override
	public void initialize() {

		super.initialize();

		ClientEventHandler clientEventHandler = new ClientEventHandler();

		MinecraftForge.EVENT_BUS.register(clientEventHandler);
		FMLCommonHandler.instance().bus().register(clientEventHandler);

	}

	@Override
	public void postInit() {

		super.postInit();

		for (int i : RecipeRegistry.getAllSeedInfuserRecipes().keySet()) {

			RecipeSeedInfuser recipeSeedInfuser = RecipeRegistry.getSeedInfuserRecipeByID(i);

		}

	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void openTablet() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiCrystalTablet());
	}

}
