package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.UpdateCraft.Render.RenderIDs;
import com.cubic_control.UpdateCraft.TileEntities.ModTileEntityEndRod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockEndRod extends BlockContainer{
	
	public ModBlockEndRod(String name) {
		super(Material.rock);
		this.setHardness(0);
		this.setLightLevel(0.9375F);
		this.setBlockTextureName(RefStrings.MODID + ":" + name);
		this.setBlockName(name);
		this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		GameRegistry.registerBlock(this, name);
	}
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		ForgeDirection dir = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
		
		if(dir == ForgeDirection.DOWN || dir == ForgeDirection.UP){
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		}else if(dir == ForgeDirection.WEST || dir == ForgeDirection.EAST){
			setBlockBounds(0.0F, 0.375F, 0.375F, 1.0F, 0.625F, 0.625F);
		}else if(dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH){
			setBlockBounds(0.375F, 0.375F, 0.0F, 0.625F, 0.625F, 1.0F);
		}
	}
	@Override
	public int getRenderType() {
		return RenderIDs.END_ROD;
	}
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
		if(world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != this){
			dir = dir.getOpposite();
		}
		return dir.ordinal();
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new ModTileEntityEndRod();
	}
	
}
