package fluxedCrystals.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fluxedCrystals.registry.Seed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTools {

	public static final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

	public static String hashmapToJson(HashMap<Integer, Seed> seedMap) {

		String tmpString = "{\"seeds\":[";

		for (int i : seedMap.keySet()) {

			if (i > 0) {

				tmpString += ",";

			}

			tmpString += gson.toJson(seedMap.get(i), Seed.class);

		}

		tmpString += "] }";

		return tmpString;

	}

	public static List<Seed> jsontoList(JsonObject jsonObject) {

		List<Seed> list = new ArrayList<Seed>();

		if (jsonObject.has("seeds") && jsonObject.get("seeds").isJsonArray()) {

			// Read the seeds from the JSON

			JsonArray jsonArray = (JsonArray) jsonObject.get("seeds");

			for (int i = 0; i < jsonArray.size(); i++) {

				list.add(gson.fromJson(jsonArray.get(i), Seed.class));

			}

		}

		return list;

	}

	public static Object parseStringIntoRecipeItem(String string) {
		return parseStringIntoRecipeItem(string, false);
	}

	public static Object parseStringIntoRecipeItem(String string, boolean forceItemStack) {
		if("null".equals(string)) {
			return null;
		} else if(OreDictionary.getOres(string).isEmpty()) {
			ItemStack stack = null;
			String[] info = string.split(";");
			Object temp = null;
			int damage = 32767;
			temp = Item.itemRegistry.getObject(info[0]);
			if(info.length > 1) {
				damage = Integer.parseInt(info[1]);
			}

			if(temp instanceof Item) {
				stack = new ItemStack((Item)temp, 1, damage);
			} else if(temp instanceof Block) {
				stack = new ItemStack((Block)temp, 1, damage);
			} else {
				if(!(temp instanceof ItemStack)) {
					throw new IllegalArgumentException(string + " is not a vaild string. Strings should be either an oredict name, or in the format objectname;damage (damage is optional)");
				}

				stack = ((ItemStack)temp).copy();
				stack.setItemDamage(damage);
			}

			return stack;
		} else {
			return forceItemStack? OreDictionary.getOres(string).get(0).copy():string;
		}
	}

	public static ItemStack parseStringIntoItemStack(String string) {
		int size = 1;
		int idx = string.indexOf(35);
		if(idx != -1) {
			String stack = string.substring(idx + 1);

			try {
				size = Integer.parseInt(stack);
			} catch (NumberFormatException var5) {
				throw new IllegalArgumentException(stack + " is not a valid stack size");
			}

			string = string.substring(0, idx);
		}

		ItemStack stack1 = (ItemStack)parseStringIntoRecipeItem(string, true);
		stack1.stackSize = MathHelper.clamp_int(size, 1, stack1.getMaxStackSize());
		return stack1;
	}

	public static String getStringForItemStack(ItemStack stack, boolean damage, boolean size) {
		if(stack == null) {
			return null;
		} else {
			String base = Item.itemRegistry.getNameForObject(stack.getItem());
			if(damage) {
				base = base + ";" + stack.getItemDamage();
			}

			if(size) {
				base = base + "#" + stack.stackSize;
			}

			return base;
		}
	}

}
