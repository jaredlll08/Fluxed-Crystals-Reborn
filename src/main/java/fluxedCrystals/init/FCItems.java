package fluxedCrystals.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.items.seeds.ItemSeed;
import fluxedCrystals.items.seeds.ItemUniversalSeed;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.reference.Textures;
import net.minecraft.item.Item;

public class FCItems
{

	public static Item universalSeed = new ItemUniversalSeed();
	public static Item seed = new ItemSeed();

	public FCItems()
	{

	}

	public static void preInit()
	{

	}

	public static void initialize()
	{

		seed.setCreativeTab(FluxedCrystals.tab);
		universalSeed.setCreativeTab(FluxedCrystals.tab).setTextureName(Textures.Items.UNIVERSAL_SEED).setUnlocalizedName(Names.Items.UNIVERSAL_SEED);

		GameRegistry.registerItem(universalSeed, Names.Items.UNIVERSAL_SEED);
		GameRegistry.registerItem(seed, Names.Items.SEED);

	}

	public static void postInit()
	{

	}

}
