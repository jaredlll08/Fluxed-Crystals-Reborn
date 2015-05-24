package fluxedCrystals.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.opengl.GL11;

public class GuiButtonItem extends GuiButton {

	ItemStack stack = null;

	public GuiButtonItem(int par1, int par2, int par3, int par4, int par5, String par6Str, ItemStack stack) {
		super(par1, par2, par3, par4, par5, par6Str);
		this.stack = stack;
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		
		field_146123_n = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
		int hoverState = getHoverState(field_146123_n);
		RenderItem.getInstance().renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, stack, xPosition, yPosition);
		if (hoverState == 2) {
			par1Minecraft.fontRenderer.drawString(displayString, xPosition, yPosition, 0, false);
		}
	}

}