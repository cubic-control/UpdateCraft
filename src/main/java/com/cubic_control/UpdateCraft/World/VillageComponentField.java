package com.cubic_control.UpdateCraft.World;

import java.util.List;
import java.util.Random;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillageComponentField extends StructureVillagePieces.Village{
    private Block cropTypeA;
    private Block cropTypeB;
    
    public VillageComponentField() {}
    
    public VillageComponentField(StructureVillagePieces.Start componentVillageStartPiece, int i1, Random rand, StructureBoundingBox structureBoundingBox, int i2) {
        super(componentVillageStartPiece, i1);
        this.coordBaseMode = i2;
        this.boundingBox = structureBoundingBox;
        this.cropTypeA = this.func_151560_a(rand);
        this.cropTypeB = this.func_151560_a(rand);
    }
    @Override
    protected void func_143012_a(NBTTagCompound compound) {
        super.func_143012_a(compound);
        compound.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
        compound.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
    }
    @Override
    protected void func_143011_b(NBTTagCompound compound) {
        super.func_143011_b(compound);
        this.cropTypeA = Block.getBlockById(compound.getInteger("CA"));
        this.cropTypeB = Block.getBlockById(compound.getInteger("CB"));
    }

    public Block func_151560_a(Random rand) {
        switch(rand.nextInt(5)){
            case 0:
                return Blocks.carrots;
            case 1:
                return Blocks.potatoes;
            case 2:
            	return Blocks.wheat;
            default:
                return MBlocks.beetroots;
        }
    }
    
    public static VillageComponentField buildComponent(StructureVillagePieces.Start componentVillageStartPiece, List list, Random rand, int i1, int i2, int i3, int i4, int i5) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(i1, i2, i3, 0, 0, 0, 7, 4, 9, i4);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new VillageComponentField(componentVillageStartPiece, i5, rand, structureboundingbox, i4) : null;
    }
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBox) {
        if(this.field_143015_k < 0){
            this.field_143015_k = this.getAverageGroundLevel(world, structureBoundingBox);

            if(this.field_143015_k < 0){
                return true;
            }
            this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
        }
        this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 6, 4, 8, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 2, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(world, structureBoundingBox, 4, 0, 1, 5, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 0, 8, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 6, 0, 0, 6, 0, 8, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 5, 0, 0, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 8, 5, 0, 8, Blocks.log, Blocks.log, false);
        this.fillWithBlocks(world, structureBoundingBox, 3, 0, 1, 3, 0, 7, Blocks.water, Blocks.water, false);
        int i;

        for(i = 1; i <= 7; ++i){
            this.placeBlockAtCurrentPosition(world, this.cropTypeA, MathHelper.getRandomIntegerInRange(rand, 2, 7), 1, 1, i, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, this.cropTypeA, MathHelper.getRandomIntegerInRange(rand, 2, 7), 2, 1, i, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, this.cropTypeB, MathHelper.getRandomIntegerInRange(rand, 2, 7), 4, 1, i, structureBoundingBox);
            this.placeBlockAtCurrentPosition(world, this.cropTypeB, MathHelper.getRandomIntegerInRange(rand, 2, 7), 5, 1, i, structureBoundingBox);
        }
        for(i = 0; i < 9; ++i){
            for(int j = 0; j < 7; ++j){
                this.clearCurrentPositionBlocksUpwards(world, j, 4, i, structureBoundingBox);
                this.func_151554_b(world, Blocks.dirt, 0, j, -1, i, structureBoundingBox);
            }
        }
        return true;
    }
}