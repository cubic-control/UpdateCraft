package com.cubic_control.UpdateCraft.Items;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModItemElytra extends ModItem{
	
    public ModItemElytra(String name) {
    	super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(432);
        BlockDispenser.dispenseBehaviorRegistry.putObject(this, BlockDispenser.dispenseBehaviorRegistry.getObject(Items.iron_chestplate));
    }
    
	public static boolean isBroken(ItemStack stack) {
        return stack.getItemDamage() < stack.getMaxDamage() - 1;
    }
	@Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == Items.leather;
    }
    @Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn){
		int entityequipmentslot = 3;
		ItemStack itemstack = playerIn.getEquipmentInSlot(entityequipmentslot);
		
		if(itemstack == null){
			playerIn.setCurrentItemOrArmor(entityequipmentslot, itemStackIn.copy());
			itemStackIn.stackSize = 0;
			return itemStackIn;
		}else{
			return itemStackIn;
		}
	}
    @Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
		return armorType == 1;
	}
    
}