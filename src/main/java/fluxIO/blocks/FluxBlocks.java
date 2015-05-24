package fluxIO.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fluxIO.ModProps;
import fluxIO.tileEntity.TileEntityCoalGenerator;
import fluxIO.tileEntity.TileEntityLavaGenerator;
import fluxIO.tileEntity.TileEntityTrashGenerator;
import net.minecraft.block.Block;

public class FluxBlocks {

	public static Block basicCoalGenerator = new BlockCoalGenerator();
	public static Block trashGenerator = new BlockTrashGenerator();
	public static Block lavaGenerator = new BlockLavaGenerator();

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityTrashGenerator.class, "trash");
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, "basicCoal");
		GameRegistry.registerTileEntity(TileEntityLavaGenerator.class, "lava");

		registerBlock(basicCoalGenerator, "Basic Coal Generator", "coalGenBasic");
		registerBlock(trashGenerator, "Trash Generator", "trashGenerator");
		registerBlock(lavaGenerator, "Lava Generator", "lavaGenerator");
	}

	public static void registerBlock(Block block, String name, String key) {
		block.setBlockTextureName(ModProps.modid + ":" + key).setBlockName(name);
		GameRegistry.registerBlock(block, key);
	}

}
