package com.cubic_control.UpdateCraft.Items;

import java.util.List;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Utils.ModEnumDyeColor;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModItemShield extends Item
{
    public ModItemShield(String par1)
    {
    	this.setUnlocalizedName(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(MCreativeTabs.tabItems);
        this.setMaxDamage(336);
        GameRegistry.registerItem(this, par1);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return super.onItemUse(stack, player, world, side, side, side, side, hitZ, hitZ, hitZ);
    }
	
	/**
     * Get an NBTTagCompound from this stack's NBT data.
     */
    public NBTTagCompound getSubCompound(String key, boolean create, ItemStack stack){
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(key, 10))
        {
            return stack.stackTagCompound.getCompoundTag(key);
        }
        else if (create)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            stack.setTagInfo(key, nbttagcompound);
            return nbttagcompound;
        }
        else
        {
            return null;
        }
    }
    
	@Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        if (this.getSubCompound("BlockEntityTag", false, stack) != null)
        {
            String s = "item.shield.";
            ModEnumDyeColor enumdyecolor = ModItemBanner.getBaseColor(stack);
            s = s + enumdyecolor.getUnlocalizedName() + ".name";
            return StatCollector.translateToLocal(s);
        }
        else
        {
            return StatCollector.translateToLocal("item.shield.name");
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        ModItemBanner.func_185054_a(stack, tooltip);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        ItemStack itemstack = new ItemStack(itemIn, 1, 0);
        subItems.add(itemstack);
    }

    /**
     * gets the CreativeTab this item is displayed on
     */
    @Override
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return MCreativeTabs.tabItems;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World worldIn, EntityPlayer playerIn){
		return par1ItemStack;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return repair.getItem() == Item.getItemFromBlock(Blocks.planks) ? true : super.getIsRepairable(toRepair, repair);
    }
}