package fluxedCrystals.util;

import cpw.mods.fml.common.FMLCommonHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedData;

import java.io.File;

public class SerializationHelper
{

	private static File dataDirectory;

	/**
	 * Returns a File reference to the mod specific directory in the data directory
	 *
	 * @return
	 */
	public static File getDataDirectory()
	{

		if(dataDirectory == null)
		{

			initModDataDirectories();

		}

		return dataDirectory;
	}

	/**
	 * Creates (if one does not exist already) and initializes a mod specific File reference inside of the current world's playerdata directory
	 */
	public static void initModDataDirectories()
	{

		dataDirectory = new File(FluxedCrystals.configDir .getAbsolutePath() + File.separator + "seedRegistry");
		dataDirectory.mkdirs();

	}

	public static SeedData readSeedDataFromFile(File dataDirectory, String fileName)
	{

		if (!dataDirectory.exists())
		{

			dataDirectory.mkdirs();

		}

		return SeedData.readFromFile(new File(dataDirectory, fileName));

	}

	public static void writeSeedDataToFile(File dataDirectory, String fileName, SeedData seedData)
	{

		if (dataDirectory != null && fileName != null)
		{

			if (!dataDirectory.exists())
			{

				dataDirectory.mkdirs();

			}

			if (seedData == null)
			{

				seedData = new SeedData();

			}

			try
			{

				File file1 = new File(dataDirectory, fileName + ".tmp");
				File file2 = new File(dataDirectory, fileName);

				SeedData.writeToFile(file1, seedData);

				if (file2.exists())
				{

					file2.delete();

				}

				file1.renameTo(file2);

				LogHelper.info(String.format("Successfully saved SeedData to file:  %s", file2.getAbsolutePath()));

			}
			catch (Exception e)
			{

				e.printStackTrace();

				LogHelper.error(String.format("Failed to save SeedData to file:  %s%s", dataDirectory.getAbsolutePath(), fileName));

			}

		}
		else
		{

			LogHelper.error("Failed to save seed data, directory is null?");

		}

	}

}
