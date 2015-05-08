package fluxedCrystals.items.seeds;

import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.Item;

public class ItemUniversalSeed extends Item
{

	public ItemUniversalSeed()
	{

		setTextureName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setHasSubtypes(true);

	}

}
