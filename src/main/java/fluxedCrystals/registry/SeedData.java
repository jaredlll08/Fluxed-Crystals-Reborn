package fluxedCrystals.registry;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import fluxedCrystals.reference.Names;
import fluxedCrystals.util.INBTTaggable;
import tterrag.core.common.json.JsonUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.io.*;
import java.lang.reflect.Type;

public class SeedData implements INBTTaggable, JsonSerializer<SeedData>, JsonDeserializer<SeedData>
{

	private static final Gson jsonSerializer = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(SeedData.class, new SeedData()).create();
	private boolean hasBeenModified;

	private String name, lore;
	private int color, dropMin, dropMax, growthTime, tier, ingredientAmount, powerPerStage, weightedDropChance, refinerAmount, entityID;
	private boolean decorationBlocks;
	private ItemStack ingredient, weightedDrop;

	public SeedData()
	{

		this("Testy", 7507918, 1, 1, 600, 9001, 16, 2000, 1, 32, 0, true, "minecraft:bedrock;0", "minecraft:dirt");

	}

	public SeedData(String name, int color, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount,
					int powerPerStage, int weightedDropChance, int refinerAmount, int entityID, boolean decorationBlocks,
					String ingredient, String weightedDrop)
	{

		this(name, color, dropMin, dropMax, growthTime, tier, ingredientAmount, powerPerStage, weightedDropChance, refinerAmount, entityID, decorationBlocks, ingredient, weightedDrop, "");

	}

	public SeedData(String name, int color, int dropMin, int dropMax, int growthTime, int tier, int ingredientAmount,
					int powerPerStage, int weightedDropChance, int refinerAmount, int entityID, boolean decorationBlocks,
					String ingredient, String weightedDrop, String lore)
	{

		if (ingredient == "")
		{
			this.ingredient = null;
		}
		else
		{
			this.ingredient = JsonUtils.parseStringIntoItemStack(ingredient);
		}

		if (weightedDrop == "")
		{
			this.weightedDrop = null;
			this.weightedDropChance = 0;
		}
		else
		{
			this.weightedDrop = JsonUtils.parseStringIntoItemStack(weightedDrop);
			this.weightedDropChance = weightedDropChance;
		}

		this.dropMin = (dropMin > 0 ? dropMin : 1);
		this.dropMax = (dropMax < this.dropMin ? this.dropMin : (dropMax > 0 ? dropMax : 1));

		this.color = color;
		this.decorationBlocks = decorationBlocks;
		this.growthTime = growthTime;
		this.tier = tier;
		this.ingredientAmount = ingredientAmount;
		this.powerPerStage = powerPerStage;
		this.refinerAmount = refinerAmount;
		this.entityID = entityID;
		this.name = name;
		this.lore = lore;

		this.hasBeenModified = false;

	}

	public boolean ishasBeenModified()
	{

		return this.hasBeenModified;

	}

	public void setHasBeenModified(boolean hasBeenModified)
	{
		this.hasBeenModified = hasBeenModified;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String name)
	{
		this.name = name;
		this.hasBeenModified = true;
	}

	public ItemStack getWeightedDrop ()
	{
		return weightedDrop;
	}

	public void setWeightedDrop (ItemStack weightedDrop)
	{
		this.weightedDrop = weightedDrop;
		this.hasBeenModified = true;
	}

	public int getColor ()
	{
		return color;
	}

	public void setColor (int color)
	{
		this.color = color;
		this.hasBeenModified = true;
	}

	public int getDropMin ()
	{
		return dropMin;
	}

	public void setDropMin (int dropMin)
	{
		this.dropMin = dropMin;
		this.hasBeenModified = true;
	}

	public int getDropMax ()
	{
		return dropMax;
	}

	public void setDropMax (int dropMax)
	{
		this.dropMax = dropMax;
		this.hasBeenModified = true;
	}

	public int getGrowthTime ()
	{
		return growthTime;
	}

	public void setGrowthTime (int growthTime)
	{
		this.growthTime = growthTime;
		this.hasBeenModified = true;
	}

	public int getTier ()
	{
		return tier;
	}

	public void setTier (int tier)
	{
		this.tier = tier;
		this.hasBeenModified = true;
	}

	public int getIngredientAmount ()
	{
		return ingredientAmount;
	}

	public void setIngredientAmount (int ingredientAmount)
	{
		this.ingredientAmount = ingredientAmount;
		this.hasBeenModified = true;
	}

	public int getPowerPerStage ()
	{
		return powerPerStage;
	}

	public void setPowerPerStage (int powerPerStage)
	{
		this.powerPerStage = powerPerStage;
		this.hasBeenModified = true;
	}

	public int getWeightedDropChance ()
	{
		return weightedDropChance;
	}

	public void setWeightedDropChance (int weightedDropChance)
	{
		this.weightedDropChance = weightedDropChance;
		this.hasBeenModified = true;
	}

	public int getRefinerAmount ()
	{
		return refinerAmount;
	}

	public void setRefinerAmount (int refinerAmount)
	{
		this.refinerAmount = refinerAmount;
		this.hasBeenModified = true;
	}

	public int getEntityID ()
	{
		return entityID;
	}

	public void setEntityID (int entityID)
	{
		this.entityID = entityID;
		this.hasBeenModified = true;
	}

	public ItemStack getIngredient ()
	{
		return ingredient;
	}

	public void setIngredient (ItemStack ingredient)
	{
		this.ingredient = ingredient;
		this.hasBeenModified = true;
	}

	public boolean isDecorationBlocks ()
	{
		return decorationBlocks;
	}

	public void setDecorationBlocks (boolean decorationBlocks)
	{
		this.decorationBlocks = decorationBlocks;
		this.hasBeenModified = true;
	}

	public String getLore()
	{

		return this.lore;

	}

	public void setLore(String lore)
	{

		this.lore = lore;

	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{

		if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.SEED_DATA))
		{

			NBTTagCompound seed = (NBTTagCompound)nbtTagCompound.getTag(Names.NBT.SEED_DATA);

			this.color = seed.getInteger("color");
			this.decorationBlocks = seed.getBoolean("decorationBlocks");
			this.dropMax = seed.getInteger("dropMax");
			this.dropMin = seed.getInteger("dropMin");
			this.growthTime = seed.getInteger("growthTime");
			this.tier = seed.getInteger("tier");
			this.ingredientAmount = seed.getInteger("ingredientAmount");
			this.powerPerStage = seed.getInteger("powerPerStage");
			this.weightedDropChance = seed.getInteger("weightedDropChance");
			this.refinerAmount = seed.getInteger("refinerAmount");
			this.entityID = seed.getInteger("entityID");
			this.ingredient = JsonUtils.parseStringIntoItemStack(seed.getString("ingredient"));
			this.name = seed.getString("name");
			this.weightedDrop = JsonUtils.parseStringIntoItemStack(seed.getString("weightedDrop"));
			this.lore = seed.getString("lore");

		}
		else
		{

			this.color = 0;
			this.decorationBlocks = false;
			this.dropMin = 0;
			this.dropMax = 0;
			this.growthTime = 0;
			this.tier = 0;
			this.ingredientAmount = 0;
			this.powerPerStage = 0;
			this.weightedDropChance = 0;
			this.refinerAmount = 0;
			this.entityID = 0;
			this.ingredient = JsonUtils.parseStringIntoItemStack("minecraft:dirt");
			this.name = "";
			this.lore = "";
			this.weightedDrop = JsonUtils.parseStringIntoItemStack("minecraft:dirt");

		}

	}

	@Override
	public void writeToNBT (NBTTagCompound nbtTagCompound)
	{

		NBTTagCompound seed = new NBTTagCompound();

		seed.setInteger("color", this.color);
		seed.setBoolean("decorationBlocks", this.decorationBlocks);
		seed.setInteger("dropMax", this.dropMax);
		seed.setInteger("dropMin", this.dropMin);
		seed.setInteger("growthTime", this.growthTime);
		seed.setInteger("tier", this.tier);
		seed.setInteger("ingredientAmount", this.ingredientAmount);
		seed.setInteger("powerPerStage", this.powerPerStage);
		seed.setInteger("weightedDropChance", this.weightedDropChance);
		seed.setInteger("refinerAmount", this.refinerAmount);
		seed.setInteger("entityID", this.entityID);
		seed.setString("ingredient", JsonUtils.getStringForItemStack(this.ingredient, true, true));
		seed.setString("name", this.name);
		seed.setString("lore", this.lore);
		seed.setString("weightedDrop", JsonUtils.getStringForItemStack(this.weightedDrop, true, true));

	}

	@Override
	public String getTagLabel() { return "SeedData"; }

	@Override
	public String toString()
	{

		return serialize(this).toString();

	}

	public static SeedData readFromFile(File file)
	{

		JsonReader jsonReader;

		try
		{

			jsonReader = new JsonReader(new FileReader(file));

			SeedData playerSpirit = jsonSerializer.fromJson(jsonReader, SeedData.class);

			jsonReader.close();

			return playerSpirit;

		}
		catch (FileNotFoundException ignored)
		{

			// NOOP

		}
		catch(IOException e)
		{

			e.printStackTrace();

		}

		return null;

	}

	public static void writeToFile(File file, SeedData seedData)
	{

		JsonWriter jsonWriter;

		try
		{

			jsonWriter = new JsonWriter(new FileWriter(file));

			jsonWriter.setIndent("    ");

			jsonSerializer.toJson(seedData, SeedData.class, jsonWriter);

			jsonWriter.close();

			seedData.hasBeenModified = false;

		}
		catch (IOException e)
		{

			e.printStackTrace();

		}

	}

	@Override
	public JsonElement serialize(SeedData seedData, Type typeOfSrc, JsonSerializationContext context)
	{

		return serialize(seedData);

	}

	private JsonElement serialize(SeedData seedData)
	{

		JsonObject jsonObject = new JsonObject();

		jsonObject.addProperty("color", seedData.color);
		jsonObject.addProperty("decorationBlocks", seedData.decorationBlocks);
		jsonObject.addProperty("dropMax", seedData.dropMax);
		jsonObject.addProperty("dropMin", seedData.dropMin);
		jsonObject.addProperty("growthTime", seedData.growthTime);
		jsonObject.addProperty("tier", seedData.tier);
		jsonObject.addProperty("ingredientAmount", seedData.ingredientAmount);
		jsonObject.addProperty("powerPerStage", seedData.powerPerStage);
		jsonObject.addProperty("weightedDropChance", seedData.weightedDropChance);
		jsonObject.addProperty("refinerAmount", seedData.refinerAmount);
		jsonObject.addProperty("entityID", seedData.entityID);
		jsonObject.addProperty("name", seedData.name);
		jsonObject.addProperty("lore", seedData.lore);
		jsonObject.addProperty("ingredient", JsonUtils.getStringForItemStack(seedData.ingredient, true, true));
		jsonObject.addProperty("weightedDrop", JsonUtils.getStringForItemStack(seedData.weightedDrop, true, true));

		return jsonObject;

	}

	@Override
	public SeedData deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{

		if (jsonElement.isJsonObject())
		{

			JsonObject jsonObject = (JsonObject) jsonElement;

			String name = "", ingredient = "", weightedDrop = "";
			int color = 0, dropMin = 0, dropMax = 0, growthTime = 0, tier = 0, ingredientAmount = 0, powerPerStage = 0, weightedDropChance = 0, refinerAmount = 0, entityID = 0;
			boolean decorationBlocks = false;

			if(jsonObject.has("color") && jsonObject.get("color").isJsonPrimitive()) {
				color = jsonObject.get("color").getAsInt();
			}
			if(jsonObject.has("dropMax") && jsonObject.get("dropMax").isJsonPrimitive()) {
				dropMax = jsonObject.get("dropMax").getAsInt();
			}
			if(jsonObject.has("dropMin") && jsonObject.get("dropMin").isJsonPrimitive()) {
				dropMin = jsonObject.get("dropMin").getAsInt();
			}
			if(jsonObject.has("growthTime") && jsonObject.get("growthTime").isJsonPrimitive()) {
				growthTime = jsonObject.get("growthTime").getAsInt();
			}
			if(jsonObject.has("tier") && jsonObject.get("tier").isJsonPrimitive()) {
				tier = jsonObject.get("tier").getAsInt();
			}
			if(jsonObject.has("decorationBlocks") && jsonObject.get("decorationBlocks").isJsonPrimitive()) {
				decorationBlocks = jsonObject.get("decorationBlocks").getAsBoolean();
			}
			if(jsonObject.has("ingredientAmount") && jsonObject.get("ingredientAmount").isJsonPrimitive()) {
				ingredientAmount = jsonObject.get("ingredientAmount").getAsInt();
			}
			if(jsonObject.has("powerPerStage") && jsonObject.get("powerPerStage").isJsonPrimitive()) {
				powerPerStage = jsonObject.get("powerPerStage").getAsInt();
			}
			if(jsonObject.has("weightedDropChance") && jsonObject.get("weightedDropChance").isJsonPrimitive()) {
				weightedDropChance = jsonObject.get("weightedDropChance").getAsInt();
			}
			if(jsonObject.has("refinerAmount") && jsonObject.get("refinerAmount").isJsonPrimitive()) {
				refinerAmount = jsonObject.get("refinerAmount").getAsInt();
			}
			if(jsonObject.has("entityID") && jsonObject.get("entityID").isJsonPrimitive()) {
				entityID = jsonObject.get("entityID").getAsInt();
			}
			if(jsonObject.has("name") && jsonObject.get("name").isJsonPrimitive()) {
				name = jsonObject.get("name").getAsString();
			}
			if(jsonObject.has("lore") && jsonObject.get("lore").isJsonPrimitive()) {
				name = jsonObject.get("lore").getAsString();
			}
			if(jsonObject.has("ingredient") && jsonObject.get("ingredient").isJsonPrimitive()) {
				ingredient = jsonObject.get("ingredient").getAsString();
			}
			if(jsonObject.has("weightedDrop") && jsonObject.get("weightedDrop").isJsonPrimitive()) {
				weightedDrop = jsonObject.get("weightedDrop").getAsString();
			}

			return new SeedData(name, color, dropMin, dropMax, growthTime, tier, ingredientAmount, powerPerStage, weightedDropChance, refinerAmount, entityID, decorationBlocks, ingredient, weightedDrop);

		}

		return null;

	}

	public String toJson()
	{

		return jsonSerializer.toJson(this);

	}

	public static SeedData createFromJson(String jsonSeedData) throws JsonParseException
	{

		try
		{

			return jsonSerializer.fromJson(jsonSeedData, SeedData.class);

		}
		catch (JsonSyntaxException exception)
		{

			exception.printStackTrace();

		}
		catch (JsonParseException exception)
		{

			exception.printStackTrace();

		}

		return null;

	}

}
