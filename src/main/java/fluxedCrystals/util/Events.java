package fluxedCrystals.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fluxedCrystals.handler.ConfigurationHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class Events
{


	@SubscribeEvent
	public void sekritStuff(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (ConfigurationHandler.isPlayerSpecial(event.entity.getCommandSenderName()) & event.entity.isSneaking()) {
				for (int x = -6; x < 5; x++) {
					for (int y = -3; y < 40; y++) {
						for (int z = -6; z < 5; z++) {
							World world = event.entity.worldObj;
							int xCoord = (int) event.entity.posX;
							int yCoord = (int) event.entity.posY;
							int zCoord = (int) event.entity.posZ;
							for (int j = 0; j < 20; j++) {
								if (world.getBlock(xCoord + x, yCoord + y, zCoord + z) != null) {
									Block b = world.getBlock(xCoord + x, yCoord + y, zCoord + z);
									b.updateTick(world, xCoord + x, yCoord + y, zCoord + z, world.rand);
									if (b instanceof ITileEntityProvider
											&& world.getTileEntity(xCoord + x, yCoord + y, zCoord + z) != null) {
										world.getTileEntity(xCoord + x, yCoord + y, zCoord + z).updateEntity();
									}
								}
							}
						}
					}
				}
			}

		}
	}
}
