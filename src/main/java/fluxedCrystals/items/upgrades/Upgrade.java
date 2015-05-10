package fluxedCrystals.items.upgrades;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.util.IUpgrade;
import net.minecraft.item.Item;

public class Upgrade extends Item implements IUpgrade
{

	public Upgrade(String textureName, String itemName)
	{

		setCreativeTab(FluxedCrystals.tab);
		setTextureName(textureName);
		setUnlocalizedName(itemName);

	}
	
}
