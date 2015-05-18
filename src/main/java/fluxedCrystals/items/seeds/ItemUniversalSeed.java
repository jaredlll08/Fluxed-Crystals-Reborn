package fluxedCrystals.items.seeds;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.reference.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUniversalSeed extends Item {

	public ItemUniversalSeed() {

		setTextureName(Textures.Items.UNIVERSAL_SEED);
		setHasSubtypes(true);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.UNIVERSAL_SEED);
		setCreativeTab(FluxedCrystals.tab);

	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (!p_77648_3_.isRemote) FluxedCrystals.proxy.openTablet();
		return true;
	}
}
