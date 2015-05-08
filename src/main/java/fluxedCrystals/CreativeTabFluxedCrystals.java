package fluxedCrystals;

import fluxedCrystals.init.FCItems;
import fluxedCrystals.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabFluxedCrystals extends CreativeTabs {

	public CreativeTabFluxedCrystals ()
	{

		super(Reference.LOWERCASE_MOD_ID);

	}

	@Override
	public Item getTabIconItem()
	{

		return FCItems.universalSeed;

	}

}
