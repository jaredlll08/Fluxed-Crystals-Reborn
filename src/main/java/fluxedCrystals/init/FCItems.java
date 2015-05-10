package fluxedCrystals.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.items.ItemShardRough;
import fluxedCrystals.items.ItemShardSmooth;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.items.upgrades.Upgrade;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Textures;
import net.minecraft.item.Item;

public class FCItems
{

	public static Item universalSeed = new ItemUniversalSeed();
	public static Item seed = new ItemSeed();
	public static Item shardRough= new ItemShardRough();
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
	public static Item upgradeRangeAdvanced= new Upgrade(Textures.Items.UPGRADE_RANGE_ADVANCED, Names.Items.UPGRADE_RANGE_ADVANCED);

	public FCItems()
	{

	}

	public static void preInit()
	{

	}

	public static void initialize()
	{

		GameRegistry.registerItem(universalSeed, Names.Items.UNIVERSAL_SEED);
		GameRegistry.registerItem(seed, Names.Items.SEED);
		GameRegistry.registerItem(shardRough, Names.Items.SHARDROUGH);
		GameRegistry.registerItem(shardSmooth, Names.Items.SHARDSMOOTH);

		if (Loader.isModLoaded("Thaumcraft"))
		{

			GameRegistry.registerItem(upgradeEssentia, Names.Items.UPGRADE_ESSENTIA);

		}

		if (Loader.isModLoaded("AWWayofTime"))
		{

			GameRegistry.registerItem(upgradeLP, Names.Items.UPGRADE_LP);

		}

		if (Loader.isModLoaded("Botania"))
		{

			GameRegistry.registerItem(upgradeMana, Names.Items.UPGRADE_MANA);

		}

		GameRegistry.registerItem(upgradeEffeciency, Names.Items.UPGRADE_EFFECIENCY);
		GameRegistry.registerItem(upgradeNight, Names.Items.UPGRADE_NIGHT);
		GameRegistry.registerItem(upgradeSpeed, Names.Items.UPGRADE_SPEED);
		GameRegistry.registerItem(upgradeAutomation, Names.Items.UPGRADE_AUTOMATION);
		GameRegistry.registerItem(upgradeRangeBasic, Names.Items.UPGRADE_RANGE_BASIC);
		GameRegistry.registerItem(upgradeRangeGreater, Names.Items.UPGRADE_RANGE_GREATER);
		GameRegistry.registerItem(upgradeRangeAdvanced, Names.Items.UPGRADE_RANGE_ADVANCED);

	}

	public static void postInit()
	{

	}

}
