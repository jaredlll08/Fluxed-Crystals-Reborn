package fluxedCrystals.client.gui.crystalTablet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCrystalTablet extends Container {

	private InventoryPlayer playerInv;

	public ContainerCrystalTablet(InventoryPlayer player) {
		this.playerInv = player;
		
		

//		for (int y = 0; y < 3; y++) {
//			for (int x = 0; x < 9; x++) {
//				addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
//			}
//		}
//
//		for (int x = 0; x < 9; x++) {
//			addSlotToContainer(new Slot(playerInv, x, 8 + 18 * x, 142));
//		}
	}
	

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
