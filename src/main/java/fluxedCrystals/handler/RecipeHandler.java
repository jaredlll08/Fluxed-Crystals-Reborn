package fluxedCrystals.handler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.ShapedNBTRecipe;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.LogHelper;
import fluxedCrystals.util.NBTHelper;

public class RecipeHandler {

	public static void postInit() {

		GameRegistry.addShapedRecipe(new ItemStack(FCItems.universalSeed, 2), "sss", "rar", "sss", 's', Items.wheat_seeds, 'r', Blocks.redstone_block, 'a', new ItemStack(Items.potionitem, 1, 16));

		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.poweredSoil, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotIron", 'a', Blocks.sand, 'e', Items.wheat_seeds));
		GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.poweredSoilMana, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "ingotManasteel", 'a', "livingrock", 'e', "manaPearl"));
		//GameRegistry.addRecipe(new ShapedOreRecipe(FCBlocks.poweredSoilEU, "sis", "aea", "sis", 's', Blocks.soul_sand, 'i', "circuitBasic", 'a', "itemRubber", 'e', "dustDiamond"));

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

		
		for (Seed seed : SeedRegistry.getInstance().getSeedMap().values()) {
			LogHelper.info("Defining recipie for :"+seed.name);
			ItemStack sword = new ItemStack(FCItems.crystalSword);
			NBTHelper.setInteger(sword, "colorType", seed.color);
			NBTHelper.setInteger(sword, "seedID", seed.seedID);
			GameRegistry.addRecipe(new ShapedOreRecipe(sword, " I ", " I ", " S ", 'I', new ItemStack(FCItems.shardRough, 1, seed.seedID), 'S', "stickWood"));
			ItemStack pickaxe = new ItemStack(FCItems.crystalPickaxe);
			NBTHelper.setInteger(pickaxe, "colorType", seed.color);
			NBTHelper.setInteger(pickaxe, "seedID", seed.seedID);
			GameRegistry.addRecipe(new ShapedOreRecipe(pickaxe, "III", " S ", " S ", 'I', new ItemStack(FCItems.shardRough, 1, seed.seedID), 'S', "stickWood"));
			ItemStack shovel = new ItemStack(FCItems.crystalShovel);
			NBTHelper.setInteger(shovel, "colorType", seed.color);
			NBTHelper.setInteger(shovel, "seedID", seed.seedID);
			GameRegistry.addRecipe(new ShapedOreRecipe(shovel, " I ", " S ", " S ", 'I', new ItemStack(FCItems.shardRough, 1, seed.seedID), 'S', "stickWood"));
			ItemStack axe = new ItemStack(FCItems.crystalAxe);
			NBTHelper.setInteger(axe, "colorType", seed.color);
			NBTHelper.setInteger(axe, "seedID", seed.seedID);
			GameRegistry.addRecipe(new ShapedOreRecipe(axe, " II", " SI", " S ", 'I', new ItemStack(FCItems.shardRough, 1, seed.seedID), 'S', "stickWood").setMirrored(true));
			GameRegistry.addRecipe(new ShapedOreRecipe(axe, "II ", "IS ", " S ", 'I', new ItemStack(FCItems.shardRough, 1, seed.seedID), 'S', "stickWood").setMirrored(true));

		}
	}

}
