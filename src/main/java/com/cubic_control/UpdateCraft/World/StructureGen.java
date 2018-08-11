package com.cubic_control.UpdateCraft.World;

import java.util.Random;

import com.cubic_control.UpdateCraft.Utils.ModUtils;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
			generateNether(random, chunkX * 16, chunkZ * 16, world);
			break;
		case 0:
			generateOverworld(random, chunkX * 16, chunkZ * 16, world);
			break;
		case 1:
			generateEnd(random, chunkX * 16, chunkZ * 16, world);
			break;
		}
	}
	
	private void addStructureSurface(WorldGenerator gen, Random random, int chunkX, int chunkZ, World world, int spawnChance) {
		for(int i = 0; i < spawnChance; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = world.getHeightValue(x, z);
			
			if(random.nextBoolean() == true) {
				gen.generate(world, random, x, y, z);
			}
			//DEBUG
			//System.out.println("["+RefStrings.NAME+"]:"+this.getClass().getCanonicalName()+":addStructure:"+gen.toString());
		}
	}
	
	private void addStructureAtHeight(WorldGenerator gen, Random random, int chunkX, int chunkZ, World world, int spawnChance, int minY, int maxY) {
		for(int i = 0; i < spawnChance; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = ModUtils.getRandomNumberInRange(minY, maxY);
			
			if(random.nextBoolean() == true) {
				gen.generate(world, random, x, y, z);
			}
			//DEBUG
			//System.out.println("["+RefStrings.NAME+"]:"+this.getClass().getCanonicalName()+":addStructure:"+gen.toString());
		}
	}
	
	private void generateNether(Random random, int chunkX, int chunkZ, World world) {}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		addStructureAtHeight(new StructureFossilSkull01(), random, chunkX, chunkZ, world, (int) 1.5625, 40, 49);
	}
	
	private void generateEnd(Random random, int chunkX, int chunkZ, World world) {}

}
