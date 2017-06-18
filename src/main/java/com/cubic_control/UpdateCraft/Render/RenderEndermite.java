package com.cubic_control.UpdateCraft.Render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import com.cubic_control.UpdateCraft.Entities.EntityEndermite;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.UpdateCraft.Models.ModelEnderMite;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEndermite extends RenderLiving
{
    private static final ResourceLocation field_177108_a = new ResourceLocation(RefStrings.MODID + ":textures/entity/endermite.png");
    private static final String __OBFID = "CL_00002445";

    public RenderEndermite()
    {
        super(new ModelEnderMite(), 0.3F);
    }

    protected float func_177107_a(EntityEndermite p_177107_1_)
    {
        return 180.0F;
    }

    protected ResourceLocation func_177106_b(EntityEndermite p_177106_1_)
    {
        return field_177108_a;
    }

    protected float getDeathMaxRotation(EntityLivingBase p_77037_1_)
    {
        return this.func_177107_a((EntityEndermite)p_77037_1_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.func_177106_b((EntityEndermite)entity);
    }
}