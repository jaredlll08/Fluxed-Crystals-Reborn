package fluxedCrystals.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.BlockGemCutter;
import fluxedCrystals.blocks.BlockGemRefiner;
import fluxedCrystals.blocks.BlockSeedInfuser;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.blocks.soil.BlockPowerBlock;
import fluxedCrystals.blocks.soil.BlockPowerBlockLP;
import fluxedCrystals.blocks.soil.BlockPowerBlockMana;
import fluxedCrystals.reference.Names;
import fluxedCrystals.tileEntity.*;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockLP;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockMana;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class FCBlocks {
	public static Map<String, Block> blockRegistry = new HashMap<String, Block>();

	public static Block poweredSoil = new BlockPowerBlock();

	public static Block crystal = new BlockCrystal();
	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block gemCutter = new BlockGemCutter();
	public static Block gemRefiner = new BlockGemRefiner();

	public FCBlocks() {

	}

	public static void preInit() {

	}

	public static void initialize() {
		GameRegistry.registerTileEntity(TileEnergyBase.class, "FCEnergy");

		registerBlock(poweredSoil, Names.Blocks.POWEREDSOIL, Names.Blocks.POWEREDSOIL);
		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, Names.Blocks.POWEREDSOIL);

		registerBlock(crystal, "crystal", "crystal");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "FCCrystal");

		registerBlock(seedInfuser, Names.Blocks.SEED_INFUSER, Names.Blocks.SEED_INFUSER);
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, Names.Blocks.SEED_INFUSER);

		registerBlock(gemCutter, Names.Blocks.GEM_CUTTER, Names.Blocks.GEM_CUTTER);
		GameRegistry.registerTileEntity(TileEntityGemCutter.class, Names.Blocks.GEM_CUTTER);

		registerBlock(gemRefiner, Names.Blocks.GEM_REFINER, Names.Blocks.GEM_REFINER);
		GameRegistry.registerTileEntity(TileEntityGemRefiner.class, Names.Blocks.GEM_REFINER);


	}

	public static void postInit() {

	}

	private static void registerBlock(Block block, String name, String key) {
		block.setBlockName(name).setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(block, key);
		blockRegistry.put(key, block);
	}


}
