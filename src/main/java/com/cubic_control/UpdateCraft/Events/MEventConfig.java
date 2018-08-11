package com.cubic_control.UpdateCraft.Events;

import com.cubic_control.UpdateCraft.Config.MConfig;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MEventConfig {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(RefStrings.MODID)){
			MConfig.syncConfig();
		}
	}

}
