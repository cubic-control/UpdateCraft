package com.cubic_control.UpdateCraft.Elytra;

import com.cubic_control.UpdateCraft.Utils.MethodImitations;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

//Class is unused
public class ElytraSound extends MovingSound{
	private final EntityPlayerSP field_189405_m;
	private int field_189406_n;

	public ElytraSound(EntityPlayerSP p_i47113_1_) {
		super(new ResourceLocation("backlytra", "item.elytra.flying"));
		this.field_189405_m = p_i47113_1_;
		this.repeat = true;
		//repeatDelay is obfuscated, this will break in 1.7.10
		this.field_147665_h = 0;
		this.volume = 0.1F;
	}
	@Override
	public void update() {
		++this.field_189406_n;
		
		if(!this.field_189405_m.isDead && (this.field_189406_n <= 20 || MethodImitations.isElytraFlying(this.field_189405_m))){
			this.xPosF = (float) this.field_189405_m.posX;
			this.yPosF = (float) this.field_189405_m.posY;
			this.zPosF = (float) this.field_189405_m.posZ;
			float f = MathHelper.sqrt_double(this.field_189405_m.motionX * this.field_189405_m.motionX + this.field_189405_m.motionZ * this.field_189405_m.motionZ + this.field_189405_m.motionY * this.field_189405_m.motionY);
			float f1 = f / 2.0F;
			
			if(f >= 0.01D){
				this.volume = MathHelper.clamp_float(f1 * f1, 0.0F, 1.0F);
			}else{
				this.volume = 0.0F;
			}
			if(this.field_189406_n < 20){
				this.volume = 0.0F;
			}else if(this.field_189406_n < 40){
				this.volume = (float) (this.volume * ((this.field_189406_n - 20) / 20.0D));
			}
			float f2 = 0.8F;

			if(this.volume > 0.8F) {
				//Pitch is obfuscated, this will break in 1.7.10
				this.field_147663_c = 1.0F + (this.volume - 0.8F);
			}else{
				//Pitch is obfuscated, this will break in 1.7.10
				this.field_147663_c = 1.0F;
			}
		}else{
			this.donePlaying = true;
		}
	}
	
}