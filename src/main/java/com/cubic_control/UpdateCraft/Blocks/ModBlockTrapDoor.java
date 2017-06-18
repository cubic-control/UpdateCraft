package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;

public class ModBlockTrapDoor extends BlockTrapDoor{

	protected ModBlockTrapDoor(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par4, par5);
		this.setResistance(par6);
		this.setStepSound(par7);
		GameRegistry.registerBlock(this, par2);
	}

}
