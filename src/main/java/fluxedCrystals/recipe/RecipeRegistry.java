package fluxedCrystals.recipe;

import java.util.HashMap;

public class RecipeRegistry
{

	private static HashMap<Integer, RecipeSeedInfuser> seedRecipes = new HashMap<Integer, RecipeSeedInfuser>();

	public static void registerSeedInfuserRecipe(int seedID, RecipeSeedInfuser recipe)
	{

		if (!seedRecipes.containsKey(seedID))
		{

			seedRecipes.put(seedID, recipe);

		}

	}

	public static RecipeSeedInfuser getSeedInfuserRecipeByID(int seedID)
	{

		if (seedRecipes.containsKey(seedID))
		{

			return seedRecipes.get(seedID);

		}

		return null;

	}

	public static HashMap<Integer, RecipeSeedInfuser> getAllSeedInfuserRecipes()
	{

		return seedRecipes;

	}

}
