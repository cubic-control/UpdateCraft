package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Blocks.ModBlockBed;

import net.minecraft.block.Block;
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
	public static Item bed_black;
	public static Item bed_red;
	public static Item bed_green;
	public static Item bed_brown;
	public static Item bed_blue;
	public static Item bed_purple;
	public static Item bed_cyan;
	public static Item bed_silver;
	public static Item bed_gray;
	public static Item bed_pink;
	public static Item bed_lime;
	public static Item bed_yellow;
	public static Item bed_light_blue;
	public static Item bed_magenta;
	public static Item bed_orange;
	public static Item bed_white;
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
		bed_black = new ModItemBed("bed_black", MBlocks.bed_black);
		bed_red = new ModItemBed("bed_red", MBlocks.bed_red);
		bed_green = new ModItemBed("bed_green", MBlocks.bed_green);
		bed_brown = new ModItemBed("bed_brown", MBlocks.bed_brown);
		bed_blue = new ModItemBed("bed_blue", MBlocks.bed_blue);
		bed_purple = new ModItemBed("bed_purple", MBlocks.bed_purple);
		bed_cyan = new ModItemBed("bed_cyan", MBlocks.bed_cyan);
		bed_silver = new ModItemBed("bed_silver", MBlocks.bed_silver);
		bed_gray = new ModItemBed("bed_gray", MBlocks.bed_gray);
		bed_pink = new ModItemBed("bed_pink", MBlocks.bed_pink);
		bed_lime = new ModItemBed("bed_lime", MBlocks.bed_lime);
		bed_yellow = new ModItemBed("bed_yellow", MBlocks.bed_yellow);
		bed_light_blue = new ModItemBed("bed_light_blue", MBlocks.bed_light_blue);
		bed_magenta = new ModItemBed("bed_magenta", MBlocks.bed_magenta);
		bed_orange = new ModItemBed("bed_orange", MBlocks.bed_orange);
		bed_white = new ModItemBed("bed_white", MBlocks.bed_white);
	}
	
	public static void changeItem(){
		Items.wooden_door.setMaxStackSize(64);
		Items.blaze_rod.setFull3D();
	}
}
