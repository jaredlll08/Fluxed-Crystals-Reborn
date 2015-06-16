package fluxedCrystals.init;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.BlockHidden;
import fluxedCrystals.blocks.BlockMulti;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.blocks.machines.BlockGemCutter;
import fluxedCrystals.blocks.machines.BlockGemRefiner;
import fluxedCrystals.blocks.machines.BlockSeedInfuser;
import fluxedCrystals.blocks.soil.BlockPowerBlock;
import fluxedCrystals.blocks.soil.BlockPowerBlockLP;
import fluxedCrystals.blocks.soil.BlockPowerBlockMana;
import fluxedCrystals.blocks.soil.BlockPoweredSoilEU;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import fluxedCrystals.tileEntity.TileEntityGemRefiner;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlock;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockLP;
import fluxedCrystals.tileEntity.soil.TileEntityPowerBlockMana;
import fluxedCrystals.tileEntity.soil.TileSoilEU;

public class FCBlocks
{
	public static Block poweredSoil = new BlockPowerBlock();
	public static Block poweredSoilMana = new BlockPowerBlockMana();
	public static Block poweredSoilLP= new BlockPowerBlockLP();
	public static Block poweredSoilEU= new BlockPoweredSoilEU();
	
	public static Block crystal = new BlockCrystal();
	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block gemCutter = new BlockGemCutter();
	public static Block gemRefiner = new BlockGemRefiner();
	public static Block multi = new BlockMulti();
	public static Block hidden = new BlockHidden();
	@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
	private static Map<String, Block> blockRegistry = new HashMap<String, Block>();

	public FCBlocks() {

	}

	public static void preInit() {

	}

	public static void initialize() {
		// GameRegistry.registerTileEntity(TileEnergyBase.class, "FCEnergy");

		registerBlock(poweredSoil, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.POWEREDSOIL, Names.Blocks.POWEREDSOIL);
		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, Names.Blocks.POWEREDSOIL);
		
		registerBlock(poweredSoilMana, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.POWEREDSOILMANA, Names.Blocks.POWEREDSOILMANA);
		GameRegistry.registerTileEntity(TileEntityPowerBlockMana.class, Names.Blocks.POWEREDSOILMANA);
		registerBlock(poweredSoilEU, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.POWEREDSOIL+"EU", Names.Blocks.POWEREDSOIL+"EU");
		GameRegistry.registerTileEntity(TileSoilEU.class, Names.Blocks.POWEREDSOIL+"EU");
		
		registerBlock(poweredSoilLP, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.POWEREDSOILLP, Names.Blocks.POWEREDSOILLP);
		GameRegistry.registerTileEntity(TileEntityPowerBlockLP.class, Names.Blocks.POWEREDSOILLP);
		

		registerBlock(crystal, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.CRYSTAL, Names.Blocks.CRYSTAL);
		GameRegistry.registerTileEntity(TileEntityCrystal.class, Names.Blocks.CRYSTAL);

		registerBlock(seedInfuser, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.SEED_INFUSER, Names.Blocks.SEED_INFUSER);
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, Names.Blocks.SEED_INFUSER);

		registerBlock(gemCutter, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.GEM_CUTTER, Names.Blocks.GEM_CUTTER);
		GameRegistry.registerTileEntity(TileEntityGemCutter.class, Names.Blocks.GEM_CUTTER);

		registerBlock(gemRefiner, Reference.LOWERCASE_MOD_ID + "." + Names.Blocks.GEM_REFINER, Names.Blocks.GEM_REFINER);
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
