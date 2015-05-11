package fluxedCrystals.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import fluxedCrystals.recipe.RecipeGemRefiner;
import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GemRefinerHandler extends TemplateRecipeHandler
{

	@Override
	public String getGuiTexture() {
		return Reference.LOWERCASE_MOD_ID + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName() {
		return "Gem Refiner";
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	private final ResourceLocation texture = new ResourceLocation(getGuiTexture());

	@Override
	public void drawBackground(int recipe) {

		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(5, 5, 0, 166, 18, 18);
		GuiDraw.drawTexturedModalRect(142, 5, 0, 166, 18, 18);

		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void drawForeground(int recipe) {
		super.drawForeground(recipe);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedRefinerRecipe r = (CachedRefinerRecipe) arecipes.get(recipe);
		int coords2[] = { 0, 0 };
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		for(int i : RecipeRegistry.getAllGemRefinerRecipes().keySet())
		{

			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(i);

			if (recipe.getOutput().isItemEqual(result)) {
				if (checkDupe(recipe))
					this.arecipes.add(new CachedRefinerRecipe(recipe));
			}

		}

	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		for(int i : RecipeRegistry.getAllGemRefinerRecipes().keySet())
		{

			RecipeGemRefiner recipe = RecipeRegistry.getGemRefinerRecipeByID(i);

			if (recipe.getInput().isItemEqual(ingredient)) {
				if (checkDupe(recipe))
					this.arecipes.add(new CachedRefinerRecipe(recipe));
			}

		}

	}

	private boolean checkDupe(RecipeGemRefiner recipe) {
		for (Object o : this.arecipes.toArray()) {
			if (o instanceof CachedRefinerRecipe) {
				CachedRefinerRecipe r = (CachedRefinerRecipe) o;
				if (r.recipe.getInput().isItemEqual(recipe.getInput())) {
					if (r.recipe.getOutput().isItemEqual(recipe.getOutput())) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public class CachedRefinerRecipe extends CachedRecipe
	{

		private PositionedStack output;
		private PositionedStack inputs;

		public RecipeGemRefiner recipe;

		public CachedRefinerRecipe(RecipeGemRefiner recipe) {
			this.output = new PositionedStack(recipe.getOutput(), 143, 6);
			this.recipe = recipe;
			this.inputs = new PositionedStack(recipe.getInput(), 6, 6);
			inputs.item.stackSize = recipe.getInputamount();

		}

		@Override
		public PositionedStack getResult() {
			return this.output;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.inputs;
		}

	}

	@Override
	public String getOverlayIdentifier() {
		return "gemRefiner";
	}

}
