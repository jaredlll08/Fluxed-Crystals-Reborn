package WayofTime.alchemicalWizardry.api.spell;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IItemManipulator
{
    List<ItemStack> handleItemsOnBlockBroken (ItemStack toolStack, List<ItemStack> itemList);
}
