package fluxedCrystals.items.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.Misc;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.NBTHelper;

public class ItemCrystalShovel extends ItemSpade {

	public IIcon[] icons;

	public ItemCrystalShovel() {

		super(Misc.shardToolMaterials);
		setHasSubtypes(true);
		setCreativeTab(FluxedCrystals.tab);
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.CRYSTAL_SHOVEL);
	}

	@Override
	public boolean requiresMultipleRenderPasses() {

		return true;
	}

	@Override
	public int getRenderPasses(int metadata) {

		return 2;
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		return pass == 0 ? icons[0] : icons[1];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {

		for (Seed seed : SeedRegistry.getInstance().getSeedMap().values()) {
			ItemStack stack = new ItemStack(item);
			NBTHelper.setInteger(stack, "colorType", seed.color);
			NBTHelper.setInteger(stack, "seedID", seed.seedID);
			list.add(stack);
		}
	}

	@Override
	public void registerIcons(IIconRegister icon) {

		icons = new IIcon[2];
		icons[0] = icon.registerIcon(Reference.MOD_ID + ":shardShovelRod");
		icons[1] = icon.registerIcon(Reference.MOD_ID + ":shardShovelHead");
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {

		switch (pass) {
			case 0:
				return 0xFFFFFF;
			case 1:
				return NBTHelper.getInt(stack, "colorType");
			default:
				return 0xFFFFFF;
		}
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), SeedRegistry.getInstance().getSeedByID(NBTHelper.getInt(stack, "seedID")).name);
	}
}
