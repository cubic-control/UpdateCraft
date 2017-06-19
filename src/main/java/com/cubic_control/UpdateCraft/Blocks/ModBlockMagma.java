package com.cubic_control.UpdateCraft.Blocks;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockMagma extends ModBlock{
	public static DamageSource Magma = (new DamageSource("Magma")).setFireDamage();
	
	protected ModBlockMagma(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1, par2, par3, par4, par5, par6, par7);
		this.setLightLevel(0.3F);
	}
	
	protected ModBlockMagma(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1, par2, par3, par6, par7);
		this.setLightLevel(0.3F);
	}
	
	protected ModBlockMagma(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1, par2, par3, par4, par5, par6, par7, par8);
		this.setLightLevel(0.3F);
	}
	//Parent Block
	protected ModBlockMagma(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1, par2, par3, par4, par5);
		this.setLightLevel(0.3F);
	}
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.attackEntityFrom(Magma, 1.0F);
    }
	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
		if(world.getBlock(x, y, z) instanceof ModBlockMagma){
			return true;
		}else{
			return super.isFireSource(world, x, y, z, side);
		}
    }
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
	@Override
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)(y + 1), (double)((float)(z + 1) - f));
    }

}
