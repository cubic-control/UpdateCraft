package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.Main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

public class MBlocks {
	public static void Main(){
		createBlock();
		changeBlock();
		MOreDictionary.initModEntries();
	}
	//1.8
	public static Block diorite;
	public static Block smooth_diorite;
	public static Block andesite;
	public static Block smooth_andesite;
	public static Block granite;
	public static Block smooth_granite;
	public static Block dirt;
	public static Block prismarine;
	public static Block prismarine_bricks;
	public static Block dark_prismarine;
	public static Block red_sandstone;
	public static Block smooth_red_sandstone;
	public static Block chiseled_red_sandstone;
	public static Block iron_trapdoor;
	public static Block sea_lantern;
	public static Block sponge;
	public static Block slime;
	public static Block standing_banner;
	public static Block wall_banner;
	public static Block barrier;
	public static Block spruce_fence;
	public static Block birch_fence;
	public static Block jungle_fence;
	public static Block dark_oak_fence;
	public static Block acacia_fence;
	public static Block spruce_fence_gate;
	public static Block birch_fence_gate;
	public static Block jungle_fence_gate;
	public static Block dark_oak_fence_gate;
	public static Block acacia_fence_gate;
	public static Block spruce_door;
	public static Block birch_door;
	public static Block jungle_door;
	public static Block acacia_door;
	public static Block dark_oak_door;
	//1.9
	public static Block beetroots;
	public static Block chorus_flower;
	public static Block chorus_plant;
	//1.10
	public static Block magma;
	public static Block nether_wart_block;
	public static Block red_nether_brick;
	public static Block bone_block;
	//1.11
	//1.12
	public static Block concrete;
	public static Block concrete_powder;
	public static Block white_glazed_terracotta;
	public static Block orange_glazed_terracotta;
	public static Block magenta_glazed_terracotta;
	public static Block light_blue_glazed_terracotta;
	public static Block yellow_glazed_terracotta;
	public static Block lime_glazed_terracotta;
	public static Block pink_glazed_terracotta;
	public static Block gray_glazed_terracotta;
	public static Block silver_glazed_terracotta;
	public static Block cyan_glazed_terracotta;
	public static Block purple_glazed_terracotta;
	public static Block blue_glazed_terracotta;
	public static Block brown_glazed_terracotta;
	public static Block green_glazed_terracotta;
	public static Block red_glazed_terracotta;
	public static Block black_glazed_terracotta;
	
	public static void createBlock(){
		//1.8
		diorite = new ModBlock(Material.rock, "diorite", 1.5f, "pickaxe", 0, 30f, Block.soundTypeStone);
		smooth_diorite = new ModBlock(diorite, "diorite_smooth", 1.5f, 30f, Block.soundTypeStone);
		andesite = new ModBlock(Material.rock, "andesite", 1.5f, "pickaxe", 0, 30f, Block.soundTypeStone);
		smooth_andesite = new ModBlock(andesite, "andesite_smooth", 1.5f, 30f, Block.soundTypeStone);
		granite = new ModBlock(Material.rock, "granite", 1.5f, "pickaxe", 0, 30f, Block.soundTypeStone);
		smooth_granite = new ModBlock(granite, "granite_smooth", 1.5f, 30f, Block.soundTypeStone);
		dirt = new ModBlock(Blocks.dirt, "coarse_dirt", 0.5f, 2.5f, Block.soundTypeGrass);
		prismarine = new ModBlock(Material.rock, "prismarine_rough", 1.5f, "pickaxe", 0, 30f, Block.soundTypeStone);
		prismarine_bricks = new ModBlock(prismarine, "prismarine_bricks", 1.5f, 30f, Block.soundTypeStone);
		dark_prismarine = new ModBlock(prismarine, "prismarine_dark", 1.5f, 30f, Block.soundTypeStone);
		red_sandstone = new ModBlockSandStone("red_sandstone", "normal");
		smooth_red_sandstone = new ModBlockSandStone("red_sandstone", "smooth");
		chiseled_red_sandstone = new ModBlockSandStone("red_sandstone", "carved");
		iron_trapdoor = new ModBlockTrapDoor(Material.iron, "iron_trapdoor", 5f, "pickaxe", 0, 25f, Block.soundTypeMetal);
		sea_lantern = new ModBlock(Material.glass, "sea_lantern", 0.3f, 1.5f, Block.soundTypeGlass).setLightLevel(1f);
		sponge = new ModBlockSponge();
		slime = new ModBlockSlime("slime");
		spruce_fence = new ModBlockFence("planks_spruce", Material.wood, "spruce_fence");
		birch_fence = new ModBlockFence("planks_birch", Material.wood, "birch_fence");
		jungle_fence = new ModBlockFence("planks_jungle", Material.wood, "jungle_fence");
		dark_oak_fence = new ModBlockFence("planks_big_oak", Material.wood, "dark_oak_fence");
		acacia_fence = new ModBlockFence("planks_acacia", Material.wood, "acacia_fence");
		spruce_fence_gate = new ModBlockFenceGate("planks_spruce", "spruce_fence_gate");
		birch_fence_gate = new ModBlockFenceGate("planks_birch", "birch_fence_gate");
		jungle_fence_gate = new ModBlockFenceGate("planks_jungle", "jungle_fence_gate");
		dark_oak_fence_gate = new ModBlockFenceGate("planks_big_oak", "dark_oak_fence_gate");
		acacia_fence_gate = new ModBlockFenceGate("planks_acacia", "acacia_fence_gate");
		spruce_door = new ModBlockDoor(Material.wood, "door_spruce");
		birch_door = new ModBlockDoor(Material.wood, "door_birch");
		jungle_door = new ModBlockDoor(Material.wood, "door_jungle");
		acacia_door = new ModBlockDoor(Material.wood, "door_acacia");
		dark_oak_door = new ModBlockDoor(Material.wood, "door_dark_oak");
		//1.9
		beetroots = new ModBlockBeetroots("beetroots");
		chorus_flower = new ModBlockChorusFlower("chorus_flower");
		chorus_plant = new ModBlockChorusPlant("chorus_plant");
		//1.10
		magma = new ModBlockMagma(Material.rock, "magma", 0.5F, "pickaxe", 0, 2.5F, Block.soundTypeStone);
		nether_wart_block = new ModBlock(Material.cloth, "nether_wart_block", 1.0F, 5.0F, Block.soundTypeCloth);
		red_nether_brick = new ModBlock(Material.rock, "red_nether_brick", 2.0F, "pickaxe", 0, 30.0F, Block.soundTypeStone);
		bone_block = new ModBlockPillar(Material.rock, "bone_block", 2.0F, "pickaxe", 0, 10.0F, Block.soundTypeStone);
		//1.11
		//1.12
		concrete = new ModBlockColored(Material.rock, "concrete", 1.8F, "pickaxe", 0, 9.0F, Block.soundTypeStone);
		concrete_powder = new ModBlockConcretePowder(Material.sand, "concrete_powder", 0.5F, "shovel", 0, 2.5F, Block.soundTypeGravel);
		white_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_white", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		orange_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_orange", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		magenta_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_magenta", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		light_blue_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_light_blue", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		yellow_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_yellow", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		lime_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_lime", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		pink_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_pink", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		gray_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_gray", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		silver_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_silver", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		cyan_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_cyan", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		purple_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_purple", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		blue_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_blue", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		brown_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_brown", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		green_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_green", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		red_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_red", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
		black_glazed_terracotta = new ModBlockRotate(Material.rock, "glazed_terracotta_black", 1.4F, "pickaxe", 0, 7.0F, Block.soundTypeStone);
	}
	
	public static void changeBlock(){
		Blocks.packed_ice.setHarvestLevel("pickaxe", 0);
		Blocks.ladder.setHarvestLevel("axe", 0);
		Blocks.melon_block.setHarvestLevel("axe", 0);
		Blocks.trapped_chest.setCreativeTab(CreativeTabs.tabRedstone);
	}
}
