package com.cubic_control.UpdateCraft.World;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageHandlerField implements IVillageCreationHandler {

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new StructureVillagePieces.PieceWeight(VillageComponentField.class, 3, MathHelper.getRandomIntegerInRange(random, 2 + i, 4 + i * 2));
	}
	@Override
	public Class<?> getComponentClass() {
		return VillageComponentField.class;
	}
	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
		return VillageComponentField.buildComponent(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}

}
