package fluxedCrystals.items.seeds;

import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.*;
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
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (!world.isRemote) {
			player.openGui(FluxedCrystals.instance, 0, world, x, y, z);
		}
		return true;
	}
}
