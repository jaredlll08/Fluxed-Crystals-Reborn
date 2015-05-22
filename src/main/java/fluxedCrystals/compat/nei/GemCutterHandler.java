package fluxedCrystals.compat.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.Optional;
import fluxedCrystals.recipe.RecipeGemCutter;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Optional.Interface(iface = "codechicken.nei.api.API", modid = "NotEnoughItems")
public class GemCutterHandler extends TemplateRecipeHandler
{

	private final ResourceLocation texture = new ResourceLocation(getGuiTexture());

	@Override
	public String getGuiTexture () {
		return Reference.LOWERCASE_MOD_ID + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName () {
		return "Gem Cutter";
	}

	@Override
	public int recipiesPerPage () {
		return 1;
	}

	@Override
	public void drawBackground (int recipe) {

		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(5, 5, 0, 166, 18, 18);
		GuiDraw.drawTexturedModalRect(142, 5, 0, 166, 18, 18);

		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void drawForeground (int recipe) {
		super.drawForeground(recipe);
	}

	@Override
	public void drawExtras (int recipe) {
		CachedCutterRecipe r = (CachedCutterRecipe) arecipes.get(recipe);
		int coords2[] = {0, 0};
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes (ItemStack result) {

		for (int i : RecipeRegistry.getAllGemCutterRecipes().keySet()) {

			RecipeGemCutter recipeGemCutter = RecipeRegistry.getGemCutterRecipeByID(i);

			if (recipeGemCutter.getOutput().isItemEqual(result)) {
				if (checkDupe(recipeGemCutter)) {
					this.arecipes.add(new CachedCutterRecipe(recipeGemCutter));
				}
			}

		}

	}

	@Override
	public void loadUsageRecipes (ItemStack ingredient) {

		for (int i : RecipeRegistry.getAllGemCutterRecipes().keySet()) {

			RecipeGemCutter recipeGemCutter = RecipeRegistry.getGemCutterRecipeByID(i);

			if (recipeGemCutter.getInput().isItemEqual(ingredient)) {
				if (checkDupe(recipeGemCutter)) {
					this.arecipes.add(new CachedCutterRecipe(recipeGemCutter));
				}
			}

		}

	}

	private boolean checkDupe (RecipeGemCutter recipe) {
		for (Object o : this.arecipes.toArray()) {
			if (o instanceof CachedCutterRecipe) {
				CachedCutterRecipe r = (CachedCutterRecipe) o;
				if (r.recipe.getInput().isItemEqual(recipe.getInput())) {
					if (r.recipe.getOutput().isItemEqual(recipe.getOutput())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public String getOverlayIdentifier () {
		return "gemCutter";
	}

	public class CachedCutterRecipe extends CachedRecipe
	{

		public RecipeGemCutter recipe;
		private PositionedStack output;
		private PositionedStack inputs;

		public CachedCutterRecipe (RecipeGemCutter recipe) {
			this.output = new PositionedStack(recipe.getOutput(), 143, 6);
			this.recipe = recipe;
			this.inputs = new PositionedStack(recipe.getInput(), 6, 6);
			output.item.stackSize = 1;
			inputs.item.stackSize = 1;


		}

		@Override
		public PositionedStack getResult () {
			return this.output;
		}

		@Override
		public PositionedStack getIngredient () {
			return this.inputs;
		}

	}

}
