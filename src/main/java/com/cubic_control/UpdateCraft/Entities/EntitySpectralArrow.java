package com.cubic_control.UpdateCraft.Entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntitySpectralArrow extends EntityArrow{

	public EntitySpectralArrow(World par1World) {
		super(par1World);
	}
	
	public EntitySpectralArrow(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}
	
	public EntitySpectralArrow(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
		super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4, par5);
	}
	
	public EntitySpectralArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3) {
		super(par1World, par2EntityLivingBase, par3);
	}
}
