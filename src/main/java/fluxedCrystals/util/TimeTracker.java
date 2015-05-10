package fluxedCrystals.util;

import net.minecraft.world.World;

public class TimeTracker {

	private long mark = Long.MIN_VALUE;

	public boolean hasDelayPassed(World world, int delay) {

		long currentTime = world.getTotalWorldTime();

		if (currentTime < mark) {
			mark = currentTime;
			return false;
		} else if (mark + delay <= currentTime) {
			mark = currentTime;
			return true;
		}
		return false;
	}

	public void markTime(World world) {

		mark = world.getTotalWorldTime();
	}

}