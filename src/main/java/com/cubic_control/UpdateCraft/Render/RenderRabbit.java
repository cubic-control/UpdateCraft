package com.cubic_control.UpdateCraft.Render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.cubic_control.UpdateCraft.Entities.EntityRabbit;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.UpdateCraft.Models.ModelRabbit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRabbit extends RenderLiving {
	private static final ResourceLocation BROWN = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/brown.png");
	private static final ResourceLocation WHITE = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/white.png");
	private static final ResourceLocation BLACK = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/black.png");
	private static final ResourceLocation GOLD = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/gold.png");
	private static final ResourceLocation SALT = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/salt.png");
	private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/white_splotched.png");
	private static final ResourceLocation TOAST = new ResourceLocation(RefStrings.MODID + ":textures/entity/rabbit/toast.png");
	
	public RenderRabbit() {
		super(new ModelRabbit(), 0.3F);
	}
	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float patialTickTime) {
		GL11.glScalef(0.65F, 0.65F, 0.65F);
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityRabbit rabbit = (EntityRabbit) entity;
		String s = EnumChatFormatting.getTextWithoutFormattingCodes(rabbit.getCommandSenderName());
		
		if(s != null && s.equals("Toast")){
			return TOAST;
		}else{
			switch(rabbit.getRabbitType()){
				case 0:
				default:
					return BROWN;
				case 1:
					return WHITE;
				case 2:
					return BLACK;
				case 3:
					return WHITE_SPLOTCHED;
				case 4:
					return GOLD;
				case 5:
					return SALT;
			}
		}
	}
	
}