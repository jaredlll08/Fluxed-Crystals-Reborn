package fluxedCrystals.client.gui;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import java.util.Set;

class GuiFactory implements IModGuiFactory
{

	@Override
	public void initialize (Minecraft minecraft) {

	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass () {

		return ModGuiConfig.class;

	}

	@Override
	public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories () {

		return null;

	}

	@Override
	public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor (IModGuiFactory.RuntimeOptionCategoryElement element) {

		return null;

	}

}
