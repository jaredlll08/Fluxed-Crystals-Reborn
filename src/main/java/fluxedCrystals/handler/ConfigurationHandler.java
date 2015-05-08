package fluxedCrystals.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{

	public static Configuration CONFIGURATION;

	public static void init(File configFile)
	{

		if (CONFIGURATION == null)
		{

			CONFIGURATION = new Configuration(configFile);

			loadConfiguration();

		}

	}

	private static void loadConfiguration()
	{

		if (CONFIGURATION.hasChanged())
		{

			CONFIGURATION.save();

		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{

		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{

			loadConfiguration();

		}

	}

}
