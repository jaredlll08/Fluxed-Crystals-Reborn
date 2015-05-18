package fluxedCrystals.items;

import com.google.common.collect.Sets;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.reference.Reference;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class ItemScythe extends ItemTool
{

	public static Set blocksEffectiveAgainst = Sets.newHashSet(FCBlocks.crystal);

	public ItemScythe(String textureName, String itemName, ToolMaterial toolMaterial)
	{

		super(3.0F, toolMaterial, blocksEffectiveAgainst);

		setCreativeTab(FluxedCrystals.tab);
		setTextureName(textureName);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + itemName);
		this.maxStackSize = 1;

	}

}
