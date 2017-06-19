package com.cubic_control.UpdateCraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ModBlockConcretePowder extends ModBlockColoredFalling{
	
	protected ModBlockConcretePowder(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1, par2, par3, par4, par5, par6, par7);
	}
	
	protected ModBlockConcretePowder(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1, par2, par3, par6, par7);
	}
	
	protected ModBlockConcretePowder(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1, par2, par3, par4, par5, par6, par7, par8);
	}
	//Parent Block
	protected ModBlockConcretePowder(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1, par2, par3, par4, par5);
	}
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
        this.checkForWater(world, x, y, z);
        super.onBlockAdded(world, x, y, z);
    }
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        this.checkForWater(world, x, y, z);
        super.onNeighborBlockChange(world, x, y, z, block);
    }
	
	private void checkForWater(World world, int x, int y, int z) {
        if(world.getBlock(x, y, z) == this){
        	boolean flag = false;
        	
        	if(flag || world.getBlock(x, y, z - 1).getMaterial() == Material.water){
        		flag = true;
        	}
        	if(flag || world.getBlock(x, y, z + 1).getMaterial() == Material.water){
        		flag = true;
        	}
        	if(flag || world.getBlock(x - 1, y, z).getMaterial() == Material.water){
        		flag = true;
        	}
        	if(flag || world.getBlock(x + 1, y, z).getMaterial() == Material.water){
        		flag = true;
        	}
        	if(flag || world.getBlock(x, y + 1, z).getMaterial() == Material.water){
        		flag = true;
        	}
        	if(flag){
        		int l = world.getBlockMetadata(x, y, z);
        		world.setBlock(x, y, z, MBlocks.concrete);
        		world.setBlockMetadataWithNotify(x, y, z, l, 2);
        	}
        }
    }

}
