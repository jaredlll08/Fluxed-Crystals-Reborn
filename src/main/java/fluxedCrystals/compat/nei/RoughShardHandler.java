package fluxedCrystals.compat.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.Optional;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Optional.Interface(iface = "codechicken.nei.api.API", modid = "NotEnoughItems")
public class RoughShardHandler extends TemplateRecipeHandler
{

	private final ResourceLocation texture = new ResourceLocation(getGuiTexture());

	@Override
	public String getGuiTexture () {
		return Reference.LOWERCASE_MOD_ID + ":textures/gui/SeedInfuser.png";
	}

	@Override
	public String getRecipeName () {
		return "Rough Shard";
	}

	@Override
	public int recipiesPerPage () {
		return 1;
	}

	@Override
	public void drawBackground (int recipe) {
		GuiDraw.drawTexturedModalRect(5, 5, 0, 166, 18, 18);
		GuiDraw.fontRenderer.drawSplitString("You get Rough Shards by breaking Crystals in the world.", 19, 38, 125, 0);
	}

	@Override
	public void drawExtras (int recipe) {
		CachedShard r = (CachedShard) arecipes.get(recipe);
//		int coords2[] = {0, 0};
		GL11.glScalef(.08f, .08f, .08f);
		GL11.glEnable(GL11.GL_BLEND);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glScalef(15.625f, 15.625f, 15.625f);
	}

	@Override
	public void loadCraftingRecipes (ItemStack result) {

		for (int i : SeedRegistry.getInstance().getSeedMap().keySet()) {

			Seed recipe = SeedRegistry.getInstance().getSeedByID(i);
			if (new ItemStack(FCItems.shardRough, 0, i).isItemEqual(result)) {
				if (checkDupe(recipe)) {
					this.arecipes.add(new CachedShard(i));
				}
			}

		}

	}

	private boolean checkDupe (Seed recipe) {
		for (Object o : this.arecipes.toArray()) {
			if (o instanceof Seed) {
				Seed r = (Seed) o;
				if (r.seedID == recipe.seedID) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String getOverlayIdentifier () {
		return "seedInfuser";
	}

	private class CachedShard extends CachedRecipe
	{
		int index;

		public CachedShard (int index) {
			this.index = index;
		}

		@Override
		public PositionedStack getResult () {
			return new PositionedStack(new ItemStack(FCItems.shardRough, 1, index), 75, 5);
		}

	}

}
