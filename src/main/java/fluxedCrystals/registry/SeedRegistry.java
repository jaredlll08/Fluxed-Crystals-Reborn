package fluxedCrystals.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.JsonTools;
import net.minecraft.item.ItemStack;
import org.apache.commons.io.FileUtils;
import tterrag.core.common.json.JsonUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SeedRegistry
{

	private static SeedRegistry seedRegistry = null;
	private static HashMap<Integer, Seed> seedMap;

	private SeedRegistry()
	{

		seedMap = new HashMap<Integer, Seed>();

	}

	public static SeedRegistry getInstance()
	{

		if (seedRegistry == null)
		{

			seedRegistry = new SeedRegistry();

		}

		return seedRegistry;

	}

	public Seed getSeedByID(int id)
	{

		if (seedMap.containsKey(id))
		{

			return seedMap.get(id);

		}

		return new Seed();

	}

	public Seed addTemplateSeed(ItemStack itemStack)
	{

		if (itemStack != null)
		{

			itemStack.stackSize = 1;

			Seed seed = new Seed();

			seed.seedID = SeedRegistry.getInstance().getNextID();
			seed.name = itemStack.getDisplayName();
			seed.ingredient = JsonUtils.getStringForItemStack(itemStack, true, false);
			seed.weightedDrop = "";

			return addSeed(seed);

		}

		return null;

	}

	private void addSeed(int id, Seed seed)
	{

		if (seedMap.containsKey(id))
		{

			seedMap.remove(id);

		}

		seedMap.put(id, seed);

	}

	public Seed addSeed(Seed seed)
	{

		boolean seedAdded = false;

		if(seedMap.containsKey(seed.seedID) && getSeedByID(seed.seedID).name.equalsIgnoreCase(seed.name))
		{

			//	Seed is there, treat as an update

			addSeed(seed.seedID, seed);

			seedAdded = true;

		}

		if (!seedMap.containsKey(seed.seedID))
		{

			// This is an insert and someone knew the right ID to pass

			addSeed(seed.seedID, seed);

			seedAdded = true;

		}

		if(seedMap.containsKey(seed.seedID) && !getSeedByID(seed.seedID).name.equalsIgnoreCase(seed.name))
		{

			//	Someone is attempted to insert a ?new? seed with an ID in use, check the name

			boolean exists = false;

			for (int i : seedMap.keySet())
			{

				if (getSeedByID(i).name.equalsIgnoreCase(seed.name))
				{

					exists = true;

					break;

				}

			}

			if (!exists)
			{

				// This is a new seed and someone did something stupid

				seed.seedID = getNextID();

				addSeed(seed.seedID, seed);

				seedAdded = true;

			}

		}

		if (seedAdded)
		{

			return seed;

		}

		return null;

	}

	public int getNextID()
	{

		if (seedMap.isEmpty())
		{

			return 1;

		}

		SortedSet<Integer> keys = new TreeSet<Integer>(seedMap.keySet());

		for (int key = 1; key < Integer.MAX_VALUE; key++)
		{

			if (!seedMap.containsKey(key))
			{

				return key;

			}

		}

		return 1;

	}

	public void Load()
	{

		File seedRegistryFile = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.json");

		if (seedRegistryFile == null || !seedRegistryFile.exists())
		{

			try
			{

				FileUtils.copyURLToFile(FluxedCrystals.class.getResource("/assets/" + Reference.LOWERCASE_MOD_ID + "/misc/crystal.json"), seedRegistryFile);

				seedRegistryFile = new File(FluxedCrystals.configDir .getAbsolutePath() + File.separator + "masterSeedData.json");

			}
			catch (IOException e)
			{

				throw new RuntimeException(e);

			}

		}

		ReadFromDisk(seedRegistryFile);

	}

	private void ReadFromDisk(File fileToRead)
	{

		if (fileToRead != null && fileToRead.exists())
		{

			try
			{

				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				JsonParser parser = new JsonParser();

				JsonObject jsonObject = parser.parse(new FileReader(fileToRead)).getAsJsonObject();

				for(Seed seed : JsonTools.jsontoList(jsonObject))
				{

					addSeed(seed);

				}

			}
			catch (FileNotFoundException ignored)
			{

				// NOOP

			}
			catch (IOException e)
			{

				e.printStackTrace();

			}

		}

	}

	public Set<Integer> keySet()
	{

		return seedMap.keySet();

	}

	public void Save()
	{

		Writer writer = null;

		try
		{

			writer = new FileWriter(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.tmp");
			writer.write(JsonTools.hashmapToJson(seedMap));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(writer != null)
			{
				try
				{
					writer.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		File file1 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.tmp");
		File file2 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterSeedData.json");

		if (file2.exists())
		{

			file2.delete();

		}

		file1.renameTo(file2);

	}

	public HashMap<Integer, Seed> getSeedMap()
	{

		return seedMap;

	}

}
