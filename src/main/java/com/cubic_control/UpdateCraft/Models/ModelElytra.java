package com.cubic_control.UpdateCraft.Models;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.cubic_control.UpdateCraft.Utils.FieldImitations;
import com.cubic_control.UpdateCraft.Utils.MethodImitations;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelElytra extends ModelBiped{
	private ModelRenderer rightWing;
	private ModelRenderer leftWing = new ModelRenderer(this, 22, 0);

	public ModelElytra() {
		this.leftWing.addBox(-10.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
		this.rightWing = new ModelRenderer(this, 22, 0);
		this.rightWing.mirror = true;
		this.rightWing.addBox(0.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
	}
	@Override
	public void render(Entity entityIn, float p_78088_2_, float limbSwing, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_CULL_FACE);
		this.leftWing.render(scale);
		this.rightWing.render(scale);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		float f = 0.2617994F;
		float f1 = -0.2617994F;
		float f2 = 0.0F;
		float f3 = 0.0F;
		
		if(entity instanceof EntityLivingBase && MethodImitations.isElytraFlying((EntityLivingBase)entity)){
			float f4 = 1.0F;
			
			if(entity.motionY < 0.0D){
				Vec3 vec3d = (Vec3.createVectorHelper(entity.motionX, entity.motionY, entity.motionZ)).normalize();
				f4 = 1.0F - (float) Math.pow(-vec3d.yCoord, 1.5D);
			}
			f = f4 * 0.34906584F + (1.0F - f4) * f;
			f1 = f4 * -((float) Math.PI / 2F) + (1.0F - f4) * f1;
		}else if(entity.isSneaking()){
			f = ((float) Math.PI * 2F / 9F);
			f1 = -((float) Math.PI / 4F);
			f2 = 3.0F;
			f3 = 0.08726646F;
		}
		this.leftWing.rotationPointX = 5.0F;
		this.leftWing.rotationPointY = f2;
		
		if(entity instanceof AbstractClientPlayer){
			AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer) entity;
			FieldImitations.set(abstractclientplayer, "rotateElytraX", (float) ((double) FieldImitations.get(abstractclientplayer, "rotateElytraX", 0f) + (f - FieldImitations.get(abstractclientplayer, "rotateElytraX", 0f)) * 0.1D));
			FieldImitations.set(abstractclientplayer, "rotateElytraY", (float) ((double) FieldImitations.get(abstractclientplayer, "rotateElytraY", 0f) + (f3 - FieldImitations.get(abstractclientplayer, "rotateElytraY", 0f)) * 0.1D));
			FieldImitations.set(abstractclientplayer, "rotateElytraZ", (float) ((double) FieldImitations.get(abstractclientplayer, "rotateElytraZ", 0f) + (f1 - FieldImitations.get(abstractclientplayer, "rotateElytraZ", 0f)) * 0.1D));
			this.leftWing.rotateAngleX = FieldImitations.get(abstractclientplayer, "rotateElytraX", 0f);
			this.leftWing.rotateAngleY = FieldImitations.get(abstractclientplayer, "rotateElytraY", 0f);
			this.leftWing.rotateAngleZ = FieldImitations.get(abstractclientplayer, "rotateElytraZ", 0f);
		}else{
			this.leftWing.rotateAngleX = f;
			this.leftWing.rotateAngleZ = f1;
			this.leftWing.rotateAngleY = f3;
		}
		this.rightWing.rotationPointX = -this.leftWing.rotationPointX;
		this.rightWing.rotateAngleY = -this.leftWing.rotateAngleY;
		this.rightWing.rotationPointY = this.leftWing.rotationPointY;
		this.rightWing.rotateAngleX = this.leftWing.rotateAngleX;
		this.rightWing.rotateAngleZ = -this.leftWing.rotateAngleZ;
	}
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float p_78086_2_, float p_78086_3_, float partialTickTime) {
		super.setLivingAnimations(entity, p_78086_2_, p_78086_3_, partialTickTime);
	}
	
}