package fluxedCrystals.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.BlockSeedInfuser;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class SeedInfuserRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderer.setOverrideBlockTexture(block.getIcon(0, metadata));
		renderer.renderBlockAsItem(Blocks.stone, 0, 1.0f);
		renderer.clearOverrideBlockTexture();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		renderer.renderStandardBlock(block, x, y, z);
		TileEntitySeedInfuser tile = (TileEntitySeedInfuser) world.getTileEntity(x, y, z);
		int index = tile.getRecipeIndex();
		if (index >= 0) {
			int color = SeedRegistry.getInstance().getSeedByID(index).color;
			Tessellator tess = Tessellator.instance;
			tess.setColorOpaque_I(color);
			float red = (color >> 16 & 255) / 255.0F;
	        float green = (color >> 8 & 255) / 255.0F;
	        float blue = (color & 255) / 255.0F;
			renderer.setOverrideBlockTexture(((BlockSeedInfuser)block).textures[1]);
			renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, red, green, blue);
			renderer.clearOverrideBlockTexture();
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return FluxedCrystals.seedInfuserRenderID;
	}
}
