package fluxedCrystals.items.tools;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import fluxedCrystals.reference.Misc;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.NBTHelper;

public class ItemShardPickaxe extends ItemPickaxe {

	public ItemShardPickaxe() {
		super(Misc.shardToolMaterials);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (Seed seed : SeedRegistry.getInstance().getSeedMap().values()) {
			ItemStack stack = new ItemStack(item);
			NBTHelper.setInteger(stack, "colorType", seed.color);
			list.add(stack);
		}
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int meta) {
		return NBTHelper.getInt(stack, "colorType");
	}

}
