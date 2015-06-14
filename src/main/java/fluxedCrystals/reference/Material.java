package fluxedCrystals.reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Material
{

	// TODO Need to link this to a craftable something so it can be repaired properly

	public static final class Tools
	{

		public static final Item.ToolMaterial crystal = EnumHelper.addToolMaterial("crystal", 3, 450, 3.0F, 0, 0);

	}

	public static final class Armor
	{

		public static final ItemArmor.ArmorMaterial crystal = EnumHelper.addArmorMaterial("crystal", 33, new int[]{3,8,6,3}, 0);

	}

}
