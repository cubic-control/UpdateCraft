package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Blocks.ModBlockColoredFalling;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MEventColoredBlockChange {
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event){
		Entity entity = event.entity;
		World world = entity.worldObj;
		
		if(entity.isInWater()){
			if(entity instanceof EntityFallingBlock){
				if(((EntityFallingBlock)entity).func_145805_f() instanceof ModBlockColoredFalling){
					
				}
			}
		}
	}

}
