package com.cubic_control.UpdateCraft.Blocks;

import java.util.Iterator;
import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ModBlockBed extends BlockBed{
	//public static final String[] colours = new String[] {"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
	@SideOnly(Side.CLIENT)
    private IIcon[] field_149980_b;
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149982_M;
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149983_N;
    /**
     * The new beds are tile entities, and I currently do not have the model.. yet.
     * I'll go searching through 1.12's source code for the models eventually,
     * but for now we will be using different beds.
     */
    //TODO: Re-Do class after gathering of real model.
	
	protected ModBlockBed(String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		//this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par4, par5);
		this.setResistance(par6);
		this.setStepSound(par7);
		this.disableStats();
		GameRegistry.registerBlock(this, par2);
	}
	
	protected ModBlockBed(String par2, float par3, float par6, SoundType par7) {
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setResistance(par6);
		this.setStepSound(par7);
		this.disableStats();
		GameRegistry.registerBlock(this, par2);
	}
	
	protected ModBlockBed(String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par3);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par4);
		this.setHarvestLevel(par5, par6);
		this.setResistance(par7);
		this.setStepSound(par8);
		this.disableStats();
		GameRegistry.registerBlock(this, par2);
	}
	//Parent Block
	protected ModBlockBed(Block par1, String par2, float par3, float par4, SoundType par5) {
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par1.getHarvestTool(0), par1.getHarvestLevel(0));
		this.setResistance(par4);
		this.setStepSound(par5);
		this.disableStats();
		GameRegistry.registerBlock(this, par2);
	}
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if(side == 0){
            return Blocks.planks.getBlockTextureFromSide(side);
        }else{
            int k = getDirection(meta);
            int l = Direction.bedDirection[k][side];
            int i1 = isBlockHeadOfBed(meta) ? 1 : 0;
            return (i1 != 1 || l != 2) && (i1 != 0 || l != 3) ? (l != 5 && l != 4 ? this.field_149983_N[i1] : this.field_149982_M[i1]) : this.field_149980_b[i1];
        }
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
		this.field_149983_N = new IIcon[] {reg.registerIcon(this.getTextureName() + "_feet_top"), reg.registerIcon(this.getTextureName() + "_head_top")};
		this.field_149980_b = new IIcon[] {reg.registerIcon(this.getTextureName() + "_feet_end"), reg.registerIcon(RefStrings.MODID + ":" + "bed_head_end")};
		this.field_149982_M = new IIcon[] {reg.registerIcon(this.getTextureName() + "_feet_side"), reg.registerIcon(this.getTextureName() + "_head_side")};
		
    }
	@Override
	public Item getItemDropped(int side, Random random, int meta) {
		if(isBlockHeadOfBed(side)){
			return this == MBlocks.bed_black ? MItems.bed_black :(
        		this == MBlocks.bed_blue ? MItems.bed_blue :(
        		this == MBlocks.bed_brown ? MItems.bed_brown :(
        		this == MBlocks.bed_cyan ? MItems.bed_cyan :(
        		this == MBlocks.bed_gray ? MItems.bed_gray :(
        		this == MBlocks.bed_green ? MItems.bed_green : (
        		this == MBlocks.bed_light_blue ? MItems.bed_light_blue :
        		this == MBlocks.bed_lime ? MItems.bed_lime :(
        	    this == MBlocks.bed_magenta ? MItems.bed_magenta :(
        	    this == MBlocks.bed_orange ? MItems.bed_orange :(
        	    this == MBlocks.bed_pink ? MItems.bed_pink :(
        	    this == MBlocks.bed_purple ? MItems.bed_purple :(
        	    this == MBlocks.bed_red ? MItems.bed_red : (
        	    this == MBlocks.bed_silver ? MItems.bed_silver :
        	    this == MBlocks.bed_white ? MItems.bed_white :(
        	    this == MBlocks.bed_yellow ? MItems.bed_yellow :
        				Item.getItemFromBlock(this))))))))))))));
		}else{
			return null;
		}
    }
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this == MBlocks.bed_black ? MItems.bed_black :(
        		this == MBlocks.bed_blue ? MItems.bed_blue :(
        		this == MBlocks.bed_brown ? MItems.bed_brown :(
        		this == MBlocks.bed_cyan ? MItems.bed_cyan :(
        		this == MBlocks.bed_gray ? MItems.bed_gray :(
        		this == MBlocks.bed_green ? MItems.bed_green : (
        		this == MBlocks.bed_light_blue ? MItems.bed_light_blue :
        		this == MBlocks.bed_lime ? MItems.bed_lime :(
        	    this == MBlocks.bed_magenta ? MItems.bed_magenta :(
        	    this == MBlocks.bed_orange ? MItems.bed_orange :(
        	    this == MBlocks.bed_pink ? MItems.bed_pink :(
        	    this == MBlocks.bed_purple ? MItems.bed_purple :(
        	    this == MBlocks.bed_red ? MItems.bed_red : (
        	    this == MBlocks.bed_silver ? MItems.bed_silver :
        	    this == MBlocks.bed_white ? MItems.bed_white :(
        	    this == MBlocks.bed_yellow ? MItems.bed_yellow :
        				Item.getItemFromBlock(this))))))))))))));
	}
	@Override
	public void setBedOccupied(IBlockAccess world, int x, int y, int z, EntityPlayer player, boolean occupied) {
		if(world instanceof World){
			ModBlockBed.func_149979_a((World)world, x, y, z, occupied);
			if(!occupied){
				// Player woke up
			}
		}
	}
	@Override
	public ChunkCoordinates getBedSpawnPosition(IBlockAccess world, int x, int y, int z, EntityPlayer player) {
		if(world instanceof World){
			return ModBlockBed.func_149977_a((World)world, x, y, z, 0);
		}
		return null;
	}
	@Override
	public int getBedDirection(IBlockAccess world, int x, int y, int z) {
		return ModBlockBed.getDirection(world.getBlockMetadata(x, y, z));
	}
	@Override
	public boolean isBedFoot(IBlockAccess world, int x, int y, int z) {
		return ModBlockBed.isBlockHeadOfBed(world.getBlockMetadata(x, y, z));
	}
	@Override
	public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
        return this instanceof BlockBed;
    }
	// Bounce Code: Slime Block Code
	private boolean bounce = false;
	private double howHigh = 0.0;
	
	@Override
	public void onFallenUpon(World world, int X, int Y, int Z, Entity entity, float entityFallDistance){
    	if(entity.isSneaking()){
    		super.onFallenUpon(world, X, Y, Z, entity, entityFallDistance);
    	}else{
    		entity.fallDistance = (entityFallDistance / 2);
    		bounce = true;
    		howHigh =-(entity.motionY / 1.5);
    	}
    }
	@Override
	public void onEntityCollidedWithBlock(World world, int X, int Y, int Z, Entity entity){
		if(!entity.isSneaking()){
			if(bounce){
				entity.motionY = howHigh;
				bounce=false;
			}else if(Math.abs(entity.motionY) < 0.1D){
	            double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
	            entity.motionX *= d0;
	            entity.motionZ *= d0;
	        }
		}
        super.onEntityCollidedWithBlock(world, X, Y, Z, entity);
    }
	
}
