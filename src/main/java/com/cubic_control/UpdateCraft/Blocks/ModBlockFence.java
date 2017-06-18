package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ModBlockFence extends BlockFence {

	public ModBlockFence(String par1, Material par2, String par3) {
		super(par1, par2);
		this.setBlockName(par3);
		this.setBlockTextureName(":" + par1);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(2f);
		this.setHarvestLevel("axe", 0);
		this.setLightOpacity(0);
		this.setResistance(15f);
		this.setStepSound(soundTypeWood);
		GameRegistry.registerBlock(this, par3);
	}
	
	@Override
	public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return super.canConnectFenceTo(world, x, y, z) || block instanceof BlockFence || block instanceof BlockFenceGate;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_){
        return this.blockIcon;
    }

}
