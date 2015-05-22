package fluxedCrystals.items.seeds;

import fluxedCrystals.reference.Reference;
import fluxedCrystals.util.ISeed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

class SeedBase extends Item implements ISeed
{

	private static IIcon overlay;

	public void registerIcons (IIconRegister icon) {

		this.itemIcon = icon.registerIcon(Reference.LOWERCASE_MOD_ID + ":seed");

	}


}
