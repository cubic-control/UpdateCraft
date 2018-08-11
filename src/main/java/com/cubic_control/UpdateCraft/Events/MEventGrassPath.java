package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MEventGrassPath {
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.entityPlayer != null){
			World world = event.entityPlayer.worldObj;
			
			if(event.action == Action.RIGHT_CLICK_BLOCK){
				if(world.getBlock(event.x, event.y, event.z) == Blocks.grass){
					ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
					
					if(stack != null && (stack.getItem() instanceof ItemSpade)){
						world.setBlock(event.x, event.y, event.z, MBlocks.grass_path);
						event.entityPlayer.swingItem();
						stack.damageItem(1, event.entityPlayer);
						world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath(), 1.0F, 0.8F);
					}
				}
			}
		}
	}

}
