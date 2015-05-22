package fluxedCrystals.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class RecipeGemCutter
{

	private ItemStack input;
	private ItemStack output;
	private int inputAmount;
	private int outputAmount;

	public RecipeGemCutter (ItemStack input, ItemStack output, int inputAmount, int outputAmount) {
		this.input = input;
		this.output = output;
		this.inputAmount = inputAmount;
		this.outputAmount = outputAmount;
	}

	public int getOutputAmount () {
		return outputAmount;
	}

	public boolean matches (ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (matches(name)) {
				return true;
			}
		}
		return stack != null && OreDictionary.itemMatches(stack, input, false);
	}

	public boolean matches (String oreDict) {
		ArrayList<ItemStack> stacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : stacks) {
			if (OreDictionary.itemMatches(stack, input, false)) {
				return true;
			}
		}
		return false;
	}

	public boolean matchesExact (ItemStack stack) {
		if (stack != null) {
			return input.isItemEqual(stack);
		}
		return false;
	}

	public ItemStack getInput () {
		ItemStack stack = input.copy();
		// stack.stackSize = getInputamount();
		return stack;
	}

	public ItemStack getOutput () {
		return output.copy();
	}

	public int getInputamount () {
		return inputAmount;
	}
}
