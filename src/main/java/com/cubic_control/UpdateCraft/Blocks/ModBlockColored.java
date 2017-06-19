package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.ModItemBlockWithMetadata;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlockColored extends BlockColored{

	protected ModBlockColored(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par4, par5);
		this.setResistance(par6);
		this.setStepSound(par7);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	
	protected ModBlockColored(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setResistance(par6);
		this.setStepSound(par7);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	
	protected ModBlockColored(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par3);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par4);
		this.setHarvestLevel(par5, par6);
		this.setResistance(par7);
		this.setStepSound(par8);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	//Parent Block
	protected ModBlockColored(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1.getMaterial());
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par1.getHarvestTool(0), par1.getHarvestLevel(0));
		this.setResistance(par4);
		this.setStepSound(par5);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
}
