/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [Jan 30, 2014, 5:57:07 PM (GMT)]
 */
package vazkii.botania.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class RecipeManaInfusion {

	ItemStack output;
	Object input;
	int mana;
	boolean isAlchemy = false;
	boolean isConjuration = false;

	public RecipeManaInfusion(ItemStack output, Object input, int mana) {
		this.output = output;
		this.input = input;
		this.mana = mana;
	}

	public boolean matches(ItemStack stack) {

		if (input instanceof ItemStack) {
			ItemStack inputCopy = ((ItemStack) input).copy();
			if (inputCopy.getItemDamage() == Short.MAX_VALUE) inputCopy.setItemDamage(stack.getItemDamage());

			return stack.isItemEqual((ItemStack) inputCopy);
		}

		if (input instanceof String) {
			List<ItemStack> validStacks = OreDictionary.getOres((String) input);

			for (ItemStack ostack : validStacks) {
				ItemStack cstack = ostack.copy();
				if (cstack.getItemDamage() == Short.MAX_VALUE) cstack.setItemDamage(stack.getItemDamage());

				if (stack.isItemEqual(cstack)) return true;
			}
		}

		return false;
	}

	public boolean isAlchemy() {
		return isAlchemy;
	}

	public void setAlchemy(boolean alchemy) {
		isAlchemy = alchemy;
	}

	public boolean isConjuration() {
		return isConjuration;
	}

	public void setConjuration(boolean conjuration) {
		isConjuration = conjuration;
	}

	public Object getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public int getManaToConsume() {
		return mana;
	}
}
