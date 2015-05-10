package fluxedCrystals.recipe;

import java.util.HashMap;

public class RecipeRegistry
{

	private static HashMap<Integer, RecipeSeedInfuser> seedInfuserRecipes = new HashMap<Integer, RecipeSeedInfuser>();
	private static HashMap<Integer, RecipeGemCutter> gemCutterRecipes = new HashMap<Integer, RecipeGemCutter>();

	public static void registerSeedInfuserRecipe(int seedID, RecipeSeedInfuser recipe)
	{

		if (!seedInfuserRecipes.containsKey(seedID))
		{

			seedInfuserRecipes.put(seedID, recipe);

		}

	}

	public static RecipeSeedInfuser getSeedInfuserRecipeByID(int seedID)
	{

		if (seedInfuserRecipes.containsKey(seedID))
		{

			return seedInfuserRecipes.get(seedID);

		}

		return null;

	}

	public static HashMap<Integer, RecipeSeedInfuser> getAllSeedInfuserRecipes()
	{

		return seedInfuserRecipes;

	}

	public static void registerGemCutterRecipe(int seedID, RecipeGemCutter recipe)
	{

		if (!gemCutterRecipes.containsKey(seedID))
		{

			gemCutterRecipes.put(seedID, recipe);

		}

	}

	public static RecipeGemCutter getGemCutterRecipeByID(int seedID)
	{

		if (gemCutterRecipes.containsKey(seedID))
		{

			return gemCutterRecipes.get(seedID);

		}

		return null;

	}

	public static HashMap<Integer, RecipeGemCutter> getAllGemCutterRecipes()
	{

		return gemCutterRecipes;

	}

}
