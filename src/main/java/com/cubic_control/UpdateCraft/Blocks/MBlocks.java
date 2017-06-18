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
	}
	
	public static void changeBlock(){
		Blocks.packed_ice.setHarvestLevel("pickaxe", 0);
		Blocks.ladder.setHarvestLevel("axe", 0);
		Blocks.melon_block.setHarvestLevel("axe", 0);
		Blocks.trapped_chest.setCreativeTab(CreativeTabs.tabRedstone);
	}
}
