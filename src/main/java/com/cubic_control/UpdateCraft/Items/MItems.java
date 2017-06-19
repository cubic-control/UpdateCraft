package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MItems {
	public static void main(){
		createItem();
		changeItem();
	}
	//1.8
	public static Item prismarine_crystals;
	public static Item prismarine_shard;
	public static Item mutton;
	public static Item cooked_mutton;
	public static Item rabbit;
	public static Item cooked_rabbit;
	public static Item rabbit_stew;
	public static Item rabbit_hide;
	public static Item rabbit_foot;
	public static Item banner;
	public static Item door_spruce;
	public static Item door_birch;
	public static Item door_jungle;
	public static Item door_acacia;
	public static Item door_dark_oak;
	public static Item armor_stand;
	//1.9
	public static Item beetroot;
	public static Item beetroot_soup;
	public static Item beetroot_seeds;
	public static Item chorus_fruit;
	public static Item dragon_breath;
	public static Item elytra;
	public static Item end_crystal;
	public static Item lingering_potion;
	public static Item chorus_fruit_popped;
	
	public static Item shield;
	public static Item spectral_arrow;
	public static Item tipped_arrow;
	//1.10
	//1.11
	public static Item shulker_shell;
	public static Item totem_of_undying;
	//1.12
	//Universal
	public static Item spawn_egg;
	
	public static void createItem(){
		//1.8
		prismarine_crystals = new ModItem("prismarine_crystals");
		prismarine_shard = new ModItem("prismarine_shard");
		mutton = new ModItemFood(2, 1.2f, true, "mutton_raw");
		cooked_mutton = new ModItemFood(6, 9.6f, true, "mutton_cooked");
		rabbit = new ModItemFood(3, 1.8f, true, "rabbit_raw");
		cooked_rabbit = new ModItemFood(5, 6f, true, "rabbit_cooked");
		rabbit_stew = new ModItemSoup(10, 12f, false, "rabbit_stew");
		rabbit_hide = new ModItem("rabbit_hide");
		rabbit_foot = new ModItemPotionIngredient("rabbit_foot");
		spawn_egg = new ModItemSpawnEgg("endermite", 0x161616, 0x6E6E6E, "endermite");
		spawn_egg = new ModItemSpawnEgg("rabbit", 0x995F40, 0x734831, "rabbit");
		//banner = new ModItemBanner("banner");
		door_spruce = new ModItemDoor("spruce_door", "door_spruce", MBlocks.spruce_door);
		door_birch = new ModItemDoor("birch_door","door_birch", MBlocks.birch_door);
		door_jungle = new ModItemDoor("jungle_door", "door_jungle", MBlocks.jungle_door);
		door_acacia = new ModItemDoor("acacia_door", "door_acacia", MBlocks.acacia_door);
		door_dark_oak = new ModItemDoor("dark_oak_door", "door_dark_oak", MBlocks.dark_oak_door);
		armor_stand = new ModItemArmorStand("wooden_armorstand");
		//1.9
		beetroot = new ModItemFood(1, 1.2f, false, "beetroot");
		beetroot_soup = new ModItemSoup(6, 7.2f, false, "beetroot_soup");
		beetroot_seeds = new ModItemSeeds(MBlocks.beetroots, "beetroot_seeds");
		chorus_fruit = new ModItemChorusFruit("chorus_fruit");
		dragon_breath = new ModItemDragonBreath("dragon_breath");
		elytra = new ModItemElytra("elytra");
		end_crystal = new ModItemEndCrystal("end_crystal");
		lingering_potion = new ModItemLingeringPotion();
		chorus_fruit_popped = new ModItem("chorus_fruit_popped");
		
		shield = new ModItemShield("shield");
		//spectral_arrow = new ModItemSpectralArrow();
		tipped_arrow = new ModItemTippedArrow("tipped_arrow");
		//1.10
		//1.11
		shulker_shell = new ModItem("shulker_shell");
		totem_of_undying = new ModItem("totem").setMaxStackSize(1);
		//1.12
	}
	
	public static void changeItem(){
		Items.wooden_door.setMaxStackSize(64);
		Items.blaze_rod.setFull3D();
	}
}
