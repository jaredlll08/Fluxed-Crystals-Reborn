package fluxedCrystals.registry;

import fluxedCrystals.util.LogHelper;
import fluxedCrystals.util.SerializationHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class SeedRegistry
{

	private static SeedRegistry seedRegistry = null;
	private static File seedRegistryDirectory;
	private static HashMap<Integer, SeedData> seedDataMap;

	private SeedRegistry()
	{

		seedRegistryDirectory = new File(SerializationHelper.getDataDirectory(), "seedData");
		seedRegistryDirectory.mkdirs();

		seedDataMap = new HashMap<Integer, SeedData>();

	}

	public static SeedRegistry getInstance()
	{

		if (seedRegistry == null)
		{

			seedRegistry = new SeedRegistry();

		}

		return seedRegistry;

	}

	public SeedData getSeedByID(int id)
	{

		loadSeedDataFromDisk(id);

		if (seedDataMap.containsKey(id))
		{

			return seedDataMap.get(id);

		}

		return new SeedData();

	}

	public void setSeedByID(int id, SeedData seedData, boolean hasBeenModified)
	{

		seedData.setHasBeenModified(hasBeenModified);

		if (seedDataMap.containsKey(id))
		{

			seedDataMap.remove(id);

		}

		seedDataMap.put(id, seedData);

		if (seedDataMap.containsKey(id) && getSeedByID(id).getName().equalsIgnoreCase(seedData.getName()))
		{
			LogHelper.info("Added seed!");
		}
		else
		{
			LogHelper.error("Did not add seed!");
		}

	}

	public void addSeed(SeedData seedData)
	{

		boolean exists = false;

		for (int i : seedDataMap.keySet())
		{

			if (getSeedByID(i).getName().equalsIgnoreCase(seedData.getName()) && seedData.getEntityID() != i)
			{

				exists = true;

				break;

			}

		}

		if (!exists)
		{

			setSeedByID(seedData.getEntityID(), seedData, true);

		}
		else
		{

			LogHelper.error("Did not add seed!");

		}

	}

	public int getNextID()
	{

		if (seedDataMap.isEmpty())
		{

			return 1;

		}

		SortedSet<Integer> keys = new TreeSet<Integer>(seedDataMap.keySet());

		for (int key = 1; key < Integer.MAX_VALUE; key++)
		{

			if (!seedDataMap.containsKey(key))
			{

				return key;

			}

		}

		return 1;

	}

	public void loadSeedDataFromDisk(int id)
	{

		if (seedRegistryDirectory != null && !seedDataMap.containsKey((id)))
		{

			SeedData seedData = new SeedData();

			File seedDataFile = new File(seedRegistryDirectory, Integer.toString(id) + ".json");

			if (seedDataFile.exists() && seedDataFile.isFile())
			{

				seedData = SerializationHelper.readSeedDataFromFile(seedRegistryDirectory, Integer.toString(id) + ".json");

			}

			setSeedByID(id, seedData, false);

		}

	}

	public void unloadSeedData(int id)
	{

		if (seedDataMap.containsKey(id))
		{

			saveSeedDataToDisk(id);

			seedDataMap.remove(id);

		}

	}

	public void saveSeedDataToDisk(int id)
	{

		if (seedRegistryDirectory != null && seedDataMap.containsKey((id)))
		{

			SerializationHelper.writeSeedDataToFile(seedRegistryDirectory, Integer.toString(id) + ".json", seedDataMap.get(id));

		}

	}

	public void SaveAll()
	{

		if (seedRegistryDirectory != null)
		{

			for (int key : seedDataMap.keySet())
			{

				saveSeedDataToDisk(key);

			}

		}

	}

	public void LoadAllFromDisk()
	{

		if (seedRegistryDirectory != null)
		{

			if(!seedRegistryDirectory.isDirectory())
			{

				LogHelper.error("seedRegistryDirectory is not directory");

			}

			LogHelper.info("seedRegistryDirectory:  " + seedRegistryDirectory.getPath());

			for(File file : seedRegistryDirectory.listFiles())
			{

				if (file.exists() && file.isFile() && file.getName().endsWith("json"))
				{

					SeedData seedData = SeedData.readFromFile(file);

					setSeedByID(seedData.getEntityID(), seedData, false);

					LogHelper.info("Loaded Seed From Disk:  " + seedData.getName());

				}

			}

		}

		if (seedDataMap.isEmpty())
		{

			LogHelper.info("seedDataMap contains " + Integer.toString(seedDataMap.size()) + " seeds");

			resetSeedRegistry();

		}
		else
		{

			LogHelper.info("seedDataMap contains " + Integer.toString(seedDataMap.size()) + " seeds");

		}

	}

	public void resetSeedRegistry()
	{

		this.seedDataMap.clear();

		ArrayList<SeedData> defaultSeeds = new ArrayList<SeedData>();

		defaultSeeds.add(new SeedData("Testy", 7507918, 1, 1, 600, 9001, 16, 2000, 1, 32, 0, true, "minecraft:bedrock;0", "minecraft:dirt"));
		defaultSeeds.add(new SeedData("Iron", 14071681, 1, 1, 2400, 1, 32, 4000, 1, 16, 1, true, "ingotIron", "ingotIron"));
		defaultSeeds.add(new SeedData("Gold", 11904556, 1, 1, 2400, 1, 32, 4000, 0, 20, 2, false, "ingotGold", ""));
		defaultSeeds.add(new SeedData("Coal", 788746, 1, 4, 1200, 0, 32, 2000, 0, 12, 3, false, "itemCoal", ""));
		defaultSeeds.add(new SeedData("Charcoal", 3418156, 1, 4, 1200, 0, 32, 2000, 0, 12, 4, false, "itemCharcoal", ""));
		defaultSeeds.add(new SeedData("Emerald", 6290199, 1, 1, 4800, 4, 32, 10000, 0, 24, 5, false, "gemEmerald", ""));
		defaultSeeds.add(new SeedData("Diamond", 5169900, 1, 1, 2400, 4, 32, 10000, 0, 24, 6, false, "gemDiamond", ""));
		defaultSeeds.add(new SeedData("Redstone", 16190746, 4, 8, 3600, 3, 32, 8000, 0, 8, 7, false, "ingotIron", ""));
		defaultSeeds.add(new SeedData("Ender", 5146997, 1, 1, 3600, 3, 16, 8000, 0, 12, 8, false, "pearlEnder", ""));
		defaultSeeds.add(new SeedData("Glowstone", 16766977, 1, 2, 1200, 0, 32, 2000, 0, 12, 9, false, "dustGlowstone", ""));
		defaultSeeds.add(new SeedData("Blaze", 15246103, 1, 1, 2400, 1, 32, 4000, 0, 8, 10, false, "itemBlazeRod", ""));
		defaultSeeds.add(new SeedData("Clay", 15066338, 1, 4, 1200, 0, 32, 2000, 0, 8, 11, false, "minecraft:clay_ball", ""));
		defaultSeeds.add(new SeedData("Flint", 7499373, 1, 1, 1200, 1, 32, 4000, 0, 8, 12, false, "itemFlint", ""));
		defaultSeeds.add(new SeedData("Ghast", 12371660, 1, 1, 2400, 2, 32, 4000, 0, 16, 13, false, "itemGhastTear", ""));
		defaultSeeds.add(new SeedData("Gunpowder", 5656657, 1, 1, 1200, 0, 32, 4000, 0, 12, 14, false, "dustGunpowder", ""));
		defaultSeeds.add(new SeedData("Leather", 16767832, 1, 1, 1200, 0, 32, 2000, 0, 8, 15, false, "itemLeather", ""));
		defaultSeeds.add(new SeedData("Quartz", 16641780, 1, 4, 3600, 3, 32, 8000, 0, 20, 16, false, "gemQuartz", ""));
		defaultSeeds.add(new SeedData("Slime", 12380500, 1, 1, 1200, 0, 32, 2000, 0, 8, 17, false, "slimeball", ""));

		for (SeedData seedData : defaultSeeds)
		{

			LogHelper.info("Attempting to add new default seed for:  " + seedData.getName());

			this.addSeed(seedData);

		}

		SaveAll();

	}

	public Set<Integer> keySet()
	{

		return seedDataMap.keySet();

	}

}
