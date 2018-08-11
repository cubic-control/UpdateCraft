package com.cubic_control.UpdateCraft.Config;

import java.io.File;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class MConfig extends Configuration{
	public static Configuration config;
	private static String PATH;
	
	public static int ID_frostWalker;
	public static int ID_mending;
	
	/**
	 * Creates Config file in custom folder in .minecraft folder.
	 */
	public static void createConfig(){
		PATH = "config/cubic_control/"+RefStrings.MODID+"/";
		File file = new File(PATH + "ConfigurationFile.cfg");
		config = new Configuration(file);
		config.load();
		syncConfig();
	}
	
	public static void syncConfig() {
		addBooleans();
		addInts();

	    if(config.hasChanged()){
	    	config.save();
	    }
	  }
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals(RefStrings.MODID)){
			syncConfig();
		}
	}
	
	/**
	 * Add Booleans to config file.
	 */
	public static void addBooleans(){
		config.getCategory(ConfigTypes.BOOLEANS);
	}
	
	public static void addInts(){
		config.getCategory(ConfigTypes.INTS);
		ID_frostWalker = config.getInt("ID_frostWalker", ConfigTypes.INTS, 36, 0, 256, "Sets the ID for the Frost Walker enchantment.");
		ID_mending = config.getInt("ID_mending", ConfigTypes.INTS, 37, 0, 256, "Sets the ID for the Mending enchantment.");
	}
	
	public static Configuration getConfig(){
		return config;
	}

}
