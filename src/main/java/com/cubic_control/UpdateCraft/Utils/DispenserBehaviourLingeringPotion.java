package com.cubic_control.UpdateCraft.Utils;

import com.cubic_control.UpdateCraft.Entities.EntityLingeringPotion;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DispenserBehaviourLingeringPotion implements IBehaviorDispenseItem {

	@Override
	public ItemStack dispense(IBlockSource block, final ItemStack stack) {
		return new BehaviorProjectileDispense() {

			@Override
			protected IProjectile getProjectileEntity(World world, IPosition pos) {
				return new EntityLingeringPotion(world, pos.getX(), pos.getY(), pos.getZ(), stack.copy());
			}

			@Override
			protected float func_82498_a() {
				return super.func_82498_a() * 0.5F;
			}

			@Override
			protected float func_82500_b() {
				return super.func_82500_b() * 1.25F;
			}
		}.dispense(block, stack);
	}
}