package fluxedCrystals.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.oredict.OreDictionary;

public class JSONHelper
{

	public static ItemStack parseStringIntoItemStack(String string)
	{

		int size = 1;
		int idx = string.indexOf('#');

		if (idx != -1)
		{
			String num = string.substring(idx + 1);

			try
			{
				size = Integer.parseInt(num);
			}
			catch (NumberFormatException e)
			{
				throw new IllegalArgumentException(num + " is not a valid stack size");
			}

			string = string.substring(0, idx);

		}

		ItemStack stack = (ItemStack) parseStringIntoRecipeItem(string, true);

		stack.stackSize = MathHelper.clamp_int(size, 1, stack.getMaxStackSize());

		return stack;

	}

	public static Object parseStringIntoRecipeItem(String string, boolean forceItemStack)
	{

		if ("null".equals(string))
		{
			return null;
		}
		else if (OreDictionary.getOres(string).isEmpty())
		{
			ItemStack stack = null;

			String[] info = string.split(";");
			Object temp = null;
			int damage = OreDictionary.WILDCARD_VALUE;
			temp = Item.itemRegistry.getObject(info[0]);
			if (info.length > 1)
			{
				damage = Integer.parseInt(info[1]);
			}

			if (temp instanceof Item)
			{
				stack = new ItemStack((Item) temp, 1, damage);
			}
			else if (temp instanceof Block)
			{
				stack = new ItemStack((Block) temp, 1, damage);
			}
			else if (temp instanceof ItemStack)
			{
				stack = ((ItemStack) temp).copy();
				stack.setItemDamage(damage);
			}
			else
			{
				throw new IllegalArgumentException(string
						+ " is not a vaild string. Strings should be either an oredict name, or in the format objectname;damage (damage is optional)");
			}

			return stack;
		}
		else if (forceItemStack)
		{
			return OreDictionary.getOres(string).get(0).copy();
		}
		else
		{
			return string;
		}

	}

	public static String getStringForItemStack(ItemStack stack, boolean damage, boolean size)
	{
		if (stack == null)
		{
			return null;
		}

		String base = Item.itemRegistry.getNameForObject(stack.getItem());

		if (damage)
		{
			base += ";" + stack.getItemDamage();
		}

		if (size)
		{
			base += "#" + stack.stackSize;
		}

		return base;
	}

}
