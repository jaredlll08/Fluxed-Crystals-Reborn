package fluxedCrystals.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


import java.util.ArrayList;

public class RecipeSeedInfuser
{

	private ItemStack input;
	private ItemStack output;
	private ItemStack ingredient;
	private int inputAmount;
	private int index = -1;

	public RecipeSeedInfuser (ItemStack ingredient, ItemStack input, ItemStack output, int inputAmount) {

		this(ingredient, input, output, inputAmount, -1);

	}

	public RecipeSeedInfuser (ItemStack ingredient, ItemStack input, ItemStack output, int inputAmount, int index) {

		this.input = input;
		this.output = output;
		this.ingredient = ingredient;
		this.inputAmount = inputAmount;
		this.index = index;

	}

	public boolean matchesIngredient (ItemStack itemStack) {

		return this.matches(this.ingredient, itemStack);

	}

	public boolean matches (ItemStack ingredient, ItemStack stack) {
		int[] ids = OreDictionary.getOreIDs(stack);
		
		for (int id : ids) {
			String name = OreDictionary.getOreName(id);
			if (name.contains("FMC")){ return false;}
			
			if (matches(name) && ingredient.isItemEqual(this.ingredient)) {
				return true;
			}
		}
		return stack != null && OreDictionary.itemMatches(stack, input, true);
		
	}

	private boolean matches (String oreDict) {
		ArrayList<ItemStack> listStacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : listStacks) {
			if (OreDictionary.itemMatches(stack, input, true) && ingredient.isItemEqual(this.ingredient)) {
				return true;
			}
		}
		return false;
	}

	public boolean matchesExact (ItemStack input, ItemStack ingredient) {
		return this.input.isItemEqual(ingredient) && this.ingredient.isItemEqual(input);
	}

	public ItemStack getInput () {
		ItemStack stack = input.copy();
		stack.stackSize = getInputamount();
		return stack;
	}

	public ItemStack getOutput () {
		return output.copy();
	}

	public ItemStack getIngredient () {
		return ingredient.copy();
	}

	public int getInputamount () {
		return inputAmount;
	}

	public int getIndex () {
		return index;
	}
}
