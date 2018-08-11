package com.cubic_control.UpdateCraft.Render;

import com.cubic_control.cubic_core.Utils.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderModBlockEndRod implements ISimpleBlockRenderingHandler {
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		RenderEndRod.renderRod(renderer, block, meta);
	}
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
	@Override
	public int getRenderId() {
		return RenderIDs.END_ROD;
	}
	
}