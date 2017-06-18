package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.cubic_core.Utils.CubicUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MEventVanilaAIChange {
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event){
		if(event.entity instanceof EntityChicken){
			((EntityChicken)event.entity).tasks.addTask(3, new EntityAITempt(((EntityChicken)event.entity), 1.0D, MItems.beetroot_seeds, false));
		}
		if(event.entity instanceof EntitySkeleton){
			((EntitySkeleton)event.entity).tasks.addTask(4, new EntityAIAvoidEntity(((EntitySkeleton)event.entity), EntityWolf.class, 6.0F, 1.0D, 1.2D));
			if(((EntitySkeleton)event.entity).getSkeletonType() == 1){
				if(event.world.isRemote){
					((EntitySkeleton)event.entity).boundingBox.maxY = ((EntitySkeleton)event.entity).boundingBox.minY + 3.0f;
				}
			}
		}
		if(event.entity instanceof EntityEnderman){
			//TODO: Find new speed..
			//((EntityEnderman)event.entity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
		}
		if(event.entity instanceof EntityWolf){
			((EntityWolf)event.entity).targetTasks.addTask(4, new EntityAITargetNonTamed(((EntityWolf)event.entity), EntitySkeleton.class, 200, false));
		}
	}
	@SubscribeEvent
	public void onEntityStruckByLightningEvent(EntityStruckByLightningEvent event){
		if(event.entity instanceof EntityVillager){
			if(!event.entity.worldObj.isRemote){
				EntityWitch witch = new EntityWitch(event.entity.worldObj);
				witch.setLocationAndAngles(event.entity.posX, event.entity.posY, event.entity.posZ, MathHelper.wrapAngleTo180_float(event.entity.worldObj.rand.nextFloat()*360.0F), 0.0F);
				event.entity.worldObj.spawnEntityInWorld(witch);
				witch.onSpawnWithEgg((IEntityLivingData)null);
				witch.playLivingSound();
				event.entity.setDead();
			}
		}
	}
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event){
		if(event.entityLiving instanceof EntitySheep){
			if(!event.entityLiving.worldObj.isRemote){
				if(event.source.isFireDamage()){
					((EntitySheep)event.entityLiving).dropItem(MItems.cooked_mutton, 1 + CubicUtils.rand.nextInt(1));
				}else{
					((EntitySheep)event.entityLiving).dropItem(MItems.mutton, 1 + CubicUtils.rand.nextInt(1));
				}
			}
		}
	}

}
