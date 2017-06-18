package com.cubic_control.UpdateCraft.Blocks;

import java.util.Random;

import com.cubic_control.UpdateCraft.Items.MItems;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockChorusPlant extends ModBlock {
	
	public ModBlockChorusPlant(String par1) {
		super(Material.plants, par1, 0.4f, "axe", -1, 2f, Block.soundTypeGrass);
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (neighbour == this){
			world.func_147480_a(x, y, z, true);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return MItems.chorus_fruit;
	}
}
