package com.cubic_control.UpdateCraft.Network;

import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Items.ModItemElytra;
import com.cubic_control.UpdateCraft.Utils.MethodImitations;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;

public class StartFallFlying implements IMessage, IMessageHandler<StartFallFlying, IMessage>{
	@Override public void fromBytes(ByteBuf buf) {}
	@Override public void toBytes(ByteBuf buf) {}
	@Override
	public IMessage onMessage(StartFallFlying message, MessageContext context) {
		EntityPlayer player = ((NetHandlerPlayServer)context.netHandler).playerEntity;
		if(!player.onGround && player.motionY < 0.0D && !MethodImitations.isElytraFlying(player) && !player.isInWater()){
			ItemStack itemstack = player.getEquipmentInSlot(3);
			
			if(itemstack != null && itemstack.getItem() == MItems.elytra && ModItemElytra.isBroken(itemstack)){
				MethodImitations.setElytraFlying(player, true);
			}
		}else{
			MethodImitations.setElytraFlying(player, false);
		}
		return null;
	}
	
}