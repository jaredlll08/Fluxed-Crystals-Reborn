package fluxedCrystals.items;

import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemShardSmooth extends Item {

	public ItemShardSmooth() {
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SHARDSMOOTH);
		setTextureName(Reference.LOWERCASE_MOD_ID + ":" + Names.Items.SHARDSMOOTH);
		setHasSubtypes(true);
	}

	public int getRenderPasses(int metadata) {
		return 1;
	}


	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return SeedRegistry.getInstance().getSeedByID(par1ItemStack.getItemDamage()).color;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list) {

		for (int i : SeedRegistry.getInstance().keySet()) {
			list.add(new ItemStack(this, 1, SeedRegistry.getInstance().getSeedByID(i).seedID));

		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {

		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).name);

	}

}
