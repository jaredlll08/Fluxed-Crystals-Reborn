package fluxedCrystals.handler;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.RecipeGemCutter;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.recipe.RecipeSeedInfuser;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeHandler
{

	public static void postInit()
	{

		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed, 2), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.poweredSoil, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.seedInfuser, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', Items.diamond, 'u', FCItems.universalSeed));

		for (int i : SeedRegistry.getInstance().keySet())
		{

			Seed seed = SeedRegistry.getInstance().getSeedByID(i);

			if (seed.modRequired.equals("") || (!seed.modRequired.equals("") && Loader.isModLoaded(seed.modRequired)))
			{

				RecipeRegistry.registerSeedInfuserRecipe(seed.seedID, new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), seed.getIngredient(), new ItemStack(FCItems.seed, 1, seed.seedID), seed.ingredientAmount));

				RecipeRegistry.registerGemCutterRecipe(seed.seedID, new RecipeGemCutter(new ItemStack(FCItems.shardRough, 1, seed.seedID), new ItemStack(FCItems.shardSmooth, 1, seed.seedID), 1, 1));

			}

		}

	}

}
