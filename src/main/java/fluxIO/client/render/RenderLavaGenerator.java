package fluxIO.client.render;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import fluxIO.tileEntity.TileEntityLavaGenerator;

public class RenderLavaGenerator extends TileEntitySpecialRenderer {
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
		if (tile instanceof TileEntityLavaGenerator) {
			renderTile((TileEntityLavaGenerator) tile, x, y, z);
		}
	}

	public void renderTile(TileEntityLavaGenerator tile, double x, double y, double z) {
		
	}

}
