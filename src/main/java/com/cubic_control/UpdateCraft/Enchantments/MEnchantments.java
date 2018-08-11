package com.cubic_control.UpdateCraft.Enchantments;

import net.minecraft.enchantment.Enchantment;

public class MEnchantments {
	public static void main() {
		initEnchantments();
	}
	public static Enchantment frostWalker;
	public static Enchantment mending;
	
	public static void initEnchantments() {
		frostWalker = new ModEnchantmentFrostWalker("frost_walker");
		mending = new ModEnchantmentMending("mending");
	}

}
