package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MEventTotem {
	
	@SubscribeEvent
	public void on(LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			if(player.inventory.hasItem(MItems.totem_of_undying)){
				if(player.getHealth() <= event.ammount){
					event.ammount = 0.0F;
					player.setHealth(1.0F);
					player.worldObj.playSoundAtEntity(player, RefStrings.MODID + ":item.totem.use_totem", 1.0F, 1.0F);
					
					if(!player.worldObj.isRemote){
						player.curePotionEffects(new ItemStack(MItems.totem_of_undying));
					}
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 800, 1));
					player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 100, 1));
					
					if(!player.capabilities.isCreativeMode){
						player.inventory.consumeInventoryItem(MItems.totem_of_undying);
					}
				}
			}
		}else{
			if(event.entityLiving.getHeldItem() == new ItemStack(MItems.totem_of_undying)){
				if(event.entityLiving.getHealth() <= event.ammount){
					event.ammount = 0.0F;
					event.entityLiving.setHealth(1.0F);
					event.entityLiving.worldObj.playSoundAtEntity(event.entityLiving, RefStrings.MODID + ":item.totem.use_totem", 1.0F, 1.0F);
					
					if(!event.entityLiving.worldObj.isRemote){
						event.entityLiving.curePotionEffects(new ItemStack(MItems.totem_of_undying));
					}
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.regeneration.id, 800, 1));
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 100, 1));
					--event.entityLiving.getHeldItem().stackSize;
				}
			}
		}
	}

}
