package fluxedCrystals.client.gui.crystalTablet;

import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiCrystalTablet extends GuiScreen {
	int guiWidth = 171;
	int guiHeight = 196;
	int left, top;
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/crystalTablet.png");

	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {

		if (mc != null && mc.renderEngine != null) {

			mc.renderEngine.bindTexture(texture);
		}
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);
		//		if (fontRendererObj != null) {
		//			fontRendererObj.drawSplitString(AstreaInfinitum.lang.localize("gui.book.basic.text", true), left + 14, top + 8, 120, 0x493D26);
		//		}
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(FCItems.universalSeed), left + 45, top + 9);
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(FCItems.seed), left + 6, top + 100);
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(FCBlocks.poweredSoil), left + 45, top + 195);
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(FCBlocks.seedInfuser), left + 130, top + 195);
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(FCBlocks.poweredSoil), left + 169, top + 100);


	}

	@Override
	public void initGui() {
		super.initGui();

		this.left = (this.width / 2) - (guiWidth / 2);
		this.top = (this.height / 2) - (guiHeight / 2);

	}
}
