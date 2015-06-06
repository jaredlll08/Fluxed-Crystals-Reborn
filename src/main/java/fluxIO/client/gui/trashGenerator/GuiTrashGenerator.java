package fluxIO.client.gui.trashGenerator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import fluxIO.ModProps;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxIO.tileEntity.TileEntityTrashGenerator;

public class GuiTrashGenerator extends GuiContainer {

	private TileEntityTrashGenerator tile;

	public GuiTrashGenerator(InventoryPlayer invPlayer, TileEntityTrashGenerator tile2) {
		super(new ContainerTrashGenerator(invPlayer, tile2));
		xSize = 176;
		ySize = 166;
		this.tile = tile2;
	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/trashGenerator.png");

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
		int barWidth= (int) (((float) tile.getEnergyStored() / tile.getMaxStorage()) * 87);
		drawTexturedModalRect(guiLeft + 42, guiTop + 48, 0, 166, barWidth, 18);
		drawTexturedModalRect(guiLeft + 76, guiTop + 33 + barHeight, 177, barHeight, 16, barHeight + 13);
	}
}
