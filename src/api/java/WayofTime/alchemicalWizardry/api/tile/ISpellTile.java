package WayofTime.alchemicalWizardry.api.tile;

import WayofTime.alchemicalWizardry.api.spell.SpellParadigm;
import net.minecraftforge.common.util.ForgeDirection;

public interface ISpellTile 
{
	void modifySpellParadigm (SpellParadigm parad);
	
	boolean canInputRecieveOutput (ForgeDirection output);
}
