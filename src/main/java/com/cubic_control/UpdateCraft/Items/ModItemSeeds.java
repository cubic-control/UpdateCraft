package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ModItemSeeds extends ItemSeeds {

	public ModItemSeeds(Block par1, String par2) {
		super(par1, Blocks.farmland);
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par2);
		this.setUnlocalizedName(par2);
		GameRegistry.registerItem(this, par2);
	}

}
