package fluxedCrystals.registry;

import java.io.Serializable;

/**
 * Created by Jared on 5/20/2015.
 */
public class Mutation implements Serializable {

	public String seed1 = "";
	public String seed2 = "";
	public String outputSeed = "";
	//	public int power = 200;


	public Mutation(String outputSeed, String seed1, String seed2) {
		this.outputSeed = outputSeed;
		this.seed1 = seed1;
		this.seed2 = seed2;
	}

}
