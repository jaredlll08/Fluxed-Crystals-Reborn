package fluxedCrystals.items.seeds;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.reference.Textures;
import net.minecraft.item.Item;

public class ItemUniversalSeed extends Item
{

	public ItemUniversalSeed()
	{

		setTextureName(Textures.Items.UNIVERSAL_SEED);
		setHasSubtypes(true);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.UNIVERSAL_SEED);
		setCreativeTab(FluxedCrystals.tab);

	}

}
