package fluxedCrystals.client.render;

import java.awt.Color;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.crystal.CrystalBase;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.util.HolidayHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderCrystal implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock (Block block, int metadata, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock (IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(x, y, z);
		Seed seed = SeedRegistry.getInstance().getSeedByID(tile.getIdx());

		Tessellator tess = Tessellator.instance;
		tess.setColorOpaque_I(SeedRegistry.getInstance().getSeedByID(tile.getIndex()).color);
		tess.setBrightness(0xF000F0);
		Color col = new Color(SeedRegistry.getInstance().getSeedByID(tile.getIndex()).color);
		renderer.enableAO = false;
		if(HolidayHelper.isBirthdayJared()){
			renderer.renderBlockByRenderType(Blocks.cake, x, y, z);
		}
		if ((seed.type.equalsIgnoreCase("crystal") || seed.type.equalsIgnoreCase("")) ) {
			tess.setColorOpaque_I(SeedRegistry.getInstance().getSeedByID(tile.getIndex()).color);
			renderer.drawCrossedSquares(((CrystalBase) block).crystals[meta], x, y, z, 1.0f);
			renderer.enableAO = true;
			return true;
		}
		if (seed.type.equalsIgnoreCase("crop")) {
			tess.setColorOpaque_I(0xFFFFFF);
			renderer.drawCrossedSquares(((CrystalBase) block).crop[meta], x, y, z, 1.0f);
			tess.setColorOpaque_I(SeedRegistry.getInstance().getSeedByID(tile.getIndex()).color);
			if (meta == 5) {
				renderer.drawCrossedSquares(((CrystalBase) block).cropOverlay[0], x, y, z, 1.0f);
			}
			if (meta == 6) {
				renderer.drawCrossedSquares(((CrystalBase) block).cropOverlay[1], x, y, z, 1.0f);
			}
			if (meta == 7) {
				renderer.drawCrossedSquares(((CrystalBase) block).cropOverlay[2], x, y, z, 1.0f);
			}
			renderer.enableAO = true;
			return true;


		}
		renderer.enableAO = true;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory (int modelId) {
		return true;
	}

	@Override
	public int getRenderId () {
		return FluxedCrystals.crystalRenderID;
	}

}
