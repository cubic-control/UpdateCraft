package com.cubic_control.UpdateCraft.Main;

import com.cubic_control.UpdateCraft.Achievements.MAchievements;
import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.Blocks.MOreDictionary;
import com.cubic_control.UpdateCraft.Config.MConfig;
import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Enchantments.MEnchantments;
import com.cubic_control.UpdateCraft.Entities.EntityVillagerTradeList;
import com.cubic_control.UpdateCraft.Entities.MEntities;
import com.cubic_control.UpdateCraft.Events.MEventHandler;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.UpdateCraft.Network.ModPacketHandler;
import com.cubic_control.UpdateCraft.TileEntities.MTileEntity;
import com.cubic_control.UpdateCraft.World.MVillage;
import com.cubic_control.UpdateCraft.World.MWorld;
import com.cubic_control.cubic_core.Interfaces.IBaseMod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION,
	dependencies = "required-after:cubic_core")
// After setting up the mod with IBaseMod, make sure to delete the implementation!
public class MainRegistry /*implements IBaseMod*/{
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	public static ServerProxy proxy;
	
	@Mod.Instance(RefStrings.MODID)
	public static MainRegistry instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
		boolean flag = Loader.isModLoaded("cubic_core");
		if(flag){
			System.out.println("["+RefStrings.NAME+"]: CubicCore Loaded! Let The Fun Begin!");
		}else{
			System.out.println("["+RefStrings.NAME+"]: CubicCore Not Loaded! We Are About To Crash!");
		}
		MConfig.createConfig();
		MCreativeTabs.createTabs();
		MTileEntity.createTileEntity();
		MBlocks.Main();
		MItems.createItem();
		MWorld.MainRegistry();
		MEntities.mainRegistry();
		MVillage.init();
		MAchievements.createAchievements();
		MEnchantments.main();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
		MCraftingManager.createRecipes();
		ModPacketHandler.initPackets();
		MOreDictionary.Main();
		FMLCommonHandler.instance().bus().register(instance);
		MEventHandler.registerEvents();
		EntityVillagerTradeList.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
		MCraftingManager.changeRecipes();
	}
}
