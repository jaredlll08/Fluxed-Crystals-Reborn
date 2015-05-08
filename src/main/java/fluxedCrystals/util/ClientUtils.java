package fluxedCrystals.util;

import fluxedCrystals.api.solarFlux.TileSolarFlux;
import fluxedCrystals.network.message.MessageEnergyUpdate;
import fluxedCrystals.network.message.MessageSolarFluxSync;
// import fluxedCrystals.tileEntity.TileEnergyBase;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.mana.IManaReceiver;

public class ClientUtils
{

	/*public static void updateEnergy(MessageEnergyUpdate message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEnergyBase) {
			((TileEnergyBase) te).setEnergyStored(message.stored);
		}
		if (te instanceof IManaReceiver) {
			IManaReceiver tile = (IManaReceiver) te;
			tile.recieveMana(-tile.getCurrentMana());
			tile.recieveMana(message.mana);
		}
	}
	public static void updateSolarflux(MessageSolarFluxSync message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileSolarFlux) {
			((TileSolarFlux) te).setEnergy(message.stored);
		}
	}*/
	
}
