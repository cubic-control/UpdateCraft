package com.cubic_control.UpdateCraft.Main;

import com.cubic_control.UpdateCraft.Entities.EntityArmorStand;
import com.cubic_control.UpdateCraft.Entities.EntityEndermite;
import com.cubic_control.UpdateCraft.Entities.EntityLingeringEffect;
import com.cubic_control.UpdateCraft.Entities.EntityLingeringPotion;
import com.cubic_control.UpdateCraft.Entities.EntityRabbit;
import com.cubic_control.UpdateCraft.Render.RenderArmorStand;
import com.cubic_control.UpdateCraft.Render.RenderEndRod;
import com.cubic_control.UpdateCraft.Render.RenderEndermite;
import com.cubic_control.UpdateCraft.Render.RenderLingeringEffect;
import com.cubic_control.UpdateCraft.Render.RenderLingeringPotion;
import com.cubic_control.UpdateCraft.Render.RenderModBlockChorusFlower;
import com.cubic_control.UpdateCraft.Render.RenderModBlockChorusPlant;
import com.cubic_control.UpdateCraft.Render.RenderModBlockEndRod;
import com.cubic_control.UpdateCraft.Render.RenderRabbit;
import com.cubic_control.UpdateCraft.TileEntities.ModTileEntityEndRod;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy{
	// Version checking instance
	public static VersionChecker versionChecker;
	public static boolean haveWarnedVersionOutOfDate = false;

	private void entities() {
		RenderingRegistry.registerEntityRenderingHandler(EntityEndermite.class, new RenderEndermite());
		RenderingRegistry.registerEntityRenderingHandler(EntityLingeringPotion.class, new RenderLingeringPotion());
		RenderingRegistry.registerEntityRenderingHandler(EntityLingeringEffect.class, new RenderLingeringEffect());
		RenderingRegistry.registerEntityRenderingHandler(EntityRabbit.class, new RenderRabbit());
		//Non-Mob Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityArmorStand.class, new RenderArmorStand());
	}
	@Override
	public void preInit(FMLPreInitializationEvent arg0) {
		MKeys.createKeys();
	}
	@Override
	public void init(FMLInitializationEvent arg0) {
		entities();
		RenderingRegistry.registerBlockHandler(new RenderModBlockEndRod());
		RenderingRegistry.registerBlockHandler(new RenderModBlockChorusFlower());
		RenderingRegistry.registerBlockHandler(new RenderModBlockChorusPlant());
		ClientRegistry.bindTileEntitySpecialRenderer(ModTileEntityEndRod.class, new RenderEndRod());
	}
	@Override
	public void postInit(FMLPostInitializationEvent arg0) {
		versionChecker = new VersionChecker();
		Thread versionCheckThread = new Thread(versionChecker, "Version Check");
		versionCheckThread.start();
	}
	
}
