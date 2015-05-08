package fluxedCrystals.items;

import fluxedCrystals.api.SeedBase;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedData;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.LogHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemShardRough extends Item {

	public ItemShardRough() {
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SHARDROUGH);
		setTextureName(Reference.LOWERCASE_MOD_ID + ":" + Names.Items.SHARDROUGH);
		setHasSubtypes(true);
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}


	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return SeedRegistry.getInstance().getSeedByID(par1ItemStack.getItemDamage()).getColor();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list) {

		for (int i : SeedRegistry.getInstance().keySet()) {
			list.add(new ItemStack(this, 1, SeedRegistry.getInstance().getSeedByID(i).getEntityID()));

		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {

		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getName());

	}

}
