package com.cubic_control.UpdateCraft.Blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.Random;

import com.cubic_control.UpdateCraft.Utils.ModEnumParticleTypes;
import com.cubic_control.cubic_core.Utils.BlockPos;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockMagma extends ModBlock{
	public static DamageSource HOT_FLOOR = (new DamageSource("hotFloor")).setFireDamage();
	
	protected ModBlockMagma(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1, par2, par3, par4, par5, par6, par7);
		this.setLightLevel(0.2F);
		this.setTickRandomly(true);
	}
	
	protected ModBlockMagma(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1, par2, par3, par6, par7);
		this.setLightLevel(0.2F);
		this.setTickRandomly(true);
	}
	
	protected ModBlockMagma(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1, par2, par3, par4, par5, par6, par7, par8);
		this.setLightLevel(0.2F);
		this.setTickRandomly(true);
	}
	//Parent Block
	protected ModBlockMagma(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1, par2, par3, par4, par5);
		this.setLightLevel(0.2F);
		this.setTickRandomly(true);
	}
	@Override
	public MapColor getMapColor(int p_149728_1_) {
		return MapColor.netherrackColor;
	}
	@Override
	public void onEntityWalking(World world, int xPos, int yPos, int zPos, Entity entity) {
		if(!entity.isImmuneToFire() && entity instanceof EntityLivingBase /*&& !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entity)*/) {
            entity.attackEntityFrom(HOT_FLOOR, 1.0F);
        }
        super.onEntityWalking(world, xPos, yPos, zPos, entity);
	}
	@Override
	public void updateTick(World world, int xPos, int yPos, int zPos, Random rand) {
		BlockPos pos = new BlockPos(xPos, yPos, zPos);
		BlockPos blockPos = pos.above();
		Block block = world.getBlock(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		
		if(block == Blocks.water || block == Blocks.flowing_water) {
			world.setBlockToAir(blockPos.getX(), blockPos.getY(), blockPos.getZ());
			world.playSoundEffect(xPos, yPos, zPos, "block.fire.extinguish", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
			
			if(world instanceof WorldServer) {
				((WorldServer)world).spawnParticle(ModEnumParticleTypes.SMOKE_LARGE.getParticleName(), (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.25D, (double)blockPos.getZ() + 0.5D, 0.5D, 0.25D, 0.5D);
			}
		}
	}

}
