package com.cubic_control.UpdateCraft.World;

import java.util.Random;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureFossilSkull01 extends WorldGenerator{
	
	protected Block blockToPlace(Random rand) {
		Block block = null;
		int i = rand.nextInt(5);
		
		if(i <= 1) {
			block = Blocks.coal_ore;
		}else if(i == 2) {
			block = Blocks.air;
		}else if(i >= 3) {
			block = MBlocks.bone_block;
		}
		if(block != null) {
			return block;
		}else {
			return MBlocks.bone_block;
		}
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int i = rand.nextInt(2);
		
		if(i == 0) {
			generate_r0(world, rand, x, y, z);
		}
		return true;
	}

	
	public boolean generate_r0(World world, Random rand, int xPos, int yPos, int zPos) {
		setMetaBlock(world, xPos+1, yPos+1, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+4, yPos+1, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+1, yPos+2, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+2, yPos+2, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+3, yPos+2, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+4, yPos+2, zPos+0, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+2, yPos+3, zPos+0, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+3, zPos+0, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+0, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+0, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+0, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+0, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+0, yPos+1, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+1, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+0, yPos+2, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+2, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+0, yPos+3, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+3, zPos+1, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+1, yPos+4, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+4, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+4, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+4, zPos+1, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+0, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+0, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+0, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+0, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+0, yPos+1, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+1, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+2, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+2, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+3, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+3, zPos+2, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+1, yPos+4, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+4, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+4, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+4, zPos+2, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+0, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+0, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+0, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+0, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+0, yPos+1, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+1, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+2, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+2, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+3, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+3, zPos+3, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+1, yPos+4, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+4, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+4, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+4, zPos+3, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+0, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+0, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+0, yPos+1, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+1, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+2, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+2, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+0, yPos+3, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+5, yPos+3, zPos+4, blockToPlace(rand), 2, 3);
		setMetaBlock(world, xPos+1, yPos+4, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+4, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+4, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+4, zPos+4, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+0, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+0, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+0, yPos+1, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+1, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+0, yPos+2, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+2, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+0, yPos+3, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+5, yPos+3, zPos+5, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+1, yPos+4, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+2, yPos+4, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+4, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+4, zPos+5, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+1, yPos+1, zPos+6, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+2, yPos+1, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+1, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+1, zPos+6, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+1, yPos+2, zPos+6, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+2, yPos+2, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+2, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+2, zPos+6, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+1, yPos+3, zPos+6, blockToPlace(rand), 0, 3);
		setMetaBlock(world, xPos+2, yPos+3, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+3, yPos+3, zPos+6, blockToPlace(rand), 1, 3);
		setMetaBlock(world, xPos+4, yPos+3, zPos+6, blockToPlace(rand), 0, 3);
		//DEBUG
		System.out.println("["+RefStrings.NAME+"]:"+this.getClass().getCanonicalName()+":generate_r0:Structure Generated At_"+xPos+"_"+yPos+"_"+zPos);
		return true;
	}
	
	private boolean setMetaBlock(World world, int xPos, int yPos, int zPos, Block block, int meta, int flag) {
		world.setBlock(xPos, yPos, zPos, block, meta, flag);
		world.setBlockMetadataWithNotify(xPos, yPos, zPos, meta, flag);
		return true;
	}

}
