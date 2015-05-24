package fluxedCrystals.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeHandler {

	public static void postInit() {

		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed, 2), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.poweredSoil, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.seedInfuser, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', Items.gold_ingot, 'd', "gemDiamond", 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCItems.upgradeNight, 4), " c ", "cuc", " c ", 'c', Items.clock, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCItems.upgradeEffeciency, 4), " c ", "cuc", " c ", 'c', Blocks.coal_block, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCItems.upgradeAutomation, 4), " p ", "sus", " p ", 'p', Blocks.piston, 's', Blocks.sticky_piston, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCItems.upgradeSpeed, 4), " s ", "sus", " s ", 's', Items.sugar, 'u', FCItems.universalSeed));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCBlocks.gemRefiner), "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', "ingotGold", 'd', "gemDiamond", 'u', FCItems.upgradeAutomation));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheWood, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.planks).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheStone, " ww", "s  ", " s ", 's', Items.stick, 'w', Blocks.cobblestone).setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheIron, " ww", "s  ", " s ", 's', Items.stick, 'w', "ingotIron").setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheGold, " ww", "s  ", " s ", 's', Items.stick, 'w', "ingotGold").setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCItems.scytheDiamond, " ww", "s  ", " s ", 's', Items.stick, 'w', "gemDiamond").setMirrored(true));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.gemCutter, "gdi", "sus", "idg", 's', Blocks.soul_sand, 'i', "ingotIron", 'g', "ingotGold", 'd', "gemDiamond", 'u', "ingotIron"));
	}

}
