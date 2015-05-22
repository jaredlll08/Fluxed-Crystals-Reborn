package fluxedCrystals.items;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.IUpgrade;
import net.minecraft.item.Item;

public class Upgrade extends Item implements IUpgrade
{

	public Upgrade (String textureName, String itemName) {

		setCreativeTab(FluxedCrystals.tab);
		setTextureName(textureName);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + itemName);

	}

}
