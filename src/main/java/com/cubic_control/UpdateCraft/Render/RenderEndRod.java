package com.cubic_control.UpdateCraft.Render;

import com.cubic_control.cubic_core.Utils.GlStateManager;
import com.cubic_control.cubic_core.Utils.OpenGLHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderEndRod extends TileEntitySpecialRenderer {
	private RenderBlocks renderer;

	public RenderEndRod() {
		renderer = new RenderBlocks();
	}
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		OpenGLHelper.colour(0xFFFFFF);
		bindTexture(TextureMap.locationBlocksTexture);
		
		ForgeDirection dir = ForgeDirection.getOrientation(tile.getBlockMetadata());
		switch(dir){
			case DOWN:
				GlStateManager.rotate(180, 1, 0, 0);
				GlStateManager.translate(0, -1, -1);
				break;
			case EAST:
				GlStateManager.rotate(270, 0, 0, 1);
				GlStateManager.translate(-1, 0, 0);
				break;
			case NORTH:
				GlStateManager.rotate(270, 1, 0, 0);
				GlStateManager.translate(0, -1, 0);
				break;
			case SOUTH:
				GlStateManager.rotate(90, 1, 0, 0);
				GlStateManager.translate(0, 0, -1);
				break;
			case WEST:
				GlStateManager.rotate(90, 0, 0, 1);
				GlStateManager.translate(0, -1, 0);
				break;
			default:
				break;
		}
		renderRod(renderer, tile.getBlockType(), tile.getBlockMetadata());
		GlStateManager.popMatrix();
	}

	public static void renderRod(RenderBlocks renderer, Block block, int meta) {
		Tessellator tessellator = Tessellator.instance;
		
		double x = 7 / 16.0;
		double y = 0;
		double z = 7 / 16.0;
		renderer.setRenderBounds(0, 1 / 16F, 0, 2 / 16F, 1, 2 / 16F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
		tessellator.draw();
		
		x = 4 / 16.0;
		y = 0;
		z = 4 / 16.0;
		renderer.setRenderBounds(2 / 16F, 0, 2 / 16F, 6 / 16F, 1 / 16F, 6 / 16F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
		y = -13 / 16.0;
		renderer.setRenderBounds(2 / 16F, 13 / 16F, 2 / 16F, 6 / 16F, 14 / 16F, 6 / 16F);
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, x, y, z, renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
		tessellator.draw();
	}
	
}