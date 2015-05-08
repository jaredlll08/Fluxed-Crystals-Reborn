package fluxedCrystals.init;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.items.ItemShardRough;
import fluxedCrystals.items.ItemShardSmooth;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Textures;

public class FCItems
{

	public static Item universalSeed = new ItemUniversalSeed();
	public static Item seed = new ItemSeed();
	public static Item shardRough= new ItemShardRough();
	public static Item shardSmooth = new ItemShardSmooth();
	
	

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
		

	}

	public static void postInit()
	{

	}

}
