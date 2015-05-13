package fluxedCrystals.util;

import fluxedCrystals.network.message.MessageSolarFluxSync;
import fluxedCrystals.tileEntity.TileEnergyBase;
import fluxedCrystals.tileEntity.solarFlux.TileSolarFlux;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.mana.IManaReceiver;

public class ClientUtils
{

	public static void updateSolarflux(MessageSolarFluxSync message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileSolarFlux) {
			((TileSolarFlux) te).setEnergy(message.stored);
		}
	}
	
}
