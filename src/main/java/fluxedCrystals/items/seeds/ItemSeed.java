package fluxedCrystals.items.seeds;

import fluxedCrystals.api.SeedBase;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.registry.SeedData;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.util.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemSeed extends SeedBase
{

	public ItemSeed()
	{

		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setTextureName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.SEED);
		setHasSubtypes(true);

	}

	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{

		list.add("-" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getName());
		list.add("Growth:" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getGrowthTime());

		if (SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getTier() > 0)
		{

			list.add("Tier:" + SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getTier());

		}

		if (!SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getLore().equals(""))
		{

			String lore = SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getLore();

			lore.replaceAll("\t", "    ");

			String[] lores = lore.split("\n");

			for (String lor : lores)
			{

				list.add(lor);

			}

		}

	}

	public int getRenderPasses(int metadata)
	{

		return 1;

	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{

		return SeedRegistry.getInstance().getSeedByID(par1ItemStack.getItemDamage()).getColor();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list)
	{

		for(int i : SeedRegistry.getInstance().keySet())
		{

			list.add(new ItemStack(this, 1, SeedRegistry.getInstance().getSeedByID(i).getEntityID()));

		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{

		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), SeedRegistry.getInstance().getSeedByID(stack.getItemDamage()).getName());

	}

}
