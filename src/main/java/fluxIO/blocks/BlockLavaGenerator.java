package fluxIO.blocks;

import fluxIO.api.GeneratorUtils;
import fluxIO.tileEntity.TileEntityLavaGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockLavaGenerator extends BlockContainer {

	protected BlockLavaGenerator() {
		super(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEntityLavaGenerator tile = (TileEntityLavaGenerator) world.getTileEntity(x, y, z);
		if (FluidContainerRegistry.isContainer(player.getCurrentEquippedItem()) && FluidContainerRegistry.isFilledContainer(player.getCurrentEquippedItem()) && tile.getFluidAmount() + FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()).amount <= tile.getCapacity()) {
			if (FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()).getFluid() == FluidRegistry.LAVA) {
				tile.fill(FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()), true);
				player.inventory.setInventorySlotContents(player.inventory.currentItem, GeneratorUtils.consumeItem(player.getCurrentEquippedItem()));
			}
		}
		if (tile.getFluid() != null)
			player.addChatComponentMessage(new ChatComponentText(tile.getFluid().getLocalizedName() + ":" + tile.getFluidAmount() + ""));
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityLavaGenerator();
	}

}
