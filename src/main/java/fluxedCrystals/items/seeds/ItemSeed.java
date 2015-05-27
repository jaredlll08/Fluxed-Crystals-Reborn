package fluxedCrystals.items.seeds;

import cpw.mods.fml.common.Loader;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.reference.*;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.util.IPowerSoil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class ItemSeed extends SeedBase
{

	public ItemSeed () {
		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setTextureName(Textures.Items.SEED);
		setHasSubtypes(true);
		setCreativeTab(FluxedCrystals.tab);
	}

	@Override
	public boolean onItemUse (ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		ItemStack seeds = stack.copy();
		seeds.stackSize = 1;
		if (world.getBlock(x, y, z) instanceof IPowerSoil) {
			if (hitY == 1.0F) {
				world.setBlock(x, y + 1, z, FCBlocks.crystal);
				((TileEntityCrystal) world.getTileEntity(x, y + 1, z)).setIdx(stack.getItemDamage());
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				return true;
			}
		}
		return false;
	}

	public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4) {

		list.add("-" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).name);
		list.add("Growth:" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).growthTime);

		if (SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).tier > 0) {

			list.add("Tier:" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).tier);

		}

		if (!SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).lore.equals("")) {

			String lore = SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).lore;

			lore = lore.replaceAll("\t", "    ");

			String[] lores = lore.split("\n");

			Collections.addAll(list, lores);

		}

	}

	public int getRenderPasses (int metadata) {

		return 1;

	}

	@Override
	public int getColorFromItemStack (ItemStack par1ItemStack, int par2) {

		return SeedRegistry.getInstance().getSeedByID(par1ItemStack.getItemDamage()).color;

	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubItems (Item p_150895_1_, CreativeTabs p_150895_2_, List list) {

		for (int i : SeedRegistry.getInstance().keySet()) {

			if (SeedRegistry.getInstance().getSeedByID(i).modRequired.equals("") || (!SeedRegistry.getInstance().getSeedByID(i).modRequired.equals("") && Loader.isModLoaded(SeedRegistry.getInstance().getSeedByID(i).modRequired))) {

				list.add(new ItemStack(this, 1, SeedRegistry.getInstance().getSeedByID(i).seedID));

			}

		}

	}

	@Override
	public String getItemStackDisplayName (ItemStack stack) {

		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).name);

	}

}
