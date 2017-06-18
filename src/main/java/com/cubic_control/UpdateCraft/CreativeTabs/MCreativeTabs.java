package com.cubic_control.UpdateCraft.CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;

public class MCreativeTabs {
	public static CreativeTabs tabBlocks;
	public static CreativeTabs tabItems;
	
	public static void createTabs(){
		tabBlocks = new CreativeTabBlocks("tabBlocks");
		tabItems = new CreativeTabItems("tabItems");
	}
}
