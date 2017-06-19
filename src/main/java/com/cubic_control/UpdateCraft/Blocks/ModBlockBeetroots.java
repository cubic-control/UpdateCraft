package com.cubic_control.UpdateCraft.Blocks;

import java.util.ArrayList;
import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ModBlockBeetroots extends BlockBush implements IGrowable{
	@SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public ModBlockBeetroots(String name) {
		float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.disableStats();
		this.setBlockName(name);
		this.setBlockTextureName(RefStrings.MODID + ":" + name);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(0.0f);
		this.setResistance(0.0f);
		this.setStepSound(Block.soundTypeGrass);
		this.setLightOpacity(1);
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);
	}
	@Override
	protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.farmland;
    }
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
        
        if(world.getBlockLightValue(x, y + 1, z) >= 9){
            int l = world.getBlockMetadata(x, y, z);
            
            if(l < 7){
                float f = this.func_149864_n(world, x, y, z);
                
                if(random.nextInt((int)(25.0F / f) + 1) == 0){
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                }
            }
        }
    }
	
    public void func_149863_m(World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
        
        if(l > 7){
            l = 7;
        }
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
    
    private float func_149864_n(World world, int x, int y, int z) {
        float f = 1.0F;
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        Block block4 = world.getBlock(x - 1, y, z - 1);
        Block block5 = world.getBlock(x + 1, y, z - 1);
        Block block6 = world.getBlock(x + 1, y, z + 1);
        Block block7 = world.getBlock(x - 1, y, z + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

        for(int l = x - 1; l <= x + 1; ++l){
            for(int i1 = z - 1; i1 <= z + 1; ++i1){
                float f1 = 0.0F;

                if(world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)){
                    f1 = 1.0F;

                    if(world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1)){
                        f1 = 3.0F;
                    }
                }
                if(l != x || i1 != z){
                    f1 /= 4.0F;
                }
                f += f1;
            }
        }

        if(flag2 || flag && flag1){
            f /= 2.0F;
        }
        return f;
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
	public int getRenderType() {
        return 6;
    }
	
	protected Item func_149866_i() {
        return MItems.beetroot_seeds;
    }
	
	protected Item func_149865_P() {
        return MItems.beetroot;
    }
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float p_149690_6_, int fortune) {
        super.dropBlockAsItemWithChance(world, x, y, z, meta, p_149690_6_, 0);
    }
	@Override
    public Item getItemDropped(int i1, Random random, int i2) {
        return i1 == 7 ? this.func_149865_P() : this.func_149866_i();
    }
	@Override
	public int quantityDropped(Random random) {
        return 1;
    }
	
    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_) {
        return world.getBlockMetadata(x, y, z) != 7;
    }
    
    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return true;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return this.func_149866_i();
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[4];

        for(int i = 0; i < this.icons.length; ++i){
            this.icons[i] = reg.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
	
	public void func_149853_b(World world, Random random, int x, int y, int z) {
        this.func_149863_m(world, x, y, z);
    }
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, meta, fortune);
        
        if(meta >= 7){
            for(int i = 0; i < 3 + fortune; ++i){
                if(world.rand.nextInt(15) <= meta){
                    ret.add(new ItemStack(this.func_149866_i(), 1, 0));
                }
            }
        }
        return ret;
    }
    
}
