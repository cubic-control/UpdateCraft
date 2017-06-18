package com.cubic_control.UpdateCraft.Render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;

import com.cubic_control.UpdateCraft.Entities.EntityLingeringPotion;
import com.cubic_control.UpdateCraft.Items.MItems;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class RenderLingeringPotion extends RenderSnowball{

	public RenderLingeringPotion() {
		super(MItems.lingering_potion);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		if(!(entity instanceof EntityLingeringPotion)){
			return;
		}
		ItemStack stack = ((EntityLingeringPotion) entity).getStack();
		
		if(stack == null || stack.getItem() == null){
			return;
		}
		int passes;
		
		if(stack.getItem().requiresMultipleRenderPasses()){
			passes = stack.getItem().getRenderPasses(0);
		}else{
			passes = 1;
		}
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		bindEntityTexture(entity);
		
		for(int pass = 0; pass < passes; pass++){
			IIcon icon = stack.getItem().getIcon(stack, pass);
			
			if(icon != null){
				GL11.glPushMatrix();
				colour(stack.getItem().getColorFromItemStack(stack, pass));
				renderIcon(icon);
				GL11.glPopMatrix();
			}
		}
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	private void renderIcon(IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		float minU = icon.getMinU();
		float maxU = icon.getMaxU();
		float minV = icon.getMinV();
		float maxV = icon.getMaxV();
		GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(-0.5F, -0.25F, 0.0D, minU, maxV);
		tessellator.addVertexWithUV(0.5F, -0.25F, 0.0D, maxU, maxV);
		tessellator.addVertexWithUV(0.5F, 0.75F, 0.0D, maxU, minV);
		tessellator.addVertexWithUV(-0.5F, 0.75F, 0.0D, minU, minV);
		tessellator.draw();
	}
	
	public static void colour(int colour) {
		float r = (colour >> 16 & 255) / 255F;
		float g = (colour >> 8 & 255) / 255F;
		float b = (colour & 255) / 255F;

		GL11.glColor3f(r, g, b);
	}

}
