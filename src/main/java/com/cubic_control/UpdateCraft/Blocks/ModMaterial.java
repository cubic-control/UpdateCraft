package com.cubic_control.UpdateCraft.Blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModMaterial extends Material{
	
	private final MapColor materialMapColor;
	
	private boolean requiresNoTool = true;
	
	private int mobilityFlag;
	
	public static final ModMaterial barrier = (new ModMaterial(MapColor.airColor)).setRequiresTool().setImmovableMobility();

	public ModMaterial(MapColor par1MapColor) {
		super(par1MapColor);
		this.materialMapColor = par1MapColor;
	}
	@Override
	public ModMaterial setRequiresTool()
    {
        this.requiresNoTool = false;
        return this;
    }
	@Override
	public ModMaterial setImmovableMobility()
    {
        this.mobilityFlag = 2;
        return this;
    }

}
