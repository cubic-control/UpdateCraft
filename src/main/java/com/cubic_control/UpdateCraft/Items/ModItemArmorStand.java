package com.cubic_control.UpdateCraft.Items;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Entities.EntityArmorStand;
import com.cubic_control.UpdateCraft.Entities.Rotations;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModItemArmorStand extends ModItem{

	public ModItemArmorStand(String name) {
		super(name);
		this.setMaxStackSize(16);
	}
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(side == 0){
			return false;
		}else{
			if(side == 1){
				y++;
			}
			if(side == 2){
				z--;
			}
			if(side == 3){
				z++;
			}
			if(side == 4){
				x--;
			}
			if(side == 5){
				x++;
			}
			if(!player.canPlayerEdit(x, y, z, side, stack)){
				return false;
			}else{
				boolean flag1 = !world.isAirBlock(x, y, z) && !world.getBlock(x, y, z).isReplaceable(world, x, y, z);
				flag1 |= !world.isAirBlock(x, y + 1, z) && !world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z);

				if(flag1){
					return false;
				}else{
					double d0 = x;
					double d1 = y;
					double d2 = z;
					List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

					if(list.size() > 0){
						return false;
					}else{
						if(!world.isRemote){
							world.setBlockToAir(x, y, z);
							world.setBlockToAir(x, y + 1, z);
							EntityArmorStand entity = new EntityArmorStand(world, d0 + 0.5D, d1, d2 + 0.5D);
							float f3 = MathHelper.floor_float((MathHelper.wrapAngleTo180_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
							entity.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f3, 0.0F);
							applyRandomRotations(entity, world.rand);
							NBTTagCompound compound = stack.getTagCompound();
							
							if(compound != null && compound.hasKey("EntityTag", 10)){
								NBTTagCompound nbt1 = new NBTTagCompound();
								entity.writeToNBTOptional(nbt1);
								merge(nbt1, compound.getCompoundTag("EntityTag"));
								entity.readFromNBT(nbt1);
							}
							world.spawnEntityInWorld(entity);
						}
						stack.stackSize--;
						return true;
					}
				}
			}
		}
	}
	
	private void applyRandomRotations(EntityArmorStand entity, Random rand) {
		Rotations rotations = entity.getHeadRotation();
		float f = rand.nextFloat() * 5.0F;
		float f1 = rand.nextFloat() * 20.0F - 10.0F;
		Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
		entity.setHeadRotation(rotations1);
		rotations = entity.getBodyRotation();
		f = rand.nextFloat() * 10.0F - 5.0F;
		rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
		entity.setBodyRotation(rotations1);
	}
	
	public void merge(NBTTagCompound compound, NBTTagCompound compound2) {
		Iterator<String> iterator = compound2.func_150296_c().iterator();
		
		while(iterator.hasNext()){
			String s = iterator.next();
			NBTBase nbtbase = compound2.getTag(s);
			
			if(nbtbase.getId() == 10){
				if(compound.hasKey(s, 10)){
					NBTTagCompound nbttagcompound1 = compound.getCompoundTag(s);
					merge(nbttagcompound1, (NBTTagCompound) nbtbase);
				}else{
					compound.setTag(s, nbtbase.copy());
				}
			}else{
				compound.setTag(s, nbtbase.copy());
			}
		}
	}
	
}