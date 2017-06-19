package com.cubic_control.UpdateCraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.ModItemBlockWithMetadata;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlockRotate extends ModBlock{
	
	protected ModBlockRotate(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1, par2, par3, par4, par5, par6, par7);
	}
	
	protected ModBlockRotate(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1, par2, par3, par6, par7);
	}
	
	protected ModBlockRotate(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1, par2, par3, par4, par5, par6, par7, par8);
	}
	//Parent Block
	protected ModBlockRotate(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1, par2, par3, par4, par5);
	}
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if(l == 0){
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        if(l == 1){
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if(l == 2){
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if(l == 3){
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
	}
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.func_149930_e(world, x, y, z);
    }

    private void func_149930_e(World world, int x, int y, int z) {
        if(!world.isRemote){
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b0 = 3;
            
            if(block.func_149730_j() && !block1.func_149730_j()){
                b0 = 3;
            }
            if(block1.func_149730_j() && !block.func_149730_j()){
                b0 = 2;
            }
            if(block2.func_149730_j() && !block3.func_149730_j()){
                b0 = 5;
            }
            if(block3.func_149730_j() && !block2.func_149730_j()){
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

}
