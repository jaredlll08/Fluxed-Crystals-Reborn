package fluxedCrystals.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import fluxedCrystals.handler.ConfigurationHandler;
import fluxedCrystals.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{

	public ModGuiConfig (GuiScreen guiScreen) {

		super(guiScreen, new ConfigElement(ConfigurationHandler.CONFIGURATION.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.CONFIGURATION.toString()));

	}

}
