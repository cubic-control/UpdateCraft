package com.cubic_control.UpdateCraft.Enchantments;

import com.cubic_control.UpdateCraft.Config.MConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModEnchantmentMending extends Enchantment{

	public ModEnchantmentMending(String name) {
		super(MConfig.ID_mending, 2, EnumEnchantmentType.breakable);
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
		return 1;
	}
}