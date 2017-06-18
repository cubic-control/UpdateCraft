package com.cubic_control.UpdateCraft.Blocks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModBlockChorusFlower extends ModBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon deadIcon;

    protected ModBlockChorusFlower(String par1)
    {
        super(Material.plants, par1, 0.4f, "axe", -1, 2f, soundTypeWood);
        this.setTickRandomly(true);
    }
    
    @Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return !(entity instanceof EntityDragon);
	}

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    	return null;
    }
    
    @Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
    
    @Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta >= 5 ? deadIcon : blockIcon;
	}
    
    @Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		super.registerBlockIcons(reg);
		deadIcon = reg.registerIcon(RefStrings.MODID + ":chorus_flower_dead");
	}

    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	if (world.isRemote){
			return;
    	}
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= 5){
			return;
		}
		if (!canBlockStay(world, x, y, z)){
			world.func_147480_a(x, y, z, true);
		}else if (world.isAirBlock(x, y + 1, z)) {
			boolean canGrowUp = false;
			boolean isSegmentOnEndstone = false;
			Block lowerBlock = world.getBlock(x, y - 1, z);
			if (lowerBlock == Blocks.end_stone){
				canGrowUp = true;
			}else if (lowerBlock == MBlocks.chorus_plant) {
				int par8 = 1;
				int height;
				for (height = 0; height < 4; height++) {
					Block b = world.getBlock(x, y - (par8 + 1), z);
					if (b != MBlocks.chorus_plant) {
						if (b == Blocks.end_stone){
							isSegmentOnEndstone = true;
						}
						break;
					}
					par8++;
				}

				height = 4;
				if (isSegmentOnEndstone){
					height++;
				}
				if (par8 < 2 || rand.nextInt(height) >= par8){
					canGrowUp = true;
				}
			} else if (lowerBlock.isAir(world, x, y - 1, z)){
				canGrowUp = true;
			}
			if (canGrowUp && isSpaceAroundFree(world, x, y + 1, z, ForgeDirection.DOWN) && world.isAirBlock(x, y + 2, z)) {
				world.setBlock(x, y, z, MBlocks.chorus_plant);
				world.setBlock(x, y + 1, z, this, meta, 3);
			} else if (meta < 4) {
				int tries = rand.nextInt(4);
				boolean grew = false;
				if (isSegmentOnEndstone){
					tries++;
				}
				for (int i = 0; i < tries; i++) {
					ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
					int xx = x + dir.offsetX;
					int yy = y + dir.offsetY;
					int zz = z + dir.offsetZ;
					if (world.isAirBlock(xx, yy, zz) && isSpaceAroundFree(world, xx, yy, zz, dir.getOpposite())) {
						world.setBlock(xx, yy, zz, this, meta + 1, 3);
						grew = true;
					}
				}
				if (grew){
					world.setBlock(x, y, z, MBlocks.chorus_plant, 0, 3);
				}else{
					world.setBlock(x, y, z, this, 5, 3);
				}
			} else if (meta == 4){
				world.setBlock(x, y, z, this, 5, 3);
			}
		}
    }
    
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (!canBlockStay(world, x, y, z)){
			world.func_147480_a(x, y, z, false);
		}
	}
    
    @Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlantStay(world, x, y, z);
	}
    
    public static boolean canPlantStay(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		if (block != MBlocks.chorus_plant && block != Blocks.end_stone) {
			if (block.isAir(world, x, y - 1, z)) {
				int adjecentCount = 0;
				for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
					Block adjecentBlock = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
					if (adjecentBlock == MBlocks.chorus_plant){
						adjecentCount++;
					}else if (!adjecentBlock.isAir(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)){
						return false;
					}
				}
				return adjecentCount == 1;
			} else{
				return false;
			}
		} else{
			return true;
		}
	}

    public static boolean isSpaceAroundFree(World world, int x, int y, int z, ForgeDirection skip) {
		Iterator<ForgeDirection> iterator = Arrays.asList(ForgeDirection.VALID_DIRECTIONS).iterator();

		ForgeDirection dir;
		do {
			if (!iterator.hasNext()){
				return true;
			}
			dir = iterator.next();
		} while (dir == skip || world.isAirBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ));

		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && canBlockStay(world, x, y, z);
	}
}