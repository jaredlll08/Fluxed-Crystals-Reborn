package fluxedCrystals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHidden extends Block {

	public BlockHidden() {
		super(Material.leaves);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	
	@Override
	public boolean isOpaqueCube() {
		return true;
	}

}
