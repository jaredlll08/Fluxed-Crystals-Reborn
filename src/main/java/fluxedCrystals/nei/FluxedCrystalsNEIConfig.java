package fluxedCrystals.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import fluxedCrystals.reference.Reference;

public class FluxedCrystalsNEIConfig implements IConfigureNEI
{

	@Override
	public String getName() {
		return "Fluxed-Crystals-NEI";
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new GemCutterHandler());
		API.registerUsageHandler(new GemCutterHandler());
		API.registerRecipeHandler(new RoughShardHandler());
		API.registerRecipeHandler(new GemRefinerHandler());
		API.registerUsageHandler(new GemRefinerHandler());
		API.registerRecipeHandler(new InfuserRecipeHandler());
		API.registerUsageHandler(new InfuserRecipeHandler());
	}

}
