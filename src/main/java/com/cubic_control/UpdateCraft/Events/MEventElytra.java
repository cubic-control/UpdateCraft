package com.cubic_control.UpdateCraft.Events;

import java.util.Set;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent;

import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Items.ModItemElytra;
import com.cubic_control.UpdateCraft.Main.MainRegistry;
import com.cubic_control.UpdateCraft.Utils.FieldImitations;
import com.cubic_control.UpdateCraft.Utils.MethodImitations;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class MEventElytra{
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onLivingTick(LivingUpdateEvent event) {
		if(event.isCanceled()){
			return;
		}
		MainRegistry.proxy.update(event.entityLiving);
		updateElytra(event.entityLiving);
	}
	
	@SubscribeEvent
	public void onPostPlayerTick(PlayerTickEvent event) {
		if(event.phase == Phase.END){
			boolean isElytraFlying = MethodImitations.isElytraFlying(event.player);
			
			if(event.player instanceof EntityPlayerMP && isElytraFlying){
				//((EntityPlayerMP)event.player).playerNetServerHandler.floatingTickCount = 0;
			}
			if(isElytraFlying != FieldImitations.get(event.player, "lastIsElytraFlying", false)){
				float f = 0.6f;
				float f1 = isElytraFlying ? 0.6f : 1.8f;
				
				if(f != event.player.width || f1 != event.player.height){
					AxisAlignedBB axisalignedbb = event.player.boundingBox;
					axisalignedbb = AxisAlignedBB.getBoundingBox(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + f, axisalignedbb.minY + f1, axisalignedbb.minZ + f);
					
					if(event.player.worldObj.func_147461_a(axisalignedbb).isEmpty()){
						float f2 = event.player.width;
						event.player.width = f;
						event.player.height = f1;
						event.player.boundingBox.setBounds(event.player.boundingBox.minX, event.player.boundingBox.minY, event.player.boundingBox.minZ, event.player.boundingBox.minX + event.player.width, event.player.boundingBox.minY + event.player.height, event.player.boundingBox.minZ + event.player.width);
						
						if(event.player.width > f2 && !event.player.worldObj.isRemote){
							event.player.moveEntity(f2 - event.player.width, 0.0D, f2 - event.player.width);
						}
					}
				}
				FieldImitations.set(event.player, "lastIsElytraFlying", isElytraFlying);
			}
		}
	}
	
	@SubscribeEvent
	public void onPostWorldTick(WorldTickEvent event) {
		if(event.phase == Phase.END && event.world instanceof WorldServer){
			WorldServer ws = (WorldServer)event.world;
			//for(EntityTrackerEntry ete : (Set<EntityTrackerEntry>)ws.getEntityTracker().trackedEntities){
				//if(ete.trackedEntity instanceof EntityLivingBase){
					//EntityLivingBase elb = (EntityLivingBase) ete.trackedEntity;
					//boolean flying = MethodImitations.isElytraFlying(elb);
					//if(!flying && FieldImitations.get(ete, "wasForcingVelocityUpdates", false)){
						//ete.sendVelocityUpdates = false;
					//}else if(flying){
						//if(!ete.sendVelocityUpdates){
							//FieldImitations.get(ete, "wasForcingVelocityUpdates", true);
						//}
						//ete.sendVelocityUpdates = true;
					//}
				//}
			//}
		}
	}
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		event.world.getGameRules().addGameRule("disableElytraMovementCheck", "false");
	}
	
	private void updateElytra(EntityLivingBase entity) {
		boolean flag = MethodImitations.isElytraFlying(entity);

		if(flag && !entity.onGround && !entity.isRiding() && !entity.isInWater()){
			ItemStack itemstack = entity.getEquipmentInSlot(3);
			
			if(itemstack != null && itemstack.getItem() == MItems.elytra && ModItemElytra.isBroken(itemstack)){
				flag = true;
				
				if(!entity.worldObj.isRemote && (MethodImitations.getTicksElytraFlying(entity) + 1) % 20 == 0){
					itemstack.damageItem(1, entity);
				}
			}else{
				flag = false;
			}
		}else{
			flag = false;
		}
		if(!entity.worldObj.isRemote){
			MethodImitations.setElytraFlying(entity, flag);
		}
		if(MethodImitations.isElytraFlying(entity)){
			FieldImitations.set(entity, "ticksElytraFlying", FieldImitations.get(entity, "ticksElytraFlying", 0)+1);
		}else{
			FieldImitations.set(entity, "ticksElytraFlying", 0);
		}
	}
	
	public static DamageSource flyIntoWall = (new DamageSource("flyIntoWall")).setDamageBypassesArmor();
	
	public static boolean moveEntityWithHeading(EntityLivingBase entity, float strafe, float forward) {
		if(!entity.worldObj.isRemote && !entity.isInWater() && !entity.handleLavaMovement()){
			if(MethodImitations.isElytraFlying(entity)){
				if(entity.motionY > -0.5D){
					entity.fallDistance = 1.0F;
				}
				Vec3 vec3d = entity.getLookVec();
				float f = entity.rotationPitch * 0.017453292F;
				double d6 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
				double d8 = Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
				double d1 = vec3d.lengthVector();
				float f4 = MathHelper.cos(f);
				f4 = (float) ((double) f4 * (double) f4 * Math.min(1.0D, d1 / 0.4D));
				entity.motionY += -0.08D + f4 * 0.06D;
				
				if(entity.motionY < 0.0D && d6 > 0.0D){
					double d2 = entity.motionY * -0.1D * f4;
					entity.motionY += d2;
					entity.motionX += vec3d.xCoord * d2 / d6;
					entity.motionZ += vec3d.zCoord * d2 / d6;
				}
				if(f < 0.0F){
					double d9 = d8 * (-MathHelper.sin(f)) * 0.04D;
					entity.motionY += d9 * 3.2D;
					entity.motionX -= vec3d.xCoord * d9 / d6;
					entity.motionZ -= vec3d.zCoord * d9 / d6;
				}
				if(d6 > 0.0D){
					entity.motionX += (vec3d.xCoord / d6 * d8 - entity.motionX) * 0.1D;
					entity.motionZ += (vec3d.zCoord / d6 * d8 - entity.motionZ) * 0.1D;
				}
				entity.motionX *= 0.9900000095367432D;
				entity.motionY *= 0.9800000190734863D;
				entity.motionZ *= 0.9900000095367432D;
				entity.moveEntity(entity.motionX, entity.motionY, entity.motionZ);
				
				if(entity.isCollidedHorizontally && !entity.worldObj.isRemote){
					double d10 = Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
					double d3 = d8 - d10;
					float f5 = (float) (d3 * 10.0D - 3.0D);

					if(f5 > 0.0F){
						entity.playSound((int) f5 > 4 ? "gamentity.player.hurt.fall.big" : "gamentity.player.hurt.fall.small", 1.0F, 1.0F);
						entity.attackEntityFrom(flyIntoWall, f5);
					}
				}
				if(entity.onGround && !entity.worldObj.isRemote){
					MethodImitations.setElytraFlying(entity, false);
				}
				return true;
			}
		}
		return false;
	}

}
