package fluxIO.utils.multiBlock;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;

public abstract class BlockMaster extends Block implements ITileEntityProvider{

	public BlockMaster(Material material) {
		super(material);
	}
	public abstract ConnectedTile getConnectedTiles();

	
}
