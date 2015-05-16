package fluxedCrystals.registry;

import net.minecraft.item.ItemStack;
import tterrag.core.common.json.JsonUtils;

import java.io.Serializable;
import java.util.Random;

public class Seed implements Serializable {

	private static final Random rand = new Random();
	public String name = "";
	public String lore = "";
	public String ingredient = "";
	public String weightedDrop = "";
	public int color = 7507918;
	public int dropMin = 1;
	public int dropMax = 1;
	public int growthTime = 600;
	public int tier = 9001;
	public int refinerOutput = 1;
	public int ingredientAmount = 16;
	public int powerPerStage = 2000;
	public int weigthedDropChance = 0;
	public int refinerAmount = 32;
	public int seedID = 0;
	public int seedReturn = 1;
	public boolean isSharp = true;
	public String modRequired = "";

	public ItemStack getIngredient() {

		if (ingredient != null && !ingredient.equals("")) {

			return JsonUtils.parseStringIntoItemStack(ingredient);

		}

		return null;

	}

	public ItemStack getWeightedDrop() {

		if (weightedDrop != null && !weightedDrop.equals("")) {

			return JsonUtils.parseStringIntoItemStack(weightedDrop);

		}

		return null;

	}

	public int getDropAmount() {

		return rand.nextInt(dropMax - dropMin + 1) + dropMin;

	}

}