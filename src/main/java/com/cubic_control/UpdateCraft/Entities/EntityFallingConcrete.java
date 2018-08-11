package com.cubic_control.UpdateCraft.Entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

/***
 * Code Inspired From Dbrown55's
 * "Concrete Mod"
 */

public class EntityFallingConcrete extends EntityFallingBlock{
	Material[] validMaterials = new Material[] {
			Material.water,
			Material.lava
	};
	
	private Block fallTile;
	private int meta;
	
	public EntityFallingConcrete(World world) {
		super(world);
	}
	
	public EntityFallingConcrete(World world, double xPos, double yPos, double zPos, Block block) {
		super(world, xPos, yPos, zPos, block);
		this.fallTile = block;
		this.meta = 0;
	}
	
	public EntityFallingConcrete(World world, double xPos, double yPos, double zPos, Block block, int meta) {
		super(world, xPos, yPos, zPos, block, meta);
		this.fallTile = block;
		this.meta = meta;
	}
	
	private boolean checkValidMaterial(Material material) {
		boolean valid = false;
		for(int i = 0; i < validMaterials.length; i++) {
			if(material == validMaterials[i]) {
				valid = true;
			}
		}
		return valid;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(checkValidMaterial(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ).getMaterial())
		|| checkValidMaterial(this.worldObj.getBlock((int)this.posX + 1, (int)this.posY, (int)this.posZ).getMaterial())
		|| checkValidMaterial(this.worldObj.getBlock((int)this.posX - 1, (int)this.posY, (int)this.posZ).getMaterial())
		|| checkValidMaterial(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ + 1).getMaterial())
		|| checkValidMaterial(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ - 1).getMaterial())) {
			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ-1, this.fallTile, this.meta, 3);
			this.setDead();
		}
	}

}
