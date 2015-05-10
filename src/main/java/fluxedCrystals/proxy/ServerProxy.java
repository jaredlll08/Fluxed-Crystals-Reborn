package fluxedCrystals.proxy;

import fluxedCrystals.recipe.RecipeRegistry;
import fluxedCrystals.recipe.RecipeSeedInfuser;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy
{

	@Override
	public ClientProxy getClientProxy()
	{

		return null;

	}

	@Override
	public World getClientWorld()
	{

		return null;

	}

	@Override
	public void registerRenderers()
	{
		
	}

	@Override
	public boolean isServer()
	{

		return true;

	}

	@Override
	public boolean isClient()
	{

		return false;

	}

	@Override
	public void postInit()
	{

		super.postInit();

		for(int i : RecipeRegistry.getAllSeedInfuserRecipes().keySet())
		{

			RecipeSeedInfuser recipeSeedInfuser = RecipeRegistry.getSeedInfuserRecipeByID(i);

		}

	}

}
