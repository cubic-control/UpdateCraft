package com.cubic_control.UpdateCraft.Entities;

import com.cubic_control.UpdateCraft.Main.MainRegistry;

import cpw.mods.fml.common.registry.EntityRegistry;

public class MEntities {
	public static void mainRegistry(){
		createEntities();
	}

	private static void createEntities() {
		EntityRegistry.registerModEntity(EntityEndermite.class, "endermite", 1, MainRegistry.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityLingeringPotion.class, "lingering_potion", 2, MainRegistry.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityLingeringEffect.class, "lingering_effect", 3, MainRegistry.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityRabbit.class, "rabbit", 4, MainRegistry.instance, 64, 1, true);
		//Non-Mob Entities
		EntityRegistry.registerModEntity(EntityArmorStand.class, "armor_stand", 30, MainRegistry.instance, 64, 1, true);
	}
}
