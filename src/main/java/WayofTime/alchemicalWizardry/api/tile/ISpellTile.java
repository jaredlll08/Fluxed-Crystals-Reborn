package WayofTime.alchemicalWizardry.api.tile;

import WayofTime.alchemicalWizardry.api.spell.SpellParadigm;
import net.minecraftforge.common.util.ForgeDirection;

public interface ISpellTile {
	public void modifySpellParadigm(SpellParadigm parad);

	public boolean canInputRecieveOutput(ForgeDirection output);
}
