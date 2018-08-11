package com.cubic_control.UpdateCraft.TileEntities;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class MTileEntity {
	public static void createTileEntity(){
		reg(ModTileEntityEndRod.class, "END_ROD");
	}
	
	public static void reg(Class<? extends TileEntity> tile, String id){
		GameRegistry.registerTileEntity(tile, RefStrings.MODID+":"+id);
	}

}
