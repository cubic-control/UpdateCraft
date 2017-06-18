package com.cubic_control.UpdateCraft.World;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class MVillage {
	public static void init(){
		addVillagePiece(VillageComponentField.class, RefStrings.MODID+":MViF");
		addVillageCreationHandler(new VillageHandlerField());
		addVillagePiece(VillageComponentLargeField.class, RefStrings.MODID+":MViLF");
		addVillageCreationHandler(new VillageHandlerLargeField());
	}
	
	private static void addVillagePiece(Class c, String s)  {
		try{
			MapGenStructureIO.func_143031_a(c, s);
		}catch(Exception localException){}
	}
	
	private static void addVillageCreationHandler(VillagerRegistry.IVillageCreationHandler v) {
		VillagerRegistry.instance().registerVillageCreationHandler(v);
	}
	
}
