package fluxedCrystals.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.reference.Reference;

public class ConfigurationHandler
{

	private static final String[] blank = {""};
	public static Configuration CONFIGURATION;
	
	public static boolean enderioAddon;
	public static boolean ic2Addon;
	public static boolean botaniaAddon;
	public static boolean shard3x3;
	public static boolean normalShardRecipes;
	public static int soilEnergyStored;
	public static boolean seedRegistryAutosave;
	public static boolean specialPlayerChangeChatColour;
	public static boolean oreDictRecipieDebug;
	public static Property oreDictBlacklist;

	private static String addonCategory = "Addon Compatability";
	private static String generalCategory = "General";
	private static String oreDictCategory = "Ore Dictionary ";
	private static String debugCategory ="Debug";
	// if your reading this jared, next time make it central, not spread all round, it fucking helps
	public static String[] specialPlayers={"TheBlueTroll","Jaredlll08","Namroc_Smith"};
	
	
	

	public static void init (File configFile) {

		if (CONFIGURATION == null) {

			CONFIGURATION = new Configuration(configFile);
			// banners
			CONFIGURATION.addCustomCategoryComment(addonCategory, "For dealing with intermod relationships");
			CONFIGURATION.addCustomCategoryComment(generalCategory, "For screwing with the crafting method");
			CONFIGURATION.addCustomCategoryComment(oreDictCategory, "For abusing the OreDict and all it stands for");
			CONFIGURATION.addCustomCategoryComment(debugCategory , "When console spam is your thing");
			loadConfiguration();

		}

	}

	private static void loadConfiguration () {
		//addon crap
		enderioAddon = CONFIGURATION.get(addonCategory, "EnderIO Addon Support", true).getBoolean(true);
		ic2Addon = CONFIGURATION.get(addonCategory, "Ic2 support", true,"Ic2 support, life support for ic2 if you ask me").getBoolean(true);
		botaniaAddon = CONFIGURATION.get(addonCategory, "Botainia Support", true).getBoolean(true);
		//general crap
		normalShardRecipes = CONFIGURATION.get(generalCategory, "Should materials be crafted in a normal crafting table?", false).getBoolean(false);
		shard3x3 = CONFIGURATION.get(generalCategory, "Should shards craft into the ingredients with 9 of the drops, or with 4 of the drop?", true).getBoolean(true);
		soilEnergyStored = CONFIGURATION.getInt("soilEnergyStored", generalCategory, 1000, 0, 10000000, "how much energy can be stored in a soil block, DONT LOWER IT UNDER 100000");
		seedRegistryAutosave = CONFIGURATION.get(generalCategory, "seedRegistryAutosave", false, "autosave the seed registry every 3 mins? (dont bother unless your changing seeds dynamically)").getBoolean(false);
		specialPlayerChangeChatColour = CONFIGURATION.get(generalCategory, "specialPlayerChangeChatColour", true, "specialPlayerChangeChatColour?").getBoolean(true);
		//oredict shit
		oreDictBlacklist = CONFIGURATION.get(oreDictCategory, "OreDict Lookup blacklist", blank,"a string array of oredict's that the infuser ignores");
		//Glorious Debug Code of The Flying Spaghetti Monster
		oreDictRecipieDebug = CONFIGURATION.get(debugCategory,"OreDictRecipieDebug",false,"Trace Oredict Calls in the infusor").getBoolean(false);
		if (CONFIGURATION.hasChanged()) {

			CONFIGURATION.save();

		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event) {

		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {

			loadConfiguration();

		}

	}

	public static boolean isPlayerSpecial(String player)
	//retrieve the string array from the config handler, and ieterate thru it comparing player name with stored, setting true if is
	{	boolean flag=false;
		for (int i=0;i<specialPlayers.length;i++){
			if (specialPlayers[i].equalsIgnoreCase(player)){
				flag=true;continue;//if we are true lets leave the loop now, we dont care about the rest
			}
		}
		return flag;
	
	}

}
