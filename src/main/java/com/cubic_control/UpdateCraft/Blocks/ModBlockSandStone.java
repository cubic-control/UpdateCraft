package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModBlockSandStone extends Block {
	private String s;
	
    public IIcon[] icons = new IIcon[6];
	
	public ModBlockSandStone(String par2, String par3){
        super(Material.rock);
        this.setBlockName(par2 + "_" + par3);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(0.8f);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(4f);
		this.setStepSound(Block.soundTypeStone);
		GameRegistry.registerBlock(this, par2 + "_" + par3);
		
		s = par3;
    }
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < 6; i ++) {
	    	if(i == 0){
	    		this.icons[i] = reg.registerIcon(this.getTextureName() + "_bottom");
	    	}else if(i == 1){
	    		this.icons[i] = reg.registerIcon(this.getTextureName() + "_top");
	    	}else if(i == 2 || i == 3 || i == 4 || i == 5 || i == 6){
	    		this.icons[i] = reg.registerIcon(this.getTextureName() + "_" + s);
	    	}
	    }
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    return this.icons[side];
	}
}
