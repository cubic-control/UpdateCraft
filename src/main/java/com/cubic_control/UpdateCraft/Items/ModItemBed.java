package com.cubic_control.UpdateCraft.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.cubic_control.UpdateCraft.Blocks.ModBlockBed;
import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItemBed extends Item{
	private Block block;
	
	public ModItemBed(String name, Block bed) {
        this.setCreativeTab(MCreativeTabs.tabItems);
        this.setUnlocalizedName(name+"_item");
        this.setTextureName(RefStrings.MODID+":"+name);
        GameRegistry.registerItem(this, name+"_item");
        this.block = (ModBlockBed)bed;
    }
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        if(world.isRemote){
            return true;
        }else if(side != 1){
            return false;
        }else{
            ++y;
            int i1 = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;
            
            if(i1 == 0){
                b1 = 1;
            }
            if(i1 == 1){
                b0 = -1;
            }
            if(i1 == 2){
                b1 = -1;
            }
            if(i1 == 3){
                b0 = 1;
            }
            if(player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x + b0, y, z + b1, side, stack)){
                if(world.isAirBlock(x, y, z) && world.isAirBlock(x + b0, y, z + b1) && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && World.doesBlockHaveSolidTopSurface(world, x + b0, y - 1, z + b1)){
                	world.setBlock(x, y, z, block, i1, 3);
                    
                    if(world.getBlock(x, y, z) == block){
                    	world.setBlock(x + b0, y, z + b1, block, i1 + 8, 3);
                    }
                    --stack.stackSize;
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }
	
}
