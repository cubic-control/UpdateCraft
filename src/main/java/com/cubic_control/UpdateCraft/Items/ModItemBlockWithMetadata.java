package com.cubic_control.UpdateCraft.Items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ModItemBlockWithMetadata extends ItemBlockWithMetadata{

	public ModItemBlockWithMetadata(Block block) {
		super(block, block);
	}
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

}
