package com.cubic_control.UpdateCraft.Blocks;

import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;

public class MOreDictionary extends OreDictionary{
	public static void Main(){
		initVanillaEntries();
		initModEntries();
	}
	public static void initModEntries(){
		registerOre("blockSponge",		 	MBlocks.sponge);
		registerOre("blockPiston",		 	MBlocks.piston);
		registerOre("blockStickyPiston", 	MBlocks.stickyPiston);
		//
		registerOre("blockDiorite",			MBlocks.diorite);
		registerOre("blockDioritePolished", MBlocks.smooth_diorite);
		registerOre("blockAndesite",		MBlocks.andesite);
		registerOre("blockAndesitePolished",MBlocks.smooth_andesite);
		registerOre("blockGranite",		 	MBlocks.granite);
		registerOre("blockGranitePolished",	MBlocks.smooth_granite);
	}
	public static void initVanillaEntries(){
		registerOre("blockSponge",		 	Blocks.sponge);
		registerOre("blockPiston",		 	Blocks.piston);
		registerOre("blockStickyPiston", 	Blocks.sticky_piston);
	}
}
