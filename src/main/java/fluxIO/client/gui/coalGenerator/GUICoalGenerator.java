package fluxIO.client.gui.coalGenerator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import fluxIO.ModProps;
import fluxIO.tileEntity.TileEntityCoalGenerator;

public class GUICoalGenerator extends GuiContainer {

	private TileEntityCoalGenerator tile;

	public GUICoalGenerator(InventoryPlayer invPlayer, TileEntityCoalGenerator tile2) {
		super(new ContainerCoalGenerator(invPlayer, tile2));

		xSize = 176;
		ySize = 166;
		this.tile = tile2;

	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/coalGenerator.png");

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
	}

	public void updateScreen() {
		super.updateScreen();
		drawGuiContainerBackgroundLayer(0, 0, 0);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		int barHeight = (int) (((float) tile.generationTimer / tile.generationTimerDefault) * 13);
		drawTexturedModalRect(guiLeft+76, guiTop+33, 177, 0, 16, 13-barHeight);
		int barWidth= (int) (((float) tile.getEnergyStored() / tile.getMaxStorage()) * 87);
		drawTexturedModalRect(guiLeft + 42, guiTop + 48, 0, 166, barWidth, 18);

	}
}