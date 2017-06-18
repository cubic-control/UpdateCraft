package com.cubic_control.UpdateCraft.Blocks;

import net.minecraftforge.oredict.OreDictionary;

public class MOreDictionary extends OreDictionary{
	public static void initModEntries(){
		registerOre("sponge", MBlocks.sponge);
	}
}
