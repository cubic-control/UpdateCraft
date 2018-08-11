package com.cubic_control.UpdateCraft.Blocks;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.RegistryNamespaced;
import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockReplaceHelper {
	
	public static boolean replaceBlock(Block toReplace, Class<? extends Block> blockClass){
		Field modifiersField = null;
		try{
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			
			for(Field field : Blocks.class.getDeclaredFields()){
				if(Block.class.isAssignableFrom(field.getType())){
					Block block = (Block)field.get(null);
					
					if(block == toReplace){
						String registryName = Block.blockRegistry.getNameForObject(block);
						int id = Block.getIdFromBlock(block);
						ItemBlock item = (ItemBlock)Item.getItemFromBlock(block);
						System.out.println("Replacing block - "+id+"/"+registryName);
						
						Block newBlock = blockClass.newInstance();//This is where the error happens.
						FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
						registry.putObject(registryName, newBlock);
						//I really have no clue what the proper replacement for registry.putObject is. (GameRegistry does not work/crashes)
						
						Field map = RegistryNamespaced.class.getDeclaredFields()[0];
						map.setAccessible(true);
						((ObjectIntIdentityMap)map.get(registry)).func_148746_a(newBlock,id);
						
						map = FMLControlledNamespacedRegistry.class.getDeclaredField("namedIds");
						map.setAccessible(true);
						((BiMap)map.get(registry)).put(registryName,id);
						
						field.setAccessible(true);
						int modifiers = modifiersField.getInt(field);
						modifiers&=~Modifier.FINAL;
						modifiersField.setInt(field,modifiers);
						field.set(null,newBlock);
						
						Field itemblock = ItemBlock.class.getDeclaredFields()[0];
						itemblock.setAccessible(true);
						modifiers = modifiersField.getInt(itemblock);
						modifiers&=~Modifier.FINAL;
						modifiersField.setInt(itemblock,modifiers);
						itemblock.set(item,newBlock);
						
						System.out.println("Check field: "+field.get(null).getClass());
						System.out.println("Check registry: "+Block.blockRegistry.getObjectById(id).getClass());
						System.out.println("Check item: "+((ItemBlock)Item.getItemFromBlock(newBlock)).field_150939_a.getClass());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean replaceBlock2(Block toReplace, Class<? extends Block> blockClass){
		Field modifiersField = null;
		try{
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			
			for(Field field : Blocks.class.getDeclaredFields()){
				if(Block.class.isAssignableFrom(field.getType())){
					Block block = (Block)field.get(null);
					
					if(block == toReplace){
						String registryName = Block.blockRegistry.getNameForObject(block);
						int id = Block.getIdFromBlock(block);
						ItemBlock item = (ItemBlock)Item.getItemFromBlock(block);
						System.out.println("Replacing block - "+id+"/"+registryName);
						
						Block newBlock = blockClass.newInstance();
						ItemBlock newItem = (ItemBlock)Item.getItemFromBlock(newBlock);
						FMLControlledNamespacedRegistry<Block> registry = GameData.getBlockRegistry();
						GameRegistry.registerBlock(newBlock, newItem.getClass(), trimmer(registryName, "minecraft:".length()));
						
						Field map = RegistryNamespaced.class.getDeclaredFields()[0];
						map.setAccessible(true);
						((ObjectIntIdentityMap)map.get(registry)).func_148746_a(newBlock, id);
						
						map = FMLControlledNamespacedRegistry.class.getDeclaredField("aliases");
						map.setAccessible(true);
						((Map)map.get(registry)).put(trimmer(registryName, "minecraft:".length()), id);
						
						field.setAccessible(true);
						int modifiers = modifiersField.getInt(field);
						modifiers&=~Modifier.FINAL;
						modifiersField.setInt(field, modifiers);
						field.set(null, newBlock);
						
						Field itemblock = ItemBlock.class.getDeclaredFields()[0];
						itemblock.setAccessible(true);
						modifiers = modifiersField.getInt(itemblock);
						modifiers&=~Modifier.FINAL;
						modifiersField.setInt(itemblock, modifiers);
						//itemblock.set(item, newBlock);
						//GameRegistry.registerItem(item, trimmer(registryName, "minecraft:".length()));
						
						Item.itemRegistry.addObject(id, trimmer(registryName, "minecraft:".length()), item);
						
						System.out.println("Check field: "+field.get(null).getClass());
						System.out.println("Check registry: "+Block.blockRegistry.getObjectById(id).getClass());
						System.out.println("Check item: "+((ItemBlock)Item.getItemFromBlock(newBlock)).field_150939_a.getClass());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static String trimmer(String string, int trimLength){
		if(string.length() >= trimLength){
			String trimmed = string.substring(trimLength);
			return trimmed;
		}else{
			return string;
		}
	}
	
}
