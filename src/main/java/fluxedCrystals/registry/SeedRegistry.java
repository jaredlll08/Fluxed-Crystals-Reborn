package fluxedCrystals.registry;

import com.google.gson.*;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCItems;
import fluxedCrystals.recipe.*;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.JsonTools;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class SeedRegistry
{

	private static SeedRegistry seedRegistry = null;
	private static HashMap<Integer, Seed> seedMap;

	private SeedRegistry () {

		seedMap = new HashMap<Integer, Seed>();

	}

	public static SeedRegistry getInstance () {

		if (seedRegistry == null) {

			seedRegistry = new SeedRegistry();

		}

		return seedRegistry;

	}

	public Seed getSeedByID (int id) {

		if (seedMap.containsKey(id)) {

			return seedMap.get(id);

		}

		return new Seed();

	}

	public int getIdFromName (String name) {
		for (Seed seed : seedMap.values()) {
			if (seed.name.equals(name)) {
				return seed.seedID;
			}

		}
		return -1;
	}

	public Seed getSeedFromName (String name) {
		for (Seed seed : seedMap.values()) {
			if (seed.name.equals(name)) {
				return seed;
			}
		}
		return new Seed();
	}


	public Seed addTemplateSeed (ItemStack itemStack) {

		if (itemStack != null) {

			Seed seed = new Seed();

			seed.seedID = SeedRegistry.getInstance().getNextID();
			seed.name = itemStack.getDisplayName();
			seed.ingredient = JsonTools.getStringForItemStack(itemStack, true, false);
			seed.weightedDrop = JsonTools.getStringForItemStack(itemStack, true, true);
			seed.dropMax = 8;
			seed.dropMin = 3;
			seed.ingredientAmount = itemStack.stackSize;
			seed.isSharp = true;
			String mod = JsonTools.getStringForItemStack(itemStack, false, false).split(":")[0];
			if (mod != null && !mod.equalsIgnoreCase("minecraft")) {
				seed.modRequired = mod;
			}
			seed.seedReturn = 1;
			seed = addSeed(seed);
			if (seed != null) {

				RecipeRegistry.registerSeedInfuserRecipe(seed.seedID, new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), seed.getIngredient(), new ItemStack(FCItems.seed, 1, seed.seedID), seed.ingredientAmount, seed.seedID));

				RecipeRegistry.registerGemCutterRecipe(seed.seedID, new RecipeGemCutter(new ItemStack(FCItems.shardRough, 1, seed.seedID), new ItemStack(FCItems.shardSmooth, 1, seed.seedID), 1, 1));

				if (seed.weightedDrop != null && !seed.weightedDrop.equals("")) {

					if (!(Block.getBlockFromName("minecraft:portal") == Block.getBlockFromItem(seed.getWeightedDrop().getItem()))) {

						RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getWeightedDrop(), seed.refinerAmount, seed.refinerOutput));

					}
					else {

						RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getIngredient(), seed.refinerAmount, seed.refinerOutput));

					}

				}
				else {

					RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getIngredient(), seed.refinerAmount, seed.refinerOutput));

				}

			}

			return seed;

		}

		return null;

	}

	public Seed addTemplateSeed (Seed seed) {

		seed = addSeed(seed);
		if (seed != null) {

			RecipeRegistry.registerSeedInfuserRecipe(seed.seedID, new RecipeSeedInfuser(new ItemStack(FCItems.universalSeed), seed.getIngredient(), new ItemStack(FCItems.seed, 1, seed.seedID), seed.ingredientAmount, seed.seedID));

			RecipeRegistry.registerGemCutterRecipe(seed.seedID, new RecipeGemCutter(new ItemStack(FCItems.shardRough, 1, seed.seedID), new ItemStack(FCItems.shardSmooth, 1, seed.seedID), 1, 1));

			if (seed.weightedDrop != null && !seed.weightedDrop.equals("")) {

				if (!(Block.getBlockFromName("minecraft:portal") == Block.getBlockFromItem(seed.getWeightedDrop().getItem()))) {

					RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getWeightedDrop(), seed.refinerAmount, seed.refinerOutput));

				}
				else {

					RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getIngredient(), seed.refinerAmount, seed.refinerOutput));

				}

			}
			else {

				RecipeRegistry.registerGemRefinerRecipe(seed.seedID, new RecipeGemRefiner(new ItemStack(FCItems.shardSmooth, 1, seed.seedID), seed.getIngredient(), seed.refinerAmount, seed.refinerOutput));

			}

		}

		return seed;

	}

	private void addSeed (int id, Seed seed) {

		if (seedMap.containsKey(id)) {

			seedMap.remove(id);

		}

		seedMap.put(id, seed);

	}

	public Seed addSeed (Seed seed) {

		boolean seedAdded = false;

		if (seedMap.containsKey(seed.seedID) && getSeedByID(seed.seedID).name.equalsIgnoreCase(seed.name)) {

			// Seed is there, treat as an update

			addSeed(seed.seedID, seed);

			seedAdded = true;

		}

		if (!seedMap.containsKey(seed.seedID)) {

			// This is an insert and someone knew the right ID to pass

			addSeed(seed.seedID, seed);

			seedAdded = true;

		}

		if (seedMap.containsKey(seed.seedID) && !getSeedByID(seed.seedID).name.equalsIgnoreCase(seed.name)) {

			// Someone is attempted to insert a ?new? seed with an ID in use,
			// check the name

			boolean exists = false;

			for (int i : seedMap.keySet()) {

				if (getSeedByID(i).name.equalsIgnoreCase(seed.name)) {

					exists = true;

					break;

				}

			}

			if (!exists) {

				// This is a new seed and someone did something stupid

				seed.seedID = getNextID();

				addSeed(seed.seedID, seed);

				seedAdded = true;

			}

		}

		if (seedAdded) {

			return seed;

		}

		return null;

	}

	public int getNextID () {

		if (seedMap.isEmpty()) {

			return 1;

		}

		for (int key = 1; key < Integer.MAX_VALUE; key++) {

			if (!seedMap.containsKey(key)) {

				return key;

			}

		}

		return 1;

	}

	public void Load () {

		File seedRegistryFile = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.json");

		//noinspection ConstantConditions
		if (seedRegistryFile == null || !seedRegistryFile.exists()) {

			try {

				FileUtils.copyURLToFile(FluxedCrystals.class.getResource("/assets/" + Reference.LOWERCASE_MOD_ID + "/misc/crystal.json"), seedRegistryFile);

				seedRegistryFile = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.json");

			}
			catch (IOException e) {

				throw new RuntimeException(e);

			}

		}

		ReadFromDisk(seedRegistryFile);

	}

	private void ReadFromDisk (File fileToRead) {

		if (fileToRead != null && fileToRead.exists()) {

			try {

				 Gson gson = new GsonBuilder().setPrettyPrinting().create();

				JsonParser parser = new JsonParser();

				JsonObject jsonObject = parser.parse(new FileReader(fileToRead)).getAsJsonObject();

				for (Seed seed : JsonTools.jsontoList_seeds(jsonObject)) {

					addSeed(seed);

				}

			}
			catch (FileNotFoundException ignored) {

				// NOOP

			}
			catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	public Set<Integer> keySet () {

		return seedMap.keySet();

	}

	public void Save () {/*

		Writer writer = null;

		try {

			writer = new FileWriter(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.tmp");
			writer.write(JsonTools.hashmapToJson_seeds(seedMap));

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		File file1 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.tmp");
		File file2 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.json");

		if (file2.exists()) {

			//noinspection ResultOfMethodCallIgnored
			file2.delete();

		}

		//noinspection ResultOfMethodCallIgnored
		file1.renameTo(file2);

	*/}

	public HashMap<Integer, Seed> getSeedMap () {

		return seedMap;

	}

}
