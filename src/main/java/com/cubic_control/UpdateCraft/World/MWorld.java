package com.cubic_control.UpdateCraft.World;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class MWorld {
	
	public static void MainRegistry(){
		InitializeWorldGen();
	}
	
	public static void InitializeWorldGen(){
		RegisterWorldGen(new ControlOre(), 1);
		RegisterWorldGen(new StructureGen(), 2);
	}
	
	public static void RegisterWorldGen(IWorldGenerator worldGenClass, int weightedProbability){
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}

}
