package fluxedCrystals.registry;

import net.minecraft.item.ItemStack;
import tterrag.core.common.json.JsonUtils;

import java.io.Serializable;

public class Seed implements Serializable
{

	public String name = "Testy";
	public String lore = "";
	public String ingredient = "minecraft:bedrock;0";
	public String weightedDrop = "minecraft:dirt";
	public int color = 7507918;
	public int dropMin = 1;
	public int dropMax = 1;
	public int growthTime = 600;
	public int tier = 9001;
	public int ingredientAmount = 16;
	public int powerPerStage = 2000;
	public int weigthedDropChance = 1;
	public int refinerAmount = 32;
	public int seedID = 0;
	public int entityID = 0;
	public boolean decorationBlock = false;

	public ItemStack getIngredient()
	{

		if (ingredient != null && ingredient != "")
		{

			return JsonUtils.parseStringIntoItemStack(ingredient);

		}

		return null;

	}

	public ItemStack getWeightedDrop()
	{

		if (weightedDrop != null && weightedDrop != "")
		{

			return JsonUtils.parseStringIntoItemStack(weightedDrop);

		}

		return null;

	}

}