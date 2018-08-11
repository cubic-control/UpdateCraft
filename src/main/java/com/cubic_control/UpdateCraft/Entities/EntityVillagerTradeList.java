package com.cubic_control.UpdateCraft.Entities;

import java.util.Map;

import com.cubic_control.UpdateCraft.Enchantments.MEnchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;

public class EntityVillagerTradeList{
	//Item, new Tuple(Minimum Value, Maximum Value)
	public static void init() {
		Map map = EntityVillager.villagersSellingList;
		map.put(getEnchantedBook(MEnchantments.frostWalker, 1), new Tuple(Integer.valueOf(5), Integer.valueOf(64)));
		map.put(getEnchantedBook(MEnchantments.mending, 1), new Tuple(Integer.valueOf(5), Integer.valueOf(64)));
	}
	
	private static ItemStack getEnchantedBook(Enchantment enchantment, int level) {
		ItemStack stack = new ItemStack(Items.enchanted_book);
		Items.enchanted_book.addEnchantment(stack, new EnchantmentData(enchantment, level));
		return stack;
	}
}
