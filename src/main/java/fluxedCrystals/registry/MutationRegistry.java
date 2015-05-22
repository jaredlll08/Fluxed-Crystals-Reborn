package fluxedCrystals.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.JsonTools;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 5/20/2015.
 */
public class MutationRegistry {
	private static MutationRegistry mutationRegistry = null;
	private static Map<MutablePair<Seed, Seed>, Mutation> mutationMap;

	public MutationRegistry()
	{

		mutationMap = new HashMap<MutablePair<Seed, Seed>, Mutation>();

	}

	public static MutationRegistry getInstance() {

		if (mutationRegistry == null) {

			return new MutationRegistry();

		}

		return mutationRegistry;

	}

	public void addMutation(String output, String seed1, String seed2) {

		mutationMap.put(new MutablePair<Seed, Seed>(SeedRegistry.getInstance().getSeedFromName(seed1), SeedRegistry.getInstance().getSeedFromName(seed2)), new Mutation(output, seed1, seed2));
	}


	public static Mutation getMutationFromNames(String name1, String name2) {
		MutablePair<Seed, Seed> seed = new MutablePair<Seed, Seed>();
		seed.setLeft(SeedRegistry.getInstance().getSeedFromName(name1));
		seed.setRight(SeedRegistry.getInstance().getSeedFromName(name2));

		if (mutationMap.containsKey(seed)) {
			return mutationMap.get(seed);
		}

		return null;
	}

	public void Load()
	{

		File mutationRegistryFile = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterMutationData.json");

		if (mutationRegistryFile == null || !mutationRegistryFile.exists()) {

			try {

				FileUtils.copyURLToFile(FluxedCrystals.class.getResource("/assets/" + Reference.LOWERCASE_MOD_ID + "/misc/mutation.json"), mutationRegistryFile);

				mutationRegistryFile = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterMutationData.json");

			} catch (IOException e) {

				throw new RuntimeException(e);

			}

		}

		ReadFromDisk(mutationRegistryFile);

	}

	private void ReadFromDisk(File fileToRead)
	{

		if (fileToRead != null && fileToRead.exists()) {

			try {

				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				JsonParser parser = new JsonParser();

				JsonObject jsonObject = parser.parse(new FileReader(fileToRead)).getAsJsonObject();

				for (Mutation mutation : JsonTools.jsontoList_mutations(jsonObject))
				{

					addMutation(mutation.outputSeed, mutation.seed1, mutation.seed2);

				}

			} catch (FileNotFoundException ignored) {

				// NOOP

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	public void Save()
	{

		Writer writer = null;

		try {

			writer = new FileWriter(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterMutationData.tmp");
			writer.write(JsonTools.mapToJson_mutations(mutationMap));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		File file1 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterMutationData.tmp");
		File file2 = new File(FluxedCrystals.configDir.getAbsolutePath() + File.separator + "masterMutationData.json");

		if (file2.exists()) {

			file2.delete();

		}

		file1.renameTo(file2);

	}

	public Map<MutablePair<Seed, Seed>, Mutation> getMutationMap()
	{

		return mutationMap;

	}

}
