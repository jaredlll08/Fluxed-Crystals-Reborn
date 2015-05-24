package fluxIO.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import fluxIO.network.MessageEnergyUpdate;
import fluxIO.tileEntity.TileEnergyBase;

public class ClientUtils {

	public static void updateEnergy(MessageEnergyUpdate message) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEnergyBase) {
			((TileEnergyBase) te).setEnergyStored(message.stored);
		}
	}

}
