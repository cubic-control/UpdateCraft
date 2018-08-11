package com.cubic_control.UpdateCraft.Network;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class ModPacketHandler {
	public static SimpleNetworkWrapper snw;
	
	public static void initPackets() {
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(RefStrings.MODID);
		registerMessage(ArmorStandInteractHandler.class, ArmorStandInteractMessage.class);
	}
	
	private static int nextPacketId = 0;
	
	private static void registerMessage(Class packet, Class message) {
		snw.registerMessage(packet, message, nextPacketId, Side.CLIENT);
		snw.registerMessage(packet, message, nextPacketId, Side.SERVER);
		nextPacketId++;
	}
	
	private static void registerMessage(Class packet, Class message, Side side) {
		snw.registerMessage(packet, message, nextPacketId, side);
		nextPacketId++;
	}

}
