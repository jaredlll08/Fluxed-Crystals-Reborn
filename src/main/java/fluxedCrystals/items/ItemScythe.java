package fluxedCrystals.items;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.Item;

public class ItemScythe extends Item {

	public ItemScythe(String textureName, String itemName) {

		setCreativeTab(FluxedCrystals.tab);
		setTextureName(textureName);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + itemName);

	}

}
