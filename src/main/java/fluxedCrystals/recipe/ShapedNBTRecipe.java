package fluxedCrystals.recipe;

import fluxedCrystals.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedNBTRecipe implements IRecipe {

	private static final int MAX_CRAFT_GRID_WIDTH = 3;
	private static final int MAX_CRAFT_GRID_HEIGHT = 3;

	private ShapedOreRecipe recipe;

	public ShapedNBTRecipe(ShapedOreRecipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		return recipe.matches(inv, world) && NBTHelper.isMatch(getRecipeOutput(), recipe.getRecipeOutput());
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return null;
	}

	@Override
	public int getRecipeSize() {
		return recipe.getRecipeSize();
	}

	@Override
	public ItemStack getRecipeOutput() {
		return recipe.getRecipeOutput();
	}

}