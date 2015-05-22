package fluxedCrystals.compat.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Used to add WAILA compatability without using and abusing <code>{@literal @}Optional</code>
 * <p/>
 * Must be implemented on a block,  will pass it to the TileEntity by default
 */
public interface IWailaInfo
{
	/**
	 * Add to the main WAILA tooltip
	 *
	 * @param tooltip - Current list of Strings in the tooltip
	 * @param x
	 * @param y
	 * @param z
	 * @param world
	 */
	void getWailaInfo (List<String> tooltip, int x, int y, int z, World world);

	ItemStack getWailaStack (IWailaDataAccessor accessor, IWailaConfigHandler config);
}