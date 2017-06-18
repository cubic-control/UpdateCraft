package com.cubic_control.UpdateCraft.Models;

import org.lwjgl.opengl.GL11;

import com.cubic_control.UpdateCraft.Entities.EntityRabbit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelRabbit extends ModelBase{
	protected ModelRenderer rabbitLeftFoot, rabbitRightFoot;
	protected ModelRenderer rabbitLeftThigh, rabbitRightThigh;
	protected ModelRenderer rabbitBody, rabbitTail;
	protected ModelRenderer rabbitLeftArm, rabbitRightArm;
	protected ModelRenderer rabbitHead, rabbitRightEar, rabbitLeftEar, rabbitNose;
	private float field_178701_m = 0.0F;
	
	public ModelRabbit() {
		setTextureOffset("head.main", 0, 0);
		setTextureOffset("head.nose", 0, 24);
		setTextureOffset("head.ear1", 0, 10);
		setTextureOffset("head.ear2", 6, 10);
		rabbitLeftFoot = new ModelRenderer(this, 26, 24);
		rabbitLeftFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
		rabbitLeftFoot.setRotationPoint(3.0F, 17.5F, 3.7F);
		rabbitLeftFoot.mirror = true;
		setRotationOffset(rabbitLeftFoot, 0.0F, 0.0F, 0.0F);
		rabbitRightFoot = new ModelRenderer(this, 8, 24);
		rabbitRightFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
		rabbitRightFoot.setRotationPoint(-3.0F, 17.5F, 3.7F);
		rabbitRightFoot.mirror = true;
		setRotationOffset(rabbitRightFoot, 0.0F, 0.0F, 0.0F);
		rabbitLeftThigh = new ModelRenderer(this, 30, 15);
		rabbitLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
		rabbitLeftThigh.setRotationPoint(3.0F, 17.5F, 3.7F);
		rabbitLeftThigh.mirror = true;
		setRotationOffset(rabbitLeftThigh, -0.34906584F, 0.0F, 0.0F);
		rabbitRightThigh = new ModelRenderer(this, 16, 15);
		rabbitRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
		rabbitRightThigh.setRotationPoint(-3.0F, 17.5F, 3.7F);
		rabbitRightThigh.mirror = true;
		setRotationOffset(rabbitRightThigh, -0.34906584F, 0.0F, 0.0F);
		rabbitBody = new ModelRenderer(this, 0, 0);
		rabbitBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
		rabbitBody.setRotationPoint(0.0F, 19.0F, 8.0F);
		rabbitBody.mirror = true;
		setRotationOffset(rabbitBody, -0.34906584F, 0.0F, 0.0F);
		rabbitLeftArm = new ModelRenderer(this, 8, 15);
		rabbitLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
		rabbitLeftArm.setRotationPoint(3.0F, 17.0F, -1.0F);
		rabbitLeftArm.mirror = true;
		setRotationOffset(rabbitLeftArm, -0.17453292F, 0.0F, 0.0F);
		rabbitRightArm = new ModelRenderer(this, 0, 15);
		rabbitRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
		rabbitRightArm.setRotationPoint(-3.0F, 17.0F, -1.0F);
		rabbitRightArm.mirror = true;
		setRotationOffset(rabbitRightArm, -0.17453292F, 0.0F, 0.0F);
		rabbitHead = new ModelRenderer(this, 32, 0);
		rabbitHead.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
		rabbitHead.setRotationPoint(0.0F, 16.0F, -1.0F);
		rabbitHead.mirror = true;
		setRotationOffset(rabbitHead, 0.0F, 0.0F, 0.0F);
		rabbitRightEar = new ModelRenderer(this, 52, 0);
		rabbitRightEar.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
		rabbitRightEar.setRotationPoint(0.0F, 16.0F, -1.0F);
		rabbitRightEar.mirror = true;
		setRotationOffset(rabbitRightEar, 0.0F, -0.2617994F, 0.0F);
		rabbitLeftEar = new ModelRenderer(this, 58, 0);
		rabbitLeftEar.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
		rabbitLeftEar.setRotationPoint(0.0F, 16.0F, -1.0F);
		rabbitLeftEar.mirror = true;
		setRotationOffset(rabbitLeftEar, 0.0F, 0.2617994F, 0.0F);
		rabbitTail = new ModelRenderer(this, 52, 6);
		rabbitTail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
		rabbitTail.setRotationPoint(0.0F, 20.0F, 7.0F);
		rabbitTail.mirror = true;
		setRotationOffset(rabbitTail, -0.3490659F, 0.0F, 0.0F);
		rabbitNose = new ModelRenderer(this, 32, 9);
		rabbitNose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
		rabbitNose.setRotationPoint(0.0F, 16.0F, -1.0F);
		rabbitNose.mirror = true;
		setRotationOffset(rabbitNose, 0.0F, 0.0F, 0.0F);
	}
	
	private void setRotationOffset(ModelRenderer renderer, float x, float y, float z) {
		renderer.rotateAngleX = x;
		renderer.rotateAngleY = y;
		renderer.rotateAngleZ = z;
	}
	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float scale) {
		setRotationAngles(f1, f2, f3, f4, f5, scale, entity);

		if(isChild){
			float f6 = 2.0F;
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 5.0F * scale, 2.0F * scale);
			rabbitHead.render(scale);
			rabbitLeftEar.render(scale);
			rabbitRightEar.render(scale);
			rabbitNose.render(scale);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GL11.glTranslatef(0.0F, 24.0F * scale, 0.0F);
			rabbitLeftFoot.render(scale);
			rabbitRightFoot.render(scale);
			rabbitLeftThigh.render(scale);
			rabbitRightThigh.render(scale);
			rabbitBody.render(scale);
			rabbitLeftArm.render(scale);
			rabbitRightArm.render(scale);
			rabbitTail.render(scale);
			GL11.glPopMatrix();
		}else{
			rabbitLeftFoot.render(scale);
			rabbitRightFoot.render(scale);
			rabbitLeftThigh.render(scale);
			rabbitRightThigh.render(scale);
			rabbitBody.render(scale);
			rabbitLeftArm.render(scale);
			rabbitRightArm.render(scale);
			rabbitHead.render(scale);
			rabbitRightEar.render(scale);
			rabbitLeftEar.render(scale);
			rabbitTail.render(scale);
			rabbitNose.render(scale);
		}
	}
	@Override
	public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float p_78087_6_, Entity entity) {
		float f6 = f3 - entity.ticksExisted;
		EntityRabbit entityrabbit = (EntityRabbit)entity;
		rabbitNose.rotateAngleX = rabbitHead.rotateAngleX = rabbitRightEar.rotateAngleX = rabbitLeftEar.rotateAngleX = f5 * 0.017453292F;
		rabbitNose.rotateAngleY = rabbitHead.rotateAngleY = f4 * 0.017453292F;
		rabbitRightEar.rotateAngleY = rabbitNose.rotateAngleY - 0.2617994F;
		rabbitLeftEar.rotateAngleY = rabbitNose.rotateAngleY + 0.2617994F;
		field_178701_m = MathHelper.sin(entityrabbit.func_175521_o(f6) * (float) Math.PI);
		rabbitLeftThigh.rotateAngleX = rabbitRightThigh.rotateAngleX = (field_178701_m * 50.0F - 21.0F) * 0.017453292F;
		rabbitLeftFoot.rotateAngleX = rabbitRightFoot.rotateAngleX = field_178701_m * 50.0F * 0.017453292F;
		rabbitLeftArm.rotateAngleX = rabbitRightArm.rotateAngleX = (field_178701_m * -40.0F - 11.0F) * 0.017453292F;
	}
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float f1, float f2, float f3) {}
	
}