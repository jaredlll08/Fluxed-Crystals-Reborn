package fluxedCrystals.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.blocks.BlockGemCutter;
import fluxedCrystals.blocks.BlockGemRefiner;
import fluxedCrystals.blocks.BlockPowerBlock;
import fluxedCrystals.blocks.BlockSeedInfuser;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.reference.Names;
import fluxedCrystals.tileEntity.*;
import net.minecraft.block.Block;

public class FCBlocks
{

	public static Block poweredSoil= new BlockPowerBlock();
	public static Block crystal= new BlockCrystal();
	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block gemCutter = new BlockGemCutter();
	public static Block gemRefiner = new BlockGemRefiner();

	public FCBlocks()
	{

	}

	public static void preInit()
	{

	}

	public static void initialize()
	{

		GameRegistry.registerBlock(poweredSoil, Names.Blocks.POWEREDSOIL);
		GameRegistry.registerTileEntity(TileEntityPowerBlock.class, Names.Blocks.POWEREDSOIL);

		GameRegistry.registerBlock(crystal, "crystal");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "FCCrystal");

		GameRegistry.registerBlock(seedInfuser, Names.Blocks.SEED_INFUSER);
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, Names.Blocks.SEED_INFUSER);

		GameRegistry.registerBlock(gemCutter, Names.Blocks.GEM_CUTTER);
		GameRegistry.registerTileEntity(TileEntityGemCutter.class, Names.Blocks.GEM_CUTTER);

		GameRegistry.registerBlock(gemRefiner, Names.Blocks.GEM_REFINER);
		GameRegistry.registerTileEntity(TileEntityGemRefiner.class, Names.Blocks.GEM_REFINER);


	}

	public static void postInit()
	{

	}

}
