package fluxedCrystals.client.gui.seedInfuser;

import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSeedInfuser;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUISeedInfuser extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/gui/SeedInfuser.png");
	private TileEntitySeedInfuser tile;

	public GUISeedInfuser(InventoryPlayer invPlayer, TileEntitySeedInfuser tile2) {
		super(new ContainerSeedInfuser(invPlayer, tile2));

		xSize = 176;
		ySize = 166;
		this.tile = tile2;

	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		buttonList.add(new GuiButton(0, guiLeft + 50, guiTop + 50, 46, 20, "Infuse"));

	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {

			case 0:
				PacketHandler.INSTANCE.sendToServer(new MessageSeedInfuser(tile.xCoord, tile.yCoord, tile.zCoord));
				break;

		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (tile.isInfusing()) {
			RenderItem.getInstance().renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, tile.getStackInSlot(0), guiLeft, guiTop);
		}
	}

}
