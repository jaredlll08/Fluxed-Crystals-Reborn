package fluxedCrystals.api;

import fluxedCrystals.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class SeedBase extends Item implements ISeed
{

	private static IIcon overlay;

	public void registerIcons(IIconRegister icon)
	{

		this.itemIcon = icon.registerIcon(Reference.LOWERCASE_MOD_ID + ":seed");

	}

}
