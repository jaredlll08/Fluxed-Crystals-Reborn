package fluxedCrystals.util;

import net.minecraft.item.Item;

public interface ITileSoil {
	public int getSpeed();
	public int getEffeciency();
	public int getStoredEnergy();
	public boolean canDrainEnergy(int energy);
	public void drainEnergy(int energy);
	public int getUpgradeDrain(int index);
	public boolean isUpgradeActive(Item upgrade);
	public int scaleEnergy(int energy);
}
