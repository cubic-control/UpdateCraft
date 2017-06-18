package com.cubic_control.UpdateCraft.Utils;

import java.util.Random;

import net.minecraftforge.common.util.ForgeDirection;

public class ModUtils {
	
	public static ForgeDirection getRandomDirection(Random rand) {
		return ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
	}

}
