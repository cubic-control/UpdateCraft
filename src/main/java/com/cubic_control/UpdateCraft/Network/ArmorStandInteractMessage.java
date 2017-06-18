package com.cubic_control.UpdateCraft.Network;

import com.cubic_control.UpdateCraft.Entities.EntityArmorStand;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ArmorStandInteractMessage implements IMessage {
	public int dimID;
	public int standID;
	public int playerID;
	public Vec3 hitPos;
	
	public ArmorStandInteractMessage() {}
	
	public ArmorStandInteractMessage(int dimID, EntityArmorStand entity, EntityPlayer player) {
		this.dimID = dimID;
		this.standID = entity.getEntityId();
		this.playerID = player.getEntityId();
		MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
		this.hitPos = Vec3.createVectorHelper(mop.hitVec.xCoord - entity.posX, mop.hitVec.yCoord - entity.posY, mop.hitVec.zCoord - entity.posZ);
	}
	@Override
	public void fromBytes(ByteBuf buf) {
		this.dimID = buf.readInt();
		this.standID = buf.readInt();
		this.playerID = buf.readInt();
		this.hitPos = Vec3.createVectorHelper(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dimID);
		buf.writeInt(standID);
		buf.writeInt(playerID);
		buf.writeDouble(hitPos.xCoord);
		buf.writeDouble(hitPos.yCoord);
		buf.writeDouble(hitPos.zCoord);
	}
	
}