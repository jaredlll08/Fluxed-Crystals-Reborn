package fluxedCrystals.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.BlockGemCutter;
import fluxedCrystals.blocks.BlockPowerBlock;
import fluxedCrystals.blocks.BlockSeedInfuser;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import fluxedCrystals.tileEntity.TileEntityGemCutter;
import fluxedCrystals.tileEntity.TileEntitySeedInfuser;
import net.minecraft.block.Block;

public class FCBlocks
{

	public static Block poweredSoil= new BlockPowerBlock();
	public static Block crystal= new BlockCrystal();
	public static Block seedInfuser = new BlockSeedInfuser();
	public static Block gemCutter = new BlockGemCutter();

	public FCBlocks()
	{

	}

	public static void preInit()
	{

	}

	public static void initialize()
	{
		poweredSoil.setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(poweredSoil, Names.Blocks.POWEREDSOIL);

		crystal.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":crop_stage_7").setCreativeTab(FluxedCrystals.tab);
		GameRegistry.registerBlock(crystal, "crystal");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "FCCrystal");

		GameRegistry.registerBlock(seedInfuser, Names.Blocks.SEED_INFUSER);
		GameRegistry.registerTileEntity(TileEntitySeedInfuser.class, Names.Blocks.SEED_INFUSER);

		GameRegistry.registerBlock(gemCutter, Names.Blocks.GEM_CUTTER);
		GameRegistry.registerTileEntity(TileEntityGemCutter.class, Names.Blocks.GEM_CUTTER);


	}

	public static void postInit()
	{

	}

}
