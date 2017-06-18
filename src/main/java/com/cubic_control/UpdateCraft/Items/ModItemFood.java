package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;

public class ModItemFood extends ItemFood{
	
	public ModItemFood(int amount, boolean isWolfFood, String par1) {
		super(amount, isWolfFood);
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par1);
		this.setUnlocalizedName(par1);
		GameRegistry.registerItem(this, par1);
	}

	public ModItemFood(int amount, float saturation, boolean isWolfFood, String par1) {
		super(amount, saturation, isWolfFood);
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par1);
		this.setUnlocalizedName(par1);
		GameRegistry.registerItem(this, par1);
	}

	

}
