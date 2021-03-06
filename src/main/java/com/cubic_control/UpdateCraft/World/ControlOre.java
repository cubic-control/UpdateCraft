package com.cubic_control.UpdateCraft.World;

import java.util.Random;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class ControlOre implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
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
	
	private void addOre(Block block, Block blockspawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minVien, int maxVien, int spawnChance){
		for(int i = 0; i < spawnChance; i++){
			int defaultChunkSize = 16;
			
			int xPos = posX + random.nextInt(defaultChunkSize);
			int yPos = minY + random.nextInt(maxY - minY);
			int zPos = posZ + random.nextInt(defaultChunkSize);
			
			new WorldGenMinable(block, (minVien + random.nextInt(maxVien - minVien)), blockspawn).generate(world, random, xPos, yPos, zPos);
		}
		}
	//addOre(MinHeight,MaxHeight,MinVien,MaxVien,SpawnChance)
	private void generateNether(Random random, int chunkX, int chunkZ, World world) {
		addOre(MBlocks.magma, Blocks.netherrack, random, world, chunkX, chunkZ, 23, 37, 1, 4, 30);
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		//Code Taken From OVERLOAD
		addOre(MBlocks.andesite, Blocks.stone, random, world, chunkX, chunkZ, 0, 256, 5, 15, 30);
		addOre(MBlocks.diorite, Blocks.stone, random, world, chunkX, chunkZ, 0, 256, 5, 15, 30);
		addOre(MBlocks.granite, Blocks.stone, random, world, chunkX, chunkZ, 0, 256, 5, 15, 30);
	}
	
	private void generateEnd(Random random, int chunkX, int chunkZ, World world) {}
}
