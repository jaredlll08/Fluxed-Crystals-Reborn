package fluxedCrystals.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import fluxedCrystals.util.LogHelper;


import java.util.ArrayList;

import fluxedCrystals.handler.ConfigurationHandler;

public class RecipeSeedInfuser
{

	private ItemStack input;
	private ItemStack output;
	private ItemStack ingredient;
	private int inputAmount;
	private int index = -1;
	private String[] blacklist = ConfigurationHandler.oreDictBlacklist.getStringList();

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
		boolean fail = false;
		
		 
		 /* a list of ore names, a list of ones to ignore
		 * and a ingredient
		 * 
		 *  We are trying to check all ids, and each id against all blacklist items.
		 */
		
		for (int id : ids) {
			String name = OreDictionary.getOreName(id); //get oredict name from id, i.e dustGunpowder or FMC250Material.
			
			LogHelper.info("Blacklist.length="+blacklist.length+" | oreDict name="+name);
			if (!(blacklist.length ==0) ){
				//only do this if blacklist has at least 1 entry				
				for (int i = 0; i < blacklist.length; i++){
				 
					if (name.contains(blacklist[i])){ fail=true;} 
		        }	
			}
		        
			if (fail==true){fail=false; continue;}//it failed the blacklist check so just finish this iteration.
			if (matches(name) && ingredient.isItemEqual(this.ingredient)) {
				
				return true;
			}
		}
		
		return stack != null && OreDictionary.itemMatches(stack, input, false);
		
	}

	
	private boolean matches (String oreDict) {
		ArrayList<ItemStack> listStacks = OreDictionary.getOres(oreDict);
		for (ItemStack stack : listStacks) {
			if (OreDictionary.itemMatches(stack, input, false) && ingredient.isItemEqual(this.ingredient)) {
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
