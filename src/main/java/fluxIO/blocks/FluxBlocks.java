package fluxIO.blocks;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxIO.FluxIO;
import fluxIO.ModProps;
import fluxIO.blocks.tank.BlockTank;
import fluxIO.blocks.tank.BlockTankFluid;
import fluxIO.blocks.tank.BlockTankFluid.TileEntityTopTank;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxIO.tileEntity.TileEntityLavaGenerator;
import fluxIO.tileEntity.TileEntityTrashGenerator;
import fluxIO.tileEntity.fluids.TileEntityFluidTank;
import fluxIO.tileEntity.fluids.TileEntityTank;

public class FluxBlocks {

	public static Block basicCoalGenerator = new BlockCoalGenerator();
	public static Block trashGenerator = new BlockTrashGenerator();
	public static Block lavaGenerator = new BlockLavaGenerator();
	public static Block tank = new BlockTank();
	public static Block tankFluid = new BlockTankFluid();
	
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityTrashGenerator.class, "trash");
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, "basicCoal");
		GameRegistry.registerTileEntity(TileEntityLavaGenerator.class, "lava");
		GameRegistry.registerTileEntity(TileEntityTank.class, "tank");
		GameRegistry.registerTileEntity(TileEntityFluidTank.class, "tankFluid");
		GameRegistry.registerTileEntity(TileEntityTopTank.class, "tankFluidTop");
		

		registerBlock(basicCoalGenerator, "Basic Coal Generator", "coalGenBasic");
		registerBlock(trashGenerator, "Trash Generator", "trashGenerator");
		registerBlock(lavaGenerator, "Lava Generator", "lavaGenerator");
		registerBlock(tank, "Tank", "tank");
//		registerBlock(tankFluid, "TankFluid", "tankFluid");
		tankFluid.setBlockName("tankFluid");
		GameRegistry.registerBlock(tankFluid, "tankFluid");
		
		
	}

	public static void registerBlock(Block block, String name, String key) {
		block.setBlockTextureName(ModProps.modid + ":" + key).setBlockName(name).setCreativeTab(FluxIO.tab);
		GameRegistry.registerBlock(block, key);
	}

}
