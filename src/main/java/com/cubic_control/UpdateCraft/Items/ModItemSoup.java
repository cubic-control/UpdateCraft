package com.cubic_control.UpdateCraft.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItemSoup extends ModItemFood {

	public ModItemSoup(int amount, float saturation, boolean isWolfFood, String par1) {
		super(amount, saturation, isWolfFood, par1);
		this.setMaxStackSize(1);
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Items.bowl);
    }

}
