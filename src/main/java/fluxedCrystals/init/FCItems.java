package fluxedCrystals.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.items.ItemScythe;
import fluxedCrystals.items.ItemShardRough;
import fluxedCrystals.items.ItemShardSmooth;
import fluxedCrystals.items.Upgrade;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Textures;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class FCItems {

	public static Map<String, Item> itemRegistry = new HashMap<String, Item>();
	public static Item universalSeed = new ItemUniversalSeed();
	public static Item seed = new ItemSeed();
	public static Item shardRough = new ItemShardRough();
	public static Item shardSmooth = new ItemShardSmooth();

	public static Item upgradeEffeciency = new Upgrade(Textures.Items.UPGRADE_EFFECIENCY, Names.Items.UPGRADE_EFFECIENCY);
	public static Item upgradeNight = new Upgrade(Textures.Items.UPGRADE_NIGHT, Names.Items.UPGRADE_NIGHT);
	public static Item upgradeSpeed = new Upgrade(Textures.Items.UPGRADE_SPEED, Names.Items.UPGRADE_SPEED);
	public static Item upgradeAutomation = new Upgrade(Textures.Items.UPGRADE_AUTOMATION, Names.Items.UPGRADE_AUTOMATION);
	public static Item upgradeMana = new Upgrade(Textures.Items.UPGRADE_MANA, Names.Items.UPGRADE_MANA);
	public static Item upgradeLP = new Upgrade(Textures.Items.UPGRADE_LP, Names.Items.UPGRADE_LP);
	public static Item upgradeEssentia = new Upgrade(Textures.Items.UPGRADE_ESSENTIA, Names.Items.UPGRADE_ESSENTIA);
	public static Item upgradeRangeBasic = new Upgrade(Textures.Items.UPGRADE_RANGE_BASIC, Names.Items.UPGRADE_RANGE_BASIC);
	public static Item upgradeRangeGreater = new Upgrade(Textures.Items.UPGRADE_RANGE_GREATER, Names.Items.UPGRADE_RANGE_GREATER);
	public static Item upgradeRangeAdvanced = new Upgrade(Textures.Items.UPGRADE_RANGE_ADVANCED, Names.Items.UPGRADE_RANGE_ADVANCED);

	public static Item scytheWood = new ItemScythe(Textures.Items.SCYTHE_WOOD, Names.Items.SCYTHE_WOOD);
	public static Item scytheStone = new ItemScythe(Textures.Items.SCYTHE_STONE, Names.Items.SCYTHE_STONE);
	public static Item scytheIron = new ItemScythe(Textures.Items.SCYTHE_IRON, Names.Items.SCYTHE_IRON);
	public static Item scytheGold = new ItemScythe(Textures.Items.SCYTHE_GOLD, Names.Items.SCYTHE_GOLD);
	public static Item scytheDiamond = new ItemScythe(Textures.Items.SCYTHE_DIAMOND, Names.Items.SCYTHE_DIAMOND);

	public FCItems() {

	}

	public static void preInit() {

	}

	public static void initialize() {

		registerItem(universalSeed, Names.Items.UNIVERSAL_SEED, Names.Items.UNIVERSAL_SEED);
		registerItem(seed, Names.Items.SEED, Names.Items.SEED);
		registerItem(shardRough, Names.Items.SHARDROUGH, Names.Items.SHARDROUGH);
		registerItem(shardSmooth, Names.Items.SHARDSMOOTH, Names.Items.SHARDSMOOTH);
		registerItem(scytheWood, Names.Items.SCYTHE_WOOD, Names.Items.SCYTHE_WOOD);
		registerItem(scytheStone, Names.Items.SCYTHE_STONE, Names.Items.SCYTHE_STONE);
		registerItem(scytheIron, Names.Items.SCYTHE_IRON, Names.Items.SCYTHE_IRON);
		registerItem(scytheGold, Names.Items.SCYTHE_GOLD, Names.Items.SCYTHE_GOLD);
		registerItem(scytheDiamond, Names.Items.SCYTHE_DIAMOND, Names.Items.SCYTHE_DIAMOND);


		if (Loader.isModLoaded("Thaumcraft")) {

			registerItem(upgradeEssentia, Names.Items.UPGRADE_ESSENTIA, Names.Items.UPGRADE_ESSENTIA);

		}

		if (Loader.isModLoaded("AWWayofTime")) {

			registerItem(upgradeLP, Names.Items.UPGRADE_LP, Names.Items.UPGRADE_LP);

		}

		if (Loader.isModLoaded("Botania")) {

			registerItem(upgradeMana, Names.Items.UPGRADE_MANA, Names.Items.UPGRADE_MANA);

		}

		registerItem(upgradeEffeciency, Names.Items.UPGRADE_EFFECIENCY, Names.Items.UPGRADE_EFFECIENCY);
		registerItem(upgradeNight, Names.Items.UPGRADE_NIGHT, Names.Items.UPGRADE_NIGHT);
		registerItem(upgradeSpeed, Names.Items.UPGRADE_SPEED, Names.Items.UPGRADE_SPEED);
		registerItem(upgradeAutomation, Names.Items.UPGRADE_AUTOMATION, Names.Items.UPGRADE_AUTOMATION);

	}

	public static void postInit() {

	}

	private static void registerItem(Item item, String name, String key) {

		GameRegistry.registerItem(item, key);
		itemRegistry.put(key, item);
	}


}
