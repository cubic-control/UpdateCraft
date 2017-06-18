package com.cubic_control.UpdateCraft.Items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModItemEndCrystal extends ModItem{

	protected ModItemEndCrystal(String par1) {
		super(par1);
		
	}
	
	/**
     * Called when a Block is right-clicked with this Item
     */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;

		Block block = world.getBlock(x, y, z);
		if (block == Blocks.obsidian || block == Blocks.bedrock)
			if (world.isAirBlock(x, y + 1, z) && world.isAirBlock(x, y + 2, z)) {
				if (!world.isRemote) {
					EntityEnderCrystal endCrystal = new EntityEnderCrystal(world);
					endCrystal.setPosition(x + 0.5, y + 2, z + 0.5);

					world.spawnEntityInWorld(endCrystal);
					
					if (world.provider instanceof WorldProviderEnd) {
                        //DragonFightManager dragonfightmanager = ((WorldProviderEnd)world.provider).getDragonFightManager();
                        //dragonfightmanager.respawnDragon();
                    }
					
					if (!player.capabilities.isCreativeMode){
						stack.stackSize--;
					}
				}
				return true;
			}

		return false;
	}

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

}
