package fluxedCrystals.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.util.Utils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ClientEventHandler {

	private boolean resetRender;
	private float trans = 0;
	private boolean descending;

	public void render3DItem(EntityItem item) {

		GL11.glPushMatrix();
		GL11.glDepthMask(true);
		GL11.glRotatef(90, 90, 0, 1);

		item.hoverStart = 0.0F;

		RenderManager.instance.func_147939_a(item, 0.0, -0.1, -0.3, 0, 0, false);
		GL11.glPopMatrix();
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void render(RenderLivingEvent.Pre event) {
		/*String s = EnumChatFormatting.getTextWithoutFormattingCodes(event.entity.getCommandSenderName());
		if (Utils.isPlayerSpecial(s)) {
			if (new Random().nextInt(2) == 0) {
				if (!descending) {
					trans++;
				} else {
					trans--;
				}
				if (trans > 100) {
					descending = true;
					trans = 100f;
				}
				if (trans < 0) {
					descending = false;
					trans = 0;
				}
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, trans / 100);
			GL11.glEnable(3042);
			GL11.glBlendFunc(770, 771);
			resetRender = true;
		}*/

	}

	@SubscribeEvent
	public void entityColorRender(RenderLivingEvent.Post event) {
		if (this.resetRender) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(3042);
			resetRender = false;
		}
	}

}
