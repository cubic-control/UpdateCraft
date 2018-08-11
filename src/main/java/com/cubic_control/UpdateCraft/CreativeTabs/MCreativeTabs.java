package com.cubic_control.UpdateCraft.CreativeTabs;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.creativetab.CreativeTabs;

public class MCreativeTabs {
	public static CreativeTabs tabBlocks;
	public static CreativeTabs tabItems;
	
	public static void createTabs(){
		tabBlocks = new CreativeTabBlocks(RefStrings.MODID + ":tabBlocks");
		tabItems = new CreativeTabItems(RefStrings.MODID + ":tabItems");
	}
}
