package fluxedCrystals.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.aspects.Aspect;

import java.io.File;

public class ConfigurationHandler
{

	public static Configuration CONFIGURATION;

	public static String addonCategory = "Addon Compatability";
	public static String dropCategory = "Drops";

	public static boolean enderioAddon;

	public static boolean shard3x3;
	public static boolean normalShardRecipes;

	public static String aspectString;
	public static int aspectRange;
	public static Aspect aspect;

	public static void init(File configFile)
	{

		if (CONFIGURATION == null)
		{

			CONFIGURATION = new Configuration(configFile);

			CONFIGURATION.addCustomCategoryComment(addonCategory, null);
			CONFIGURATION.addCustomCategoryComment(dropCategory, null);

			loadConfiguration();

		}

	}

	private static void loadConfiguration()
	{

		enderioAddon = CONFIGURATION.get(addonCategory, "EnderIO Addon Support", true).getBoolean(true);
		aspectString = CONFIGURATION.get(addonCategory, "Override Aspect for Crystals. (null for nothing)", "null").getString();
		aspectRange = CONFIGURATION.get(addonCategory, "Override Aspect Range for Crystals. (0 for nothing)", "0").getInt();

		if (!aspectString.equals("null"))
		{

			aspect = Aspect.getAspect(aspectString);

		}

		normalShardRecipes = CONFIGURATION.get(dropCategory, "Should materials be crafted in a normal crafting table?", false).getBoolean(false);
		shard3x3 = CONFIGURATION.get(dropCategory, "Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop?", true).getBoolean(true);

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
