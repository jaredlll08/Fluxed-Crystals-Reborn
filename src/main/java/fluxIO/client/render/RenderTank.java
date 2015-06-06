package fluxIO.client.render;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import fluxIO.ModProps;
import fluxIO.client.TextureUtils;
import fluxIO.tileEntity.fluids.TileEntityFluidTank;

public class RenderTank extends TileEntitySpecialRenderer {
	private Random random = new Random();
	private RenderBlocks renderBlock = new RenderBlocks();
	private Minecraft mc = Minecraft.getMinecraft();
	private final float size = 0.0625f;
	private ModelTank model = new ModelTank();

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float p_147500_8_) {
		if (tile instanceof TileEntityFluidTank) {
			renderTile((TileEntityFluidTank) tile, x, y, z);
		}
	}

	public void renderTile(TileEntityFluidTank tile, double x, double y, double z) {
		if (tile.tank.getFluid() != null) {
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);

			GL11.glTranslatef((float) x + 0.9f, (float) y + 0.56f, (float) z + 0.9f);
			// GL11.glRotatef(180f, 1f, 0f, 0f);
			FluidStack liquid = tile.tank.getFluid();
			liquid.amount = tile.tank.getFluidAmount();
			renderFluid(liquid);
			GL11.glPopMatrix();

		}
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.50f, (float) z + 0.5f);
		GL11.glRotatef(180f, 1, 0, 0);
		GL11.glRotatef(tile.getBlockMetadata() * 90, 0, 1, 0);
		mc.getTextureManager().bindTexture(new ResourceLocation(ModProps.modid, "textures/models/modelTank.png"));
		GL11.glColor4d(0.7, 0.7, 0.7, 1);
		model.render(size);
		GL11.glPopMatrix();
	}

	public static void renderFluid(FluidStack stack) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor4f(1, 1, 1, 1);
		FluidStack fluidStack = stack;
		final Fluid fluid = fluidStack.getFluid();

		IIcon texture = fluid.getStillIcon();
		final int color;

		if (texture != null) {
			TextureUtils.bindTextureToClient(getFluidSheet(fluid));
			color = fluid.getColor(fluidStack);
		} else {
			TextureUtils.bindDefaultTerrainTexture();
			texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("missingno");
			color = 0xFFFFFFFF;
		}

		Tessellator t = Tessellator.instance;

		double liquid = stack.amount + 0.0;
		double maxLiquid = 32000.0;
		double height = (liquid / maxLiquid) * 0.76;

		final double se = height;
		final double ne = height;
		final double sw = height;
		final double nw = height;

		final double center = height;

		final double uMin = texture.getMinU();
		final double uMax = texture.getMaxU();
		final double vMin = texture.getMinV();
		final double vMax = texture.getMaxV();

		final double vHeight = vMax - vMin;

		final float r = (color >> 16 & 0xFF) / 255.0F;
		final float g = (color >> 8 & 0xFF) / 255.0F;
		final float b = (color & 0xFF) / 255.0F;
		t.startDrawingQuads();
		t.setColorOpaque_F(r, g, b);
		t.setBrightness(0xF000F0);
		t.addVertexWithUV(-0.8, 0, 0, uMax, vMin);
		t.addVertexWithUV(0, 0, 0, uMin, vMin);
		t.addVertexWithUV(0, nw, 0, uMin, vMin + (vHeight * nw));
		t.addVertexWithUV(-0.8, ne, 0, uMax, vMin + (vHeight * ne));

		t.addVertexWithUV(-0.8, 0, -0.8, uMin, vMin);
		t.addVertexWithUV(-0.8, se, -0.8, uMin, vMin + (vHeight * se));
		t.addVertexWithUV(0, sw, -0.8, uMax, vMin + (vHeight * sw));
		t.addVertexWithUV(0, 0, -0.8, uMax, vMin);

		t.addVertexWithUV(-0.8, 0, 0, uMin, vMin);
		t.addVertexWithUV(-0.8, ne, 0, uMin, vMin + (vHeight * ne));
		t.addVertexWithUV(-0.8, se, -0.8, uMax, vMin + (vHeight * se));
		t.addVertexWithUV(-0.8, 0, -0.8, uMax, vMin);

		t.addVertexWithUV(0, 0, -0.8, uMin, vMin);
		t.addVertexWithUV(0, sw, -0.8, uMin, vMin + (vHeight * sw));
		t.addVertexWithUV(0, nw, 0, uMax, vMin + (vHeight * nw));
		t.addVertexWithUV(0, 0, 0, uMax, vMin);

		final double uMid = (uMax + uMin) / 2;
		final double vMid = (vMax + vMin) / 2;

		t.addVertexWithUV(-0.8, center, -0.8, uMid, vMid);
		t.addVertexWithUV(-0.8, se, -0.8, uMax, vMin);
		t.addVertexWithUV(-0.8, ne, 0, uMin, vMin);
		t.addVertexWithUV(0, nw, 0, uMin, vMax);

		t.addVertexWithUV(0, sw, -0.8, uMax, vMax);
		t.addVertexWithUV(-0.8, se, -0.8, uMax, vMin);
		t.addVertexWithUV(-0.8, center, -0.8, uMid, vMid);
		t.addVertexWithUV(0, nw, 0, uMin, vMax);

		t.addVertexWithUV(-0.8, 0, 0, uMax, vMin);
		t.addVertexWithUV(-0.8, 0, -0.8, uMin, vMin);
		t.addVertexWithUV(0, 0, -0.8, uMin, vMax);
		t.addVertexWithUV(0, 0, 0, uMax, vMax);
		t.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	public static ResourceLocation getFluidSheet(FluidStack liquid) {
		if (liquid == null) return TextureMap.locationBlocksTexture;
		return getFluidSheet(liquid.getFluid());
	}

	/**
	 * @param liquid
	 */
	public static ResourceLocation getFluidSheet(Fluid liquid) {
		return TextureMap.locationBlocksTexture;
	}
}
