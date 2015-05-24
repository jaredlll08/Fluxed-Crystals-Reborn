package fluxIO.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fluxIO.FluxIO;
import fluxIO.client.gui.coalGenerator.ContainerCoalGenerator;
import fluxIO.client.gui.coalGenerator.GUICoalGenerator;
import fluxIO.tileEntity.TileEntityCoalGenerator;

public class GUIHandler implements IGuiHandler {

	public GUIHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(FluxIO.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {

		case 0:
			if (te != null && te instanceof TileEntityCoalGenerator) {
				return new ContainerCoalGenerator(player.inventory, (TileEntityCoalGenerator) te);
			}
			break;

		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		switch (ID) {
		case 0:
			if (te != null && te instanceof TileEntityCoalGenerator) {
				return new GUICoalGenerator(player.inventory, (TileEntityCoalGenerator) te);
			}
			break;
		}
		return null;
	}

}
