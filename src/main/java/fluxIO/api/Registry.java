package fluxIO.api;

import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.MutablePair;

public class Registry {

	public static class LavaGenerator {
		public static ArrayList<FluidStack> lavaGeneratorFluids = new ArrayList<FluidStack>();

		public static boolean canUse(Fluid fluid) {
			for (FluidStack stack : lavaGeneratorFluids) {
				if (stack.getFluid() == fluid) {
					return true;
				}
			}
			return false;
		}

		public static void addLavaGeneratorFluid(FluidStack fluid) {
			if (!canUse(fluid.getFluid())) {
				lavaGeneratorFluids.add(fluid);
			}
		}

		public static void removeLavaGeneratorFluid(Fluid fluid) {
			FluidStack toRemove = null;
			for (FluidStack pair : lavaGeneratorFluids) {
				if (pair.getFluid() == fluid) {
					toRemove = pair;
				}
			}
			lavaGeneratorFluids.remove(toRemove);
		}
	}

	public static class TrashGenerator {

		public static ArrayList<ItemStack> trashGeneratorBlacklist = new ArrayList<ItemStack>();

		public static boolean canTrash(ItemStack item) {
			for (ItemStack stack : trashGeneratorBlacklist) {
				if (stack.isItemEqual(item)) {
					return false;
				}
			}
			return true;
		}

		public static void addTrashGeneratorItem(ItemStack stack) {
			if (!trashGeneratorBlacklist.contains(stack)) trashGeneratorBlacklist.add(stack);
		}

		public static void removeBasicCoalGeneratorItem(ItemStack stack) {
			trashGeneratorBlacklist.remove(stack);
		}
	}

	public static class BasicCoalGenerator {
		public static ArrayList<MutablePair<ItemStack, Integer>> basicCoalGenerator = new ArrayList<MutablePair<ItemStack, Integer>>();

		public static int getBurnTime(ItemStack stack) {
			for (MutablePair<ItemStack, Integer> pair : basicCoalGenerator) {
				if (pair.left.isItemEqual(stack)) {
					return pair.right;
				}
			}
			return 0;
		}

		public static boolean containsItemStack(ItemStack stack) {
			for (MutablePair<ItemStack, Integer> pair : basicCoalGenerator) {
				if (pair.left.isItemEqual(stack)) {
					return true;
				}
			}
			return false;
		}

		public static void addBasicCoalGeneratorItem(ItemStack stack, int burnTime) {
			if (!containsItemStack(stack)) basicCoalGenerator.add(new MutablePair<ItemStack, Integer>(stack, burnTime));
		}

		public static void removeBasicCoalGeneratorItem(ItemStack stack) {
			MutablePair<ItemStack, Integer> toRemove = null;
			for (MutablePair<ItemStack, Integer> pair : basicCoalGenerator) {
				if (pair.left.isItemEqual(stack)) {
					toRemove = pair;
				}
			}
			basicCoalGenerator.remove(toRemove);
		}
	}

}
