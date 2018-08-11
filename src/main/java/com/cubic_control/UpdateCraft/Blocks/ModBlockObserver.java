package com.cubic_control.UpdateCraft.Blocks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.cubic_core.Utils.BlockPos;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockObserver extends BlockDirectional{
	//Need To Work On This /\ Represents ProperyBool
	private boolean isPowered;
	
	@SideOnly(Side.CLIENT)
    private IIcon icon_top;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front;
    @SideOnly(Side.CLIENT)
    private IIcon icon_back;
    
    String texName;
	
	protected ModBlockObserver(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		this(par1, par2, par2, par3, par4, par5, par6, par7);
	}
	
	protected ModBlockObserver(Material par1, String par2, float par3, float par6, SoundType par7) {
		this(par1, par2, par2, par3, "", 0, par6, par7);
	}
	
	protected ModBlockObserver(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1);
		this.setBlockName(par2);
		texName = (RefStrings.MODID + ":" + par3);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par4);
		this.setHarvestLevel(par5, par6);
		this.setResistance(par7);
		this.setStepSound(par8);
		GameRegistry.registerBlock(this, par2);
	}
	//Parent Block
	protected ModBlockObserver(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1.getMaterial());
		this.setBlockName(par2);
		texName = (RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par1.getHarvestTool(0), par1.getHarvestLevel(0));
		this.setResistance(par4);
		this.setStepSound(par5);
		GameRegistry.registerBlock(this, par2);
	}
	@Override
	public void updateTick(World world, int xPos, int yPos, int zPos, Random rand) {
		isPowered = true;
		world.scheduleBlockUpdate(xPos, yPos, zPos, this, 2);
		
		updateNeighborsInFront(world, new BlockPos(xPos, yPos, zPos));
	}
	@Override
	public boolean canProvidePower() {
		return true;
	}
	@Override
	public void onBlockAdded(World world, int xPos, int yPos, int zPos) {
		if(!world.isRemote) {
			if(isPowered) {
				updateTick(world, xPos, yPos, zPos, world.rand);
			}
			startSignal(world, new BlockPos(xPos, yPos, zPos));
		}
	}
	@Override
	public void breakBlock(World world, int xPos, int yPos, int zPos, Block block, int meta) {
		if(isPowered && world.isBlockTickScheduledThisTick(xPos, yPos, zPos, this)) {
			updateNeighborsInFront(world, new BlockPos(xPos, yPos, zPos));
		}
	}
	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		//TODO: Use to power block
		if(world instanceof World) {
			World theWorld = (World)world;
			
			if(!theWorld.isRemote) {
				int meta = world.getBlockMetadata(x, y, z);
				int direction = this.getDirection(meta);
				BlockPos pos = new BlockPos(x, y, z);
				BlockPos neighborPos = new BlockPos(tileX, tileY, tileZ);
				pos.compareTo(neighborPos);
				DataOutputStream stream = new DataOutputStream(System.out);
				try {
					pos.write(stream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public int isProvidingStrongPower(IBlockAccess world, int xPos, int yPos, int zPos, int p_149748_5_) {
        return this.isProvidingWeakPower(world, xPos, yPos, zPos, p_149748_5_);
    }/*
	@Override
    public int isProvidingWeakPower(IBlockAccess world, int xPos, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        int i1 = world.getBlockMetadata(xPos, p_149709_3_, p_149709_4_);
        int j1 = getDirection(i1);
        return j1 == 0 && p_149709_5_ == 3 ? this.func_149904_f(world, xPos, p_149709_3_, p_149709_4_, i1) : (j1 == 1 && p_149709_5_ == 4 ? this.func_149904_f(world, xPos, p_149709_3_, p_149709_4_, i1) : (j1 == 2 && p_149709_5_ == 2 ? this.func_149904_f(world, xPos, p_149709_3_, p_149709_4_, i1) : (j1 == 3 && p_149709_5_ == 5 ? this.func_149904_f(world, xPos, p_149709_3_, p_149709_4_, i1) : 0)));
    }*/
	
	private void startSignal(World world, BlockPos pos) {
		if(!isPowered && world.isBlockTickScheduledThisTick(pos.getX(), pos.getY(), pos.getZ(), this)) {
			world.scheduleBlockUpdate(pos.getX(), pos.getY(), pos.getZ(), this, 2);
		}
	}
	
	private void updateNeighborsInFront(World world, BlockPos pos) {
		int meta = world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ());
		int dir = this.getDirection(meta);
		ForgeDirection direct = ForgeDirection.getOrientation(dir);
		ForgeDirection opposite = direct.getOpposite();
		BlockPos front = pos.offset(opposite.offsetX, opposite.offsetY, opposite.offsetZ);
		world.notifyBlockChange(front.getX(), front.getY(), front.getZ(), this);
		world.notifyBlocksOfNeighborChange(front.getX(), front.getY(), front.getZ(), this, dir);
	}
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		if(side == 0) {//Bottom
			return this.icon_top;
		}else if(side == 1) {//Top
			return this.icon_top;
		}else if(side == 2) {//Back
			return this.icon_back;
		}else if(side == 3) {//Front
			return this.icon_front;
		}else {//Sides
			return this.blockIcon;
		}
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
		this.icon_back = reg.registerIcon(this.isPowered ? texName+"_back_lit" : texName+"_back");
        this.icon_front = reg.registerIcon(texName+"_front");
        this.icon_top = reg.registerIcon(texName+"_top");
        this.blockIcon = reg.registerIcon(texName+"_side");
        
        //Prevent System From Complaining
        this.setBlockTextureName(texName+"_front");
    }

}
