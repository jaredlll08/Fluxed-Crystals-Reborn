package fluxedCrystals.util;

import net.minecraft.util.DamageSource;

public class DamageSourceCrystal extends DamageSource {

	public DamageSourceCrystal() {
		super("Crystal");
	}

	public boolean isDifficultyScaled() {
		return true;
	}

	public boolean isDamageAbsolute() {
		return true;
	}

}
