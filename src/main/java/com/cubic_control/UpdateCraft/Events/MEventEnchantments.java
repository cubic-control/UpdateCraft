package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Enchantments.MEnchantments;
import com.cubic_control.cubic_core.Utils.BlockPos;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

public class MEventEnchantments {
	
	@SubscribeEvent
	public void onFrostWalker(LivingUpdateEvent event) {
		if(!event.entityLiving.worldObj.isRemote) {
			if(event.entityLiving.getEquipmentInSlot(1) != null && event.entityLiving.getEquipmentInSlot(1).getItem() != null) {
				ItemStack stack = event.entityLiving.getEquipmentInSlot(1);
				int level = EnchantmentHelper.getEnchantmentLevel(MEnchantments.frostWalker.effectId, stack);
				
				if(level > 0 && stack != null) {
					if(event.entityLiving.onGround) {
						BlockPos pos = new BlockPos((int)event.entityLiving.posX, (int)event.entityLiving.posY, (int)event.entityLiving.posZ);
						int r = level + 1;
						
						for(int x = -r; x <= r; x++) {
							for(int z = -r; z <= r; z++) {
								Block block = event.entityLiving.worldObj.getBlock(pos.getX() + x, pos.getY() - 1, pos.getZ() + z);
								
								if(block == Blocks.water || block == Blocks.flowing_water) {
									//Frosted Ice Not Yet Created
									System.out.println("Frost Ice Would Be Created At:"+(pos.getX()+x)+"_"+(pos.getY()-1)+"_"+(pos.getZ()+z));
								}
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onMending(PlayerPickupXpEvent event) {
		EntityPlayer player = event.entityPlayer;
		
		if(!player.worldObj.isRemote) {
			for(int i = 0; player.inventory.armorInventory.length > i ; i++) {
				ItemStack stack = player.inventory.armorInventory[i];
				
				if(stack != null && stack.getItemDamage() > 0) {
					mendingLogic(event, stack);
				}
			}
			if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItemDamage() > 0) {
				mendingLogic(event, player.getCurrentEquippedItem());
			}
		}
	}
	
	private void mendingLogic(PlayerPickupXpEvent event, ItemStack stack) {
		if(EnchantmentHelper.getEnchantmentLevel(MEnchantments.mending.effectId, stack) > 0) {
			int xp = event.orb.xpValue;
			
			while(xp > 0 && stack.getItemDamage() > 0) {
				stack.setItemDamage(stack.getItemDamage() - 2);
				xp--;
			}
			if(xp <= 0) {
				event.orb.setDead();
				event.setCanceled(true);
				return;
			}
		}
	}
	

}
