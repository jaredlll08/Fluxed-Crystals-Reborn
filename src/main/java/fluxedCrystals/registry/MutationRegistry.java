package fluxedCrystals.registry;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 5/20/2015.
 */
public class MutationRegistry {
	private static MutationRegistry mutationRegistry = null;
	private static Map<MutablePair<Seed, Seed>, Mutation> mutationMap = new HashMap<MutablePair<Seed, Seed>, Mutation>();

	public static MutationRegistry getInstance() {

		if (mutationMap == null) {

			return new MutationRegistry();

		}

		return mutationRegistry;

	}

	public static void addMutation(String output, String seed1, String seed2) {

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
}
