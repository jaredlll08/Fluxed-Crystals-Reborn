package fluxedCrystals.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.TilebigCube;

public class RenderMultiBlock extends TileEntitySpecialRenderer {
	private final float size = 0.0625f;
	public MultiBlocktest model;

	public RenderMultiBlock(MultiBlocktest model) {
		this.model = model;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
		if (tile instanceof TilebigCube) {
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
			GL11.glRotatef(180f, 1f, 0f, 0f);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/texture.png"));
			model.render(size);
			GL11.glPopMatrix();

		}
	}
}
