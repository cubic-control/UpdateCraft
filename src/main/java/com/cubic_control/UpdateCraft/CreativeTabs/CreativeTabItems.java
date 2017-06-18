package com.cubic_control.UpdateCraft.CreativeTabs;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Items.MItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabItems extends CreativeTabs {

	public CreativeTabItems(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return MItems.prismarine_crystals;
	}

}
