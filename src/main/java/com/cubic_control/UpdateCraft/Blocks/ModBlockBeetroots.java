package com.cubic_control.UpdateCraft.Blocks;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ModBlockBeetroots extends BlockCrops{
	@SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public ModBlockBeetroots(String name){
		this.setBlockName(name);
		this.setBlockTextureName(RefStrings.MODID + ":" + name);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setStepSound(Block.soundTypeGrass);
		this.setLightOpacity(1);
		GameRegistry.registerBlock(this, name);
	}
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int i1, int i2) {
        if(i2 < 7){
            if(i2 == 6){
                i2 = 5;
            }
            return this.icons[i2 >> 1];
        }else{
            return this.icons[3];
        }
    }
	@Override
	protected Item func_149866_i() {
        return MItems.beetroot_seeds;
    }
	@Override
	protected Item func_149865_P() {
        return MItems.beetroot;
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[4];

        for(int i = 0; i < this.icons.length; ++i){
            this.icons[i] = reg.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }

}
