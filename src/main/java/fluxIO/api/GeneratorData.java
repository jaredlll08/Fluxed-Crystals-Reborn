package fluxIO.api;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.oredict.OreDictionary;
import static fluxIO.api.Registry.BasicCoalGenerator.*;

import org.apache.commons.lang3.tuple.MutablePair;

import cpw.mods.fml.common.registry.GameRegistry;

public class GeneratorData {

	public static void init() {
		registerBasicCoalGeneratorItems();
		registerTrashGeneratorItems();
		registerLavaGeneratorFluid();
	}

	private static void registerLavaGeneratorFluid() {
		Registry.LavaGenerator.addLavaGeneratorFluid(new FluidStack(FluidRegistry.LAVA, 250));
	}

	private static void registerTrashGeneratorItems() {
	}

	private static void registerBasicCoalGeneratorItems() {
//		Registry.BasicCoalGenerator.basicCoalGenerator.add(new MutablePair<ItemStack, Integer>(new ItemStack(Items.coal), 600));
		addBasicCoalGeneratorItem(new ItemStack(Items.coal), 600);
		addBasicCoalGeneratorItem(new ItemStack(Items.coal, 1, 1), 600);
		for (ItemStack stack : OreDictionary.getOres("blockCoal")) {
			addBasicCoalGeneratorItem(stack, 5400);
		}
	}
}
