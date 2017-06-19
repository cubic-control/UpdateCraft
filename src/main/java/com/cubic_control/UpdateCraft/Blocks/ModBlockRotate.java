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
	//TODO: Based on BlockRotatedPillar, experimentation needed.
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        int j1 = meta & 3;
        byte b0 = 0;
        
        switch(side){
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }
        return j1 | b0;
    }

}
