package com.cubic_control.UpdateCraft.Utils;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.util.ForgeDirection;

public class ModUtils {
	
	public static ForgeDirection getRandomDirection(Random rand) {
		return ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
	}
	
	public static void replaceItemInRecipe(Item toReplace, Item replacement){
		replaceItemInRecipe(new ItemStack(toReplace), new ItemStack(replacement));
	}
	
	public static void replaceItemInRecipe(Block toReplace, Block replacement){
		replaceItemInRecipe(new ItemStack(toReplace), new ItemStack(replacement));
	}
	
	public static void replaceItemInRecipe(ItemStack toReplace, ItemStack replacement){
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> it = recipes.iterator();
		
		while(it.hasNext()){
			IRecipe recipe = it.next();
			
			// Shaped
			if(recipe instanceof ShapedRecipes){
				ItemStack[] stacks = ((ShapedRecipes)recipe).recipeItems;
				
				for(int i1 = 0; i1 < stacks.length; i1++){
					if(stacks[i1] != null){
						if(stacks[i1] == toReplace){
							stacks[i1].func_150996_a(replacement.getItem());
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
						
						if(stack == toReplace){
							stack.func_150996_a(replacement.getItem());
						}
					}
				}
			}
			// Output
			ItemStack stack = recipe.getRecipeOutput();
			
			if(stack != null && stack == toReplace){
				stack.func_150996_a(replacement.getItem());
			}
		}
	}
	
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
