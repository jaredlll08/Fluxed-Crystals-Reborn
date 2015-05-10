package fluxedCrystals.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
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

	public static Item upgradeEffeciency = new Upgrade();
	public static Item upgradeNight = new Upgrade();
	public static Item upgradeSpeed = new Upgrade();
	public static Item upgradeAutomation = new Upgrade();

	public static Item upgradeMana = new Upgrade();
	public static Item upgradeLP = new Upgrade();
	public static Item upgradeEssentia = new Upgrade();

	public static Item upgradeRangeBasic = new Upgrade();
	public static Item upgradeRangeGreater = new Upgrade();
	public static Item upgradeRangeAdvanced= new Upgrade();

	public FCItems()
	{

	}

	public static void preInit()
	{

	}

	public static void initialize()
	{

		seed.setCreativeTab(FluxedCrystals.tab);
		shardRough.setCreativeTab(FluxedCrystals.tab);
		shardSmooth.setCreativeTab(FluxedCrystals.tab);
		universalSeed.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UNIVERSAL_SEED).setUnlocalizedName(Names.Items.UNIVERSAL_SEED);

		GameRegistry.registerItem(universalSeed, Names.Items.UNIVERSAL_SEED);
		GameRegistry.registerItem(seed, Names.Items.SEED);
		GameRegistry.registerItem(shardRough, Names.Items.SHARDROUGH);
		GameRegistry.registerItem(shardSmooth, Names.Items.SHARDSMOOTH);

		if (Loader.isModLoaded("Thaumcraft"))
		{

			upgradeEssentia.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_ESSENTIA).setUnlocalizedName(Names.Items.UPGRADE_ESSENTIA);

			GameRegistry.registerItem(upgradeEssentia, Names.Items.UPGRADE_ESSENTIA);

		}

		if (Loader.isModLoaded("AWWayofTime"))
		{

			upgradeLP.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_LP).setUnlocalizedName(Names.Items.UPGRADE_LP);

			GameRegistry.registerItem(upgradeLP, Names.Items.UPGRADE_LP);

		}

		if (Loader.isModLoaded("Botania"))
		{

			upgradeMana.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_MANA).setUnlocalizedName(Names.Items.UPGRADE_MANA);

			GameRegistry.registerItem(upgradeMana, Names.Items.UPGRADE_MANA);

		}

		upgradeEffeciency.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_EFFECIENCY).setUnlocalizedName(Names.Items.UPGRADE_EFFECIENCY);
		upgradeNight.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_NIGHT).setUnlocalizedName(Names.Items.UPGRADE_NIGHT);
		upgradeSpeed.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_SPEED).setUnlocalizedName(Names.Items.UPGRADE_SPEED);
		upgradeAutomation.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_AUTOMATION).setUnlocalizedName(Names.Items.UPGRADE_AUTOMATION);
		upgradeRangeBasic.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_RANGE_BASIC).setUnlocalizedName(Names.Items.UPGRADE_RANGE_BASIC);
		upgradeRangeGreater.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_RANGE_GREATER).setUnlocalizedName(Names.Items.UPGRADE_RANGE_GREATER);
		upgradeRangeAdvanced.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UPGRADE_RANGE_ADVANCED).setUnlocalizedName(Names.Items.UPGRADE_RANGE_ADVANCED);

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
