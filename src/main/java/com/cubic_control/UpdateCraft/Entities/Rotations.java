package com.cubic_control.UpdateCraft.Entities;

import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class Rotations {
	protected final float x;
	protected final float y;
	protected final float z;

	public Rotations(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Rotations(NBTTagList nbt) {
		x = nbt.func_150308_e(0);
		y = nbt.func_150308_e(1);
		z = nbt.func_150308_e(2);
	}

	public NBTTagList writeToNBT() {
		NBTTagList nbt = new NBTTagList();
		nbt.appendTag(new NBTTagFloat(x));
		nbt.appendTag(new NBTTagFloat(y));
		nbt.appendTag(new NBTTagFloat(z));
		return nbt;
	}
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Rotations)){
			return false;
		}else{
			Rotations rotations = (Rotations)object;
			return x == rotations.x && y == rotations.y && z == rotations.z;
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
}