package com.cubic_control.UpdateCraft.Network;

import com.cubic_control.UpdateCraft.Entities.EntityArmorStand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ArmorStandInteractHandler implements IMessageHandler<ArmorStandInteractMessage, IMessage> {
	@Override
	public IMessage onMessage(ArmorStandInteractMessage message, MessageContext context) {
		World world = DimensionManager.getWorld(message.dimID);
		EntityArmorStand stand = (EntityArmorStand)world.getEntityByID(message.standID);
		EntityPlayer player = (EntityPlayer) world.getEntityByID(message.playerID);
		stand.interact(player, message.hitPos);
		return null;
	}
}