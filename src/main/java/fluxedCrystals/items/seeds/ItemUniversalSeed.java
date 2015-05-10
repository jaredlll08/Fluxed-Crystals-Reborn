package fluxedCrystals.items.seeds;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.Item;

public class ItemUniversalSeed extends Item
{

	public ItemUniversalSeed()
	{

		setTextureName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setHasSubtypes(true);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setCreativeTab(FluxedCrystals.tab);

	}

}
