package fluxIO.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import fluxIO.blocks.FluxBlocks;
import fluxIO.client.gui.GUIHandler;
import fluxIO.client.render.RenderLavaGenerator;
import fluxIO.client.render.RenderTank;
import fluxIO.client.render.items.RenderItemTank;
import fluxIO.tileEntity.TileEntityLavaGenerator;
import fluxIO.tileEntity.fluids.TileEntityFluidTank;

public class ClientProxy extends CommonProxy {
	public void initGuis() {
		new GUIHandler();
	}
	
	@Override
	public void initRenderers() {
	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLavaGenerator.class, new RenderLavaGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTank.class, new RenderTank());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FluxBlocks.tank), new RenderItemTank());
				
	}
}
