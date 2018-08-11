package com.cubic_control.UpdateCraft.Blocks;

import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ModBlockDoor extends BlockDoor{

	protected ModBlockDoor(Material par1, String par2) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(null);
		this.setHardness(3f);
		this.setHarvestLevel("axe", 0);
		this.setResistance(15f);
		GameRegistry.registerBlock(this, par2);
	}
	@Override
	public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return this == MBlocks.acacia_door ? MItems.door_acacia : (
        		this == MBlocks.birch_door ? MItems.door_birch : (
        		this == MBlocks.dark_oak_door ? MItems.door_dark_oak : (
        		this == MBlocks.jungle_door ? MItems.door_jungle : (
        		this == MBlocks.spruce_door ? MItems.door_spruce : Item.getItemFromBlock(this)))));
    }
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
		return this == MBlocks.acacia_door ? MItems.door_acacia : (
        		this == MBlocks.birch_door ? MItems.door_birch : (
        		this == MBlocks.dark_oak_door ? MItems.door_dark_oak : (
        		this == MBlocks.jungle_door ? MItems.door_jungle : (
        		this == MBlocks.spruce_door ? MItems.door_spruce : Item.getItemFromBlock(this)))));
    }

}
