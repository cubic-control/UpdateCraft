package com.cubic_control.UpdateCraft.Blocks;

import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.ModItemBlockWithMetadata;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ModBlockColoredFalling extends ModBlockColored{
	public static boolean fallInstantly;
	
	protected ModBlockColoredFalling(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1, par2, par3, par4, par5, par6, par7);
	}
	
	protected ModBlockColoredFalling(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1, par2, par3, par6, par7);
	}
	
	protected ModBlockColoredFalling(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1, par2, par3, par4, par5, par6, par7, par8);
	}
	//Parent Block
	protected ModBlockColoredFalling(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1, par2, par3, par4, par5);
	}
	//Falling code
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
    }
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
    }
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
        if(!world.isRemote){
            this.func_149830_m(world, x, y, z);
        }
    }
	
	private void func_149830_m(World world, int x, int y, int z) {
        if(func_149831_e(world, x, y - 1, z) && y >= 0){
            byte b0 = 32;
            
            if(!fallInstantly && world.checkChunksExist(x - b0, y - b0, z - b0, x + b0, y + b0, z + b0)){
                if(!world.isRemote){
                    EntityFallingBlock entity = new EntityFallingBlock(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this, world.getBlockMetadata(x, y, z));
                    this.func_149829_a(entity);
                    world.spawnEntityInWorld(entity);
                }
            }else{
            	world.setBlockToAir(x, y, z);
                
                while(func_149831_e(world, x, y - 1, z) && y > 0){
                    --y;
                }
                if(y > 0){
                	world.setBlock(x, y, z, this);
                }
            }
        }
    }
	
	protected void func_149829_a(EntityFallingBlock entity) {}
	@Override
	public int tickRate(World world) {
        return 2;
    }
	
	public static boolean func_149831_e(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        
        if(block.isAir(world, x, y, z)){
            return true;
        }else if(block == Blocks.fire){
            return true;
        }else{
            Material material = block.getMaterial();
            return material == Material.water ? true : material == Material.lava;
        }
    }
	
	public void func_149828_a(World world, int x, int y, int z, int side) {}
	
}
