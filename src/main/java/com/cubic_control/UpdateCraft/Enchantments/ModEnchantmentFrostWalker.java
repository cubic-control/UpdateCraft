package com.cubic_control.UpdateCraft.Enchantments;

import com.cubic_control.UpdateCraft.Config.MConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModEnchantmentFrostWalker extends Enchantment {

	public ModEnchantmentFrostWalker(String name) {
		super(MConfig.ID_frostWalker, 1, EnumEnchantmentType.armor_feet);
		Enchantment.addToBookList(this);
		this.setName(name);
	}
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return stack != null && stack.getItem() == Items.book;
	}
	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}
	@Override
	public int getMaxLevel() {
		return 2;
	}
}