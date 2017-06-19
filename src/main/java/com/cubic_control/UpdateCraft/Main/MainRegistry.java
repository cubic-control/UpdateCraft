package com.cubic_control.UpdateCraft.Main;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.opengl.GL11;

import com.cubic_control.UpdateCraft.Achievements.MAchievements;
import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Entities.MEntities;
import com.cubic_control.UpdateCraft.Events.MEventHandler;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.UpdateCraft.Network.ArmorStandInteractHandler;
import com.cubic_control.UpdateCraft.Network.ArmorStandInteractMessage;
import com.cubic_control.UpdateCraft.Network.StartFallFlying;
import com.cubic_control.UpdateCraft.World.MVillage;
import com.cubic_control.UpdateCraft.World.MWorld;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION)
public class MainRegistry {
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	public static ServerProxy proxy;
	
	@Mod.Instance(RefStrings.MODID)
	public static MainRegistry instance;
	
	public static SimpleNetworkWrapper snw;
	
	// Version checking instance
	public static VersionChecker versionChecker;
	public static boolean haveWarnedVersionOutOfDate = false;
	
	@EventHandler
	public static void Preload(FMLPreInitializationEvent PreEvent){
		boolean flag = Loader.isModLoaded("cubic_core");
		if(flag){
			System.out.println("["+RefStrings.NAME+"]: CubicCore Loaded! Let The Fun Begin!");
		}else{
			System.out.println("["+RefStrings.NAME+"]: CubicCore Not Loaded! We Are About To Crash!");
		}
		MCreativeTabs.createTabs();
		MBlocks.Main();
		MItems.createItem();
		MWorld.MainRegistry();
		MCraftingManager.createRecipes();
		MEntities.mainRegistry();
		MVillage.init();
		MAchievements.createAchievements();
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(RefStrings.MODID);
		snw.registerMessage(ArmorStandInteractHandler.class, ArmorStandInteractMessage.class, 0, Side.SERVER);
		snw.registerMessage(StartFallFlying.class, StartFallFlying.class, 0, Side.SERVER);
	}
	
	@EventHandler
	public static void load(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(instance);
		MEventHandler.registerEvents();
		proxy.registerRenderInfo();
	}
	
	@EventHandler
	public static void Postload(FMLPostInitializationEvent PostEvent){
		MainRegistry.versionChecker = new VersionChecker();
		Thread versionCheckThread = new Thread(MainRegistry.versionChecker, "Version Check");
		versionCheckThread.start();
	}
}
