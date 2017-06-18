package com.cubic_control.UpdateCraft.Events;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class MEventHandler {
	
	public static void registerEvents(){
		FMLCommonHandler.instance().bus().register(new MEventUpdateChecker());
		MinecraftForge.EVENT_BUS.register(new MEventVanilaAIChange());
	}

}
