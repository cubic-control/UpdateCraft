package com.cubic_control.UpdateCraft.Utils;

import com.cubic_control.UpdateCraft.Entities.EntityTippedArrow;
import com.cubic_control.UpdateCraft.Items.ModItemTippedArrow;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenserBehaviourTippedArrow extends BehaviorDefaultDispenseItem {
	@Override
	public ItemStack dispenseStack(IBlockSource block, final ItemStack stack) {
		IPosition iposition = BlockDispenser.func_149939_a(block);
		return new BehaviorProjectileDispense() {
			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos) {
				EntityTippedArrow entity = new EntityTippedArrow(world, pos.getX(), pos.getY(), pos.getZ());
				entity.canBePickedUp = 1;
				entity.setEffect(ModItemTippedArrow.getEffect(stack));
				return entity;
			}
		}.dispense(block, stack);
	}
	@Override
	protected void playDispenseSound(IBlockSource block) {
		block.getWorld().playAuxSFX(1002, block.getXInt(), block.getYInt(), block.getZInt(), 0);
	}
	
}