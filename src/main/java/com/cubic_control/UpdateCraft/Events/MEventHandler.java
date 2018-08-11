package com.cubic_control.UpdateCraft.Events;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class MEventHandler {
	
	public static void registerEvents(){
		reg(new MEventUpdateChecker());
		reg(new MEventVanilaAIChange());
		reg(new MEventTotem());
		reg(new MEventGrassPath());
		reg(new MEventEnchantments());
		reg(new MEventConfig());
	}
	
	private static void reg(Object target){
		FMLCommonHandler.instance().bus().register(target);
		MinecraftForge.EVENT_BUS.register(target);
	}

}
