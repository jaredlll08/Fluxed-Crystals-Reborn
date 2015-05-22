package fluxedCrystals.blocks.crystal;

import fluxedCrystals.init.FCBlocks;
import fluxedCrystals.registry.*;
import fluxedCrystals.tileEntity.TileEntityCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jared on 5/20/2015.
 */
public class BlockMutationCrystal extends Block
{

	public BlockMutationCrystal () {
		super(Material.plants);
	}

	@Override
	public void updateTick (World world, int x, int y, int z, Random random) {
		ForgeDirection[] directions = new ForgeDirection[]{ForgeDirection.EAST, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST};
		ArrayList<Seed> crystalSeeds = new ArrayList<Seed>();

		if (world.getBlock(x, y, z + 1) != null && world.getBlock(x, y, z - 1) != null) {
			if (world.getBlock(x, y, z + 1) instanceof BlockCrystal && world.getBlock(x, y, z - 1) instanceof BlockCrystal) {
				TileEntityCrystal crys1 = (TileEntityCrystal) world.getTileEntity(x, y, z + 1);
				TileEntityCrystal crys2 = (TileEntityCrystal) world.getTileEntity(x, y, z - 1);
				Seed seed1 = SeedRegistry.getInstance().getSeedByID(crys1.getIndex());
				Seed seed2 = SeedRegistry.getInstance().getSeedByID(crys2.getIndex());
				if (MutationRegistry.getMutationFromNames(seed1.name, seed2.name) != null) {
					Mutation mut = MutationRegistry.getMutationFromNames(seed1.name, seed2.name);
					Seed mutated = SeedRegistry.getInstance().getSeedFromName(mut.outputSeed);
					world.setBlock(x, y, z, FCBlocks.crystal);
					((TileEntityCrystal) world.getTileEntity(x, y, z)).setIdx(mutated.seedID);

				}

			}
		}

		if (world.getBlock(x + 1, y, z) != null && world.getBlock(x - 1, y, z) != null) {
			if (world.getBlock(x + 1, y, z) instanceof BlockCrystal && world.getBlock(x + 1, y, z) instanceof BlockCrystal) {
				TileEntityCrystal crys1 = (TileEntityCrystal) world.getTileEntity(x + 1, y, z);
				TileEntityCrystal crys2 = (TileEntityCrystal) world.getTileEntity(x - 1, y, z);
				Seed seed1 = SeedRegistry.getInstance().getSeedByID(crys1.getIndex());
				Seed seed2 = SeedRegistry.getInstance().getSeedByID(crys2.getIndex());
				if (MutationRegistry.getMutationFromNames(seed1.name, seed2.name) != null) {
					Mutation mut = MutationRegistry.getMutationFromNames(seed1.name, seed2.name);
					Seed mutated = SeedRegistry.getInstance().getSeedFromName(mut.outputSeed);
					world.setBlock(x, y, z, FCBlocks.crystal);
					((TileEntityCrystal) world.getTileEntity(x, y, z)).setIdx(mutated.seedID);

				}

			}
		}

	}

}
