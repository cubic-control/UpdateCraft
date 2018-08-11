package com.cubic_control.UpdateCraft.Main;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Utils.ModUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public class MCraftingManager {
	public static void createRecipes(){
		removeRecipes();
		//1.8
		GameRegistry.addRecipe(new ItemStack(MBlocks.diorite), "XY", "YX", 'X', Blocks.cobblestone, 'Y', Items.quartz);
		GameRegistry.addRecipe(new ItemStack(MBlocks.smooth_diorite), "XX", "XX", 'X', MBlocks.diorite);
		GameRegistry.addShapelessRecipe(new ItemStack(MBlocks.andesite), MBlocks.diorite, Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(MBlocks.smooth_andesite), "XX", "XX", 'X', MBlocks.andesite);
		GameRegistry.addShapelessRecipe(new ItemStack(MBlocks.granite), MBlocks.diorite, Items.quartz);
		GameRegistry.addRecipe(new ItemStack(MBlocks.smooth_granite), "XX", "XX", 'X', MBlocks.granite);
		GameRegistry.addRecipe(new ItemStack(MBlocks.dirt), "XY", "YX", 'X', Blocks.dirt, 'Y', Blocks.gravel);
		GameRegistry.addRecipe(new ItemStack(MBlocks.prismarine), "XX", "XX", 'X', MItems.prismarine_shard);
		GameRegistry.addRecipe(new ItemStack(MBlocks.prismarine_bricks), "XXX", "XXX", "XXX", 'X', MItems.prismarine_shard);
		GameRegistry.addRecipe(new ItemStack(MBlocks.dark_prismarine), "XXX", "XYX", "XXX", 'X', MItems.prismarine_shard, 'Y', new ItemStack(Items.dye, 0, 0));
		GameRegistry.addRecipe(new ItemStack(MBlocks.red_sandstone), "XX", "XX", 'X', new ItemStack(Blocks.sand, 1, 1));
		GameRegistry.addRecipe(new ItemStack(MBlocks.smooth_red_sandstone), "XX", "XX", 'X', MBlocks.red_sandstone);
		//
		GameRegistry.addRecipe(new ItemStack(MBlocks.iron_trapdoor), "XX", "XX", 'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(MBlocks.sea_lantern), "XYX", "YYY", "XYX", 'X', MItems.prismarine_shard, 'Y', MItems.prismarine_crystals);
		GameRegistry.addSmelting(new ItemStack(MBlocks.sponge, 1, 1), new ItemStack(MBlocks.sponge, 1, 0), 0f);
		GameRegistry.addRecipe(new ItemStack(Blocks.sponge), "X", 'X', new ItemStack(MBlocks.sponge, 1, 0));
		GameRegistry.addRecipe(new ItemStack(MBlocks.sponge, 1, 0), "X", 'X', new ItemStack(Blocks.sponge));
		GameRegistry.addRecipe(new ItemStack(MBlocks.slime), "XXX", "XXX", "XXX", 'X', Items.slime_ball);
		//GameRegistry.addRecipe(new ItemStack(MItems.banner), "XXX", "XXX", " Y ", 'X', Blocks.wool, 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(Blocks.fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 0), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.spruce_fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 1), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.birch_fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 2), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.jungle_fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 3), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.acacia_fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 4), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.dark_oak_fence, 3), "XYX", "XYX", 'X', new ItemStack(Blocks.planks, 1, 5), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(Blocks.fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 0), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.spruce_fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 1), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.birch_fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 2), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.jungle_fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 3), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.acacia_fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 4), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.dark_oak_fence_gate), "YXY", "YXY", 'X', new ItemStack(Blocks.planks, 1, 5), 'Y', Items.stick);
		GameRegistry.addRecipe(new ItemStack(Items.wooden_door, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 0));
		GameRegistry.addRecipe(new ItemStack(Items.iron_door, 3), "XX", "XX", "XX", 'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(MItems.door_spruce, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 1));
		GameRegistry.addRecipe(new ItemStack(MItems.door_birch, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 2));
		GameRegistry.addRecipe(new ItemStack(MItems.door_jungle, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 3));
		GameRegistry.addRecipe(new ItemStack(MItems.door_acacia, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 4));
		GameRegistry.addRecipe(new ItemStack(MItems.door_dark_oak, 3), "XX", "XX", "XX", 'X', new ItemStack(Blocks.planks, 1, 5));
		GameRegistry.addSmelting(MItems.mutton, new ItemStack(MItems.cooked_mutton), 0f);
		GameRegistry.addSmelting(MItems.rabbit, new ItemStack(MItems.cooked_rabbit), 0f);
		GameRegistry.addRecipe(new ItemStack(MItems.rabbit_stew), " A ", "BCD", " E ", 'A', MItems.cooked_rabbit, 'B', Items.carrot, 'C', Items.baked_potato, 'D', Blocks.brown_mushroom, 'E', Items.bowl);
		GameRegistry.addRecipe(new ItemStack(MItems.rabbit_stew), " A ", "BCD", " E ", 'A', MItems.cooked_rabbit, 'B', Items.carrot, 'C', Items.baked_potato, 'D', Blocks.red_mushroom, 'E', Items.bowl);
		GameRegistry.addRecipe(new ItemStack(Items.leather), "XX", "XX", 'X', MItems.rabbit_hide);
		GameRegistry.addRecipe(new ItemStack(MItems.armor_stand), "sss", " s ", "sSs", 's', Items.stick, 'S', Blocks.stone_slab);
		//1.9
		GameRegistry.addRecipe(new ItemStack(MBlocks.end_rod, 4), "X", "Y", 'X', Items.blaze_rod, 'Y', MItems.chorus_fruit_popped);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.end_bricks), "XX", "XX", 'X', Blocks.end_stone);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_block), "XX", "XX", 'X', MItems.chorus_fruit_popped);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_pillar), "X", "X", 'X', MBlocks.purpur_slab);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_stairs), "  X", " XX", "XXX", 'X', MBlocks.purpur_block);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_slab), "XXX", 'X', MBlocks.purpur_block);
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 1), "X", 'X', MItems.beetroot);
		GameRegistry.addRecipe(new ItemStack(MItems.beetroot_soup), "XXX", "XXX", " Y ", 'X', MItems.beetroot, 'Y', Items.bowl);
		//1.10
		GameRegistry.addRecipe(new ItemStack(MBlocks.magma), "mm", "mm", 'm', Items.magma_cream);
		GameRegistry.addRecipe(new ItemStack(MBlocks.nether_wart_block), "nnn", "nnn", "nnn", 'n', Items.nether_wart);
		GameRegistry.addRecipe(new ItemStack(MBlocks.red_nether_brick), "wb", "bw", 'w', Items.nether_wart, 'b', Items.netherbrick);
		GameRegistry.addRecipe(new ItemStack(MBlocks.bone_block), "bbb", "bbb", "bbb", 'b', new ItemStack(Items.dye, 1, 15));
		GameRegistry.addRecipe(new ItemStack(Items.dye, 9, 15), "X", 'X', MBlocks.bone_block);
		//1.11
		//1.12
		for(int i = 0; i < 16; i++){
			int k = (16 - i) - 1;
			if(k < 0){
				k = -k;
			}
			//DEBUG:
			//System.out.println("k = " + k);
			GameRegistry.addRecipe(new ItemStack(MBlocks.concrete_powder, 1, i), "SGS", "GdG", "SGS", 'S', Blocks.sand, 'G', Blocks.gravel, 'd', new ItemStack(Items.dye, 1, k));
		}
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 0), new ItemStack(MBlocks.white_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 1), new ItemStack(MBlocks.orange_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 2), new ItemStack(MBlocks.magenta_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 3), new ItemStack(MBlocks.light_blue_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 4), new ItemStack(MBlocks.yellow_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 5), new ItemStack(MBlocks.lime_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 6), new ItemStack(MBlocks.pink_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 7), new ItemStack(MBlocks.gray_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 8), new ItemStack(MBlocks.silver_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 9), new ItemStack(MBlocks.cyan_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 10), new ItemStack(MBlocks.purple_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 11), new ItemStack(MBlocks.blue_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 12), new ItemStack(MBlocks.brown_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 13), new ItemStack(MBlocks.green_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 14), new ItemStack(MBlocks.red_glazed_terracotta), 0f);
		GameRegistry.addSmelting(new ItemStack(Blocks.stained_hardened_clay, 1, 15), new ItemStack(MBlocks.black_glazed_terracotta), 0f);
		GameRegistry.addRecipe(new ItemStack(MItems.bed_white), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 0), 'w', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(MItems.bed_orange), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 1), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_orange), MItems.bed_white, new ItemStack(Items.dye, 1, 14));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_magenta), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 2), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_magenta), MItems.bed_white, new ItemStack(Items.dye, 1, 13));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_light_blue), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 3), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_light_blue), MItems.bed_white, new ItemStack(Items.dye, 1, 12));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_yellow), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 4), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_yellow), MItems.bed_white, new ItemStack(Items.dye, 1, 11));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_lime), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 5), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_lime), MItems.bed_white, new ItemStack(Items.dye, 1, 10));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_pink), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 6), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_pink), MItems.bed_white, new ItemStack(Items.dye, 1, 9));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_gray), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 7), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_gray), MItems.bed_white, new ItemStack(Items.dye, 1, 8));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_silver), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 8), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_silver), MItems.bed_white, new ItemStack(Items.dye, 1, 7));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_cyan), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 9), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_cyan), MItems.bed_white, new ItemStack(Items.dye, 1, 6));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_purple), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 10), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_purple), MItems.bed_white, new ItemStack(Items.dye, 1, 5));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_blue), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 11), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_blue), MItems.bed_white, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_brown), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 12), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_brown), MItems.bed_white, new ItemStack(Items.dye, 1, 3));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_green), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 13), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_green), MItems.bed_white, new ItemStack(Items.dye, 1, 2));
		GameRegistry.addRecipe(new ItemStack(MItems.bed_red), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 14), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_red), MItems.bed_white, new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_red), Items.bed);
		GameRegistry.addRecipe(new ItemStack(MItems.bed_black), "xxx", "www", 'x', new ItemStack(Blocks.wool, 1, 15), 'w', Blocks.planks);
		GameRegistry.addShapelessRecipe(new ItemStack(MItems.bed_black), MItems.bed_white, new ItemStack(Items.dye, 1, 0));
	}
	public static void removeRecipes(){
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		
		Iterator<IRecipe> Leash = recipes.iterator();
		
			while(Leash.hasNext()){
				ItemStack is = Leash.next().getRecipeOutput();
				if(is != null && is.getItem() == Item.getItemFromBlock(Blocks.fence)){
					Leash.remove();
				}
				if(is != null && is.getItem() == Item.getItemFromBlock(Blocks.fence_gate)){
					Leash.remove();
				}
				if(is != null && is.getItem() == Items.wooden_door){
					Leash.remove();
				}
				if(is != null && is.getItem() == Items.iron_door){
					Leash.remove();
				}
			};
	}
	public static void changeRecipes(){
		ModUtils.replaceItemInRecipe(Blocks.piston, MBlocks.piston);
		ModUtils.replaceItemInRecipe(Blocks.sticky_piston, MBlocks.stickyPiston);
		ModUtils.replaceItemInRecipe(Blocks.fence, MBlocks.oak_fence);
		GameRegistry.addShapelessRecipe(new ItemStack(MBlocks.piston), Blocks.piston);
		GameRegistry.addShapelessRecipe(new ItemStack(MBlocks.stickyPiston), Blocks.sticky_piston);
		GameRegistry.addShapelessRecipe(new ItemStack(MBlocks.oak_fence), Blocks.fence);
		/*
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> it = recipes.iterator();
		
		while(it.hasNext()){
			IRecipe recipe = it.next();
			
			// Shaped
			if(recipe instanceof ShapedRecipes){
				ItemStack[] stacks = ((ShapedRecipes)recipe).recipeItems;
				
				for(int i1 = 0; i1 < stacks.length; i1++){
					if(stacks[i1] != null){
						Item item = stacks[i1].getItem();
						
						if(item == Item.getItemFromBlock(Blocks.piston)){
							stacks[i1].func_150996_a(Item.getItemFromBlock(MBlocks.piston));
						}
						if(item == Item.getItemFromBlock(Blocks.sticky_piston)){
							stacks[i1].func_150996_a(Item.getItemFromBlock(MBlocks.stickyPiston));
						}
						if(item == Item.getItemFromBlock(Blocks.fence)){
							stacks[i1].func_150996_a(Item.getItemFromBlock(MBlocks.oak_fence));
						}
					}
				}
			}
			// Shapeless
			if(recipe instanceof ShapelessRecipes){
				List list = ((ShapelessRecipes)recipe).recipeItems;
				
				for(int i1 = 0; i1 < list.size(); i1++){
					if(list.get(i1) != null){
						ItemStack stack = (ItemStack)list.get(i1);
						Item item = stack.getItem();
						
						if(item == Item.getItemFromBlock(Blocks.piston)){
							stack.func_150996_a(Item.getItemFromBlock(MBlocks.piston));
						}
						if(item == Item.getItemFromBlock(Blocks.sticky_piston)){
							stack.func_150996_a(Item.getItemFromBlock(MBlocks.stickyPiston));
						}
						if(item == Item.getItemFromBlock(Blocks.fence)){
							stack.func_150996_a(Item.getItemFromBlock(MBlocks.oak_fence));
						}
					}
				}
			}
			// Output
			ItemStack stack = recipe.getRecipeOutput();
			
			if(stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.piston)){
				stack.func_150996_a(Item.getItemFromBlock(MBlocks.piston));
			}
			if(stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.sticky_piston)){
				stack.func_150996_a(Item.getItemFromBlock(MBlocks.stickyPiston));
			}
		}*/
	}
	
}
