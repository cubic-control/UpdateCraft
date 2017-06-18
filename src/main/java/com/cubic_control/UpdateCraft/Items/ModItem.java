package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItem extends Item{
	
	protected ModItem(String par1){
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par1);
		this.setUnlocalizedName(par1);
		GameRegistry.registerItem(this, par1);
	}
	
	protected ModItem(String par1, String par2){
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par2);
		this.setUnlocalizedName(par1);
		GameRegistry.registerItem(this, par1);
	}
}
