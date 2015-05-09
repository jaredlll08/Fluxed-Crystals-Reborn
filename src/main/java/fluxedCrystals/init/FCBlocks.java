package fluxedCrystals.init;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.blocks.BlockPowerBlock;
import fluxedCrystals.blocks.crystal.BlockCrystal;
import fluxedCrystals.reference.Names;
import fluxedCrystals.reference.Reference;
import fluxedCrystals.reference.Textures;
import fluxedCrystals.tileEntity.TileEntityCrystal;

public class FCBlocks
{

	public static Block poweredSoil= new BlockPowerBlock();
	public static Block crystal= new BlockCrystal();
	

	
	

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
		

	}

	public static void postInit()
	{

	}

}
