package com.cubic_control.UpdateCraft.Items;

import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class ModItemChorusFruit extends ItemFood{

	public ModItemChorusFruit(String par1) {
		super(4, 2.4f, false);
		this.setAlwaysEdible();
		this.setCreativeTab(MCreativeTabs.tabItems);
		this.setMaxStackSize(64);
		this.setTextureName(RefStrings.MODID + ":" + par1);
		this.setUnlocalizedName(par1);
		GameRegistry.registerItem(this, par1);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		ItemStack result = super.onEaten(stack, world, player);
		if (!world.isRemote)
        {
            double d0 = player.posX;
            double d1 = player.posY;
            double d2 = player.posZ;

            for (int i = 0; i < 16; ++i)
            {
                double d3 = player.posX + (player.getRNG().nextDouble() - 0.5D) * 16.0D;
                double d4 = MathHelper.clamp_double(player.posY + (double)(player.getRNG().nextInt(16) - 8), 0.0D, (double)(world.getActualHeight() - 1));
                double d5 = player.posZ + (player.getRNG().nextDouble() - 0.5D) * 16.0D;

                if (this.teleportTo_(player, d3, d4, d5))
                {
                    world.playSound(d0, d1, d2, "item.chorus_fruit.teleport", 1.0F, 1.0F, true);
                    player.playSound("item.chorus_fruit.teleport", 1.0F, 1.0F);
                    break;
                }
            }
        }
		return result;
	}

	/**
     * Teleports the entity to the specified location. Used for Enderman and Chorus Fruit teleportation
     */
	
	//Mix between Enderman of 1.7 and EntityLivingBase of 1.9
    public boolean teleportTo_(EntityPlayer player, double x, double y, double z)
    {
        double d0 = player.posX;
        double d1 = player.posY;
        double d2 = player.posZ;
        player.posX = x;
        player.posY = y;
        player.posZ = z;
        boolean flag = false;
        World world = player.worldObj;
        Random random = player.getRNG();
        int i = MathHelper.floor_double(player.posX);
        int j = MathHelper.floor_double(player.posY);
        int k = MathHelper.floor_double(player.posZ);

        if (player.worldObj.blockExists(i, j, k))
        {
            boolean flag1 = false;

            while (!flag1 && j > 0)
            {
            	Block block = player.worldObj.getBlock(i, j - 1, k);

                if (block.getMaterial().blocksMovement())
                {
                    flag1 = true;
                }
                else
                {
                    --player.posY;
                    --j;
                }
            }

            if (flag1)
            {
            	player.setPositionAndUpdate(player.posX, player.posY, player.posZ);

            	if (player.worldObj.getCollidingBoundingBoxes(player, player.boundingBox).isEmpty() && !player.worldObj.isAnyLiquid(player.boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
        	player.setPositionAndUpdate(d0, d1, d2);
            return false;
        }
        else
        {
        	short short1 = 128;

            for (int l = 0; l < short1; ++l)
            {
                double d6 = (double)j / ((double)i - 1.0D);
                float f = (random.nextFloat() - 0.5F) * 0.2F;
                float f1 = (random.nextFloat() - 0.5F) * 0.2F;
                float f2 = (random.nextFloat() - 0.5F) * 0.2F;
                double d3 = d0 + (player.posX - d0) * d6 + (random.nextDouble() - 0.5D) * (double)player.width * 2.0D;
                double d4 = d1 + (player.posY - d1) * d6 + random.nextDouble() * (double)player.height;
                double d5 = d2 + (player.posZ - d2) * d6 + (random.nextDouble() - 0.5D) * (double)player.width * 2.0D;
                world.spawnParticle("portal", d3, d4, d5, (double)f, (double)f1, (double)f2);
            }

            return true;
        }
    }
}
