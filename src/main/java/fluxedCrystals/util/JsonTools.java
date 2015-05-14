package fluxedCrystals.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fluxedCrystals.registry.Seed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTools {

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

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

}
