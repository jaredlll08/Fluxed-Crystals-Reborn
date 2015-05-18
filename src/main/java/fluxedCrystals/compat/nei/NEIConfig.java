package fluxedCrystals.compat.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Optional;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.ItemStack;

@Optional.Interface(iface = "codechicken.nei.api.API", modid = "NotEnoughItems")
public class NEIConfig implements IConfigureNEI {

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
		API.hideItem(new ItemStack(FCBlocks.crystal));
	}

}
