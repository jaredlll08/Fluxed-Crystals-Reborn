package fluxedCrystals.client.gui;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class RenderCrystal implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(x, y, z);
		Tessellator tess = Tessellator.instance;
		tess.setColorOpaque_I(SeedRegistry.getInstance().getSeedByID(tile.getIndex()).color);
		tess.setBrightness(0xF000F0);
		renderer.enableAO = false;
		renderer.drawCrossedSquares(block.getIcon(world, x, y, z, meta), x, y, z, 1.0f);
		renderer.enableAO = true;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return FluxedCrystals.crystalRenderID;
	}

}
