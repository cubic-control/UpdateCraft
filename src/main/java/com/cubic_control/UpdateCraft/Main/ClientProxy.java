package com.cubic_control.UpdateCraft.Main;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import com.cubic_control.UpdateCraft.Entities.EntityArmorStand;
import com.cubic_control.UpdateCraft.Entities.EntityEndermite;
import com.cubic_control.UpdateCraft.Entities.EntityLingeringEffect;
import com.cubic_control.UpdateCraft.Entities.EntityLingeringPotion;
import com.cubic_control.UpdateCraft.Entities.EntityRabbit;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Items.ModItemElytra;
import com.cubic_control.UpdateCraft.Network.StartFallFlying;
import com.cubic_control.UpdateCraft.Render.RenderArmorStand;
import com.cubic_control.UpdateCraft.Render.RenderEndermite;
import com.cubic_control.UpdateCraft.Render.RenderLingeringEffect;
import com.cubic_control.UpdateCraft.Render.RenderLingeringPotion;
import com.cubic_control.UpdateCraft.Render.RenderRabbit;
import com.cubic_control.UpdateCraft.Utils.FieldImitations;
import com.cubic_control.UpdateCraft.Utils.MethodImitations;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy{
	
	public void registerRenderInfo() {
		entities();
	}

	private void entities() {
		RenderingRegistry.registerEntityRenderingHandler(EntityEndermite.class, new RenderEndermite());
		RenderingRegistry.registerEntityRenderingHandler(EntityLingeringPotion.class, new RenderLingeringPotion());
		RenderingRegistry.registerEntityRenderingHandler(EntityLingeringEffect.class, new RenderLingeringEffect());
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbit.class, new RenderRabbit());
		//Non-Mob Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityArmorStand.class, new RenderArmorStand());
	}
	
	@Override
	public void update(EntityLivingBase entity) {
		if(entity instanceof EntityPlayerSP){
			EntityPlayerSP player = (EntityPlayerSP)entity;
			boolean lastJumping = FieldImitations.get(player, "lastIsJumping", false);
			if(player.movementInput.jump && !lastJumping && !player.onGround && player.motionY < 0.0D && !MethodImitations.isElytraFlying(player) && !player.capabilities.isFlying){
				ItemStack stack = player.getEquipmentInSlot(3);
				
				if(stack != null && stack.getItem() == MItems.elytra && ModItemElytra.isBroken(stack)){
					MainRegistry.snw.sendToServer(new StartFallFlying());
					//Minecraft.getMinecraft().getSoundHandler().playSound(new ElytraSound(e));
				}
			}
			FieldImitations.set(player, "lastIsJumping", player.movementInput.jump);
		}
	}
	
}
