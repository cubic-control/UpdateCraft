package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModItemDragonBreath extends Item{
	
	public ModItemDragonBreath(String par1) {
		setPotionEffect("-14+13");
		setTextureName(RefStrings.MODID + ":" + par1);
		setContainerItem(Items.glass_bottle);
		setUnlocalizedName(par1);
		setCreativeTab(MCreativeTabs.tabItems);
		GameRegistry.registerItem(this, par1);
	}
}
