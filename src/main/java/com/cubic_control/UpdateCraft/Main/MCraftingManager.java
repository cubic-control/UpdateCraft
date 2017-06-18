package com.cubic_control.UpdateCraft.Main;

import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Items.MItems;

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
		//GameRegistry.addRecipe(new ItemStack(MBlocks.end_rod), "X", "Y", 'X', Items.blaze_rod, 'Y', MItems.chorus_fruit_popped);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.end_bricks), "XX", "XX", 'X', Blocks.end_stone);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_block), "XX", "XX", 'X', MItems.chorus_fruit_popped);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_pillar), "X", "X", 'X', MBlocks.purpur_slab);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_stairs), "  X", " XX", "XXX", 'X', MBlocks.purpur_block);
		//GameRegistry.addRecipe(new ItemStack(MBlocks.purpur_slab), "XXX", 'X', MBlocks.purpur_block);
		GameRegistry.addRecipe(new ItemStack(Items.dye, 1, 1), "X", 'X', MItems.beetroot);
		GameRegistry.addRecipe(new ItemStack(MItems.beetroot_soup), "XXX", "XXX", " Y ", 'X', MItems.beetroot, 'Y', Items.bowl);
	}
	public static void removeRecipes(){
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		
		Iterator<IRecipe> Leash = recipes.iterator();
		
			while (Leash.hasNext()) {
				ItemStack is = Leash.next().getRecipeOutput();
				if (is != null && is.getItem() == Item.getItemFromBlock(Blocks.fence)){
					Leash.remove();
				}
				if (is != null && is.getItem() == Item.getItemFromBlock(Blocks.fence_gate)){
					Leash.remove();
				}
				if (is != null && is.getItem() == Items.wooden_door){
					Leash.remove();
				}
				if (is != null && is.getItem() == Items.iron_door){
					Leash.remove();
				}
			};
	}
}
