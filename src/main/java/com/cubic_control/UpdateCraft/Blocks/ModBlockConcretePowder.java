package com.cubic_control.UpdateCraft.Blocks;

import java.util.List;
import java.util.Random;

import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.Entities.EntityFallingConcrete;
import com.cubic_control.UpdateCraft.Items.ModItemBlockWithMetadata;
import com.cubic_control.UpdateCraft.Lib.RefStrings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockConcretePowder extends BlockFalling{
	@SideOnly(Side.CLIENT)
    private IIcon[] iicon;
	
	Material[] validMaterials = new Material[] {
			Material.water,
			Material.lava
	};
	
	protected ModBlockConcretePowder(Material par1, String par2, float par3, String par4, int par5, float par6, SoundType par7) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par4, par5);
		this.setResistance(par6);
		this.setStepSound(par7);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	
	protected ModBlockConcretePowder(Material par1, String par2, float par3, float par6, SoundType par7) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setResistance(par6);
		this.setStepSound(par7);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	
	protected ModBlockConcretePowder(Material par1, String par2, String par3, float par4, String par5, int par6, float par7, SoundType par8) {
		super(par1);
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par3);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par4);
		this.setHarvestLevel(par5, par6);
		this.setResistance(par7);
		this.setStepSound(par8);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class,par2);
	}
	//Parent Block
	protected ModBlockConcretePowder(Block par1, String par2, float par3, float par4, SoundType par5) {
		super(par1.getMaterial());
		this.setBlockName(par2);
		this.setBlockTextureName(RefStrings.MODID + ":" + par2);
		this.setCreativeTab(MCreativeTabs.tabBlocks);
		this.setHardness(par3);
		this.setHarvestLevel(par1.getHarvestTool(0), par1.getHarvestLevel(0));
		this.setResistance(par4);
		this.setStepSound(par5);
		GameRegistry.registerBlock(this, ModItemBlockWithMetadata.class, par2);
	}
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
        this.checkForLiquid(world, x, y, z);
        super.onBlockAdded(world, x, y, z);
    }
	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
		if(world instanceof World) {
			World theWorld = (World) world;
			this.checkForLiquid(theWorld, x, y, z);
		}
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		this.checkForLiquid(world, x, y, z);
    }
	@Override
	public void updateTick(World world, int xPos, int yPos, int zPos, Random rand) {
		if(!world.isRemote) {
			this.func_149830_m(world, xPos, yPos, zPos);
		}
		super.updateTick(world, xPos, yPos, zPos, rand);
	}
	
	private void func_149830_m(World world, int xPos, int yPos, int zPos) {
		if(func_149831_e(world, xPos, yPos - 1, zPos) && yPos >= 0) {
			byte b0 = 32;

			if(!fallInstantly && world.checkChunksExist(xPos - b0, yPos - b0, zPos - b0, xPos + b0, yPos + b0, zPos + b0)) {
				if(!world.isRemote) {
					EntityFallingConcrete entityfallingblock = new EntityFallingConcrete(world, (double)((float)xPos + 0.5F), (double)((float)yPos + 0.5F), (double)((float)zPos + 0.5F), this, world.getBlockMetadata(xPos, yPos, zPos));
					this.func_149829_a(entityfallingblock);
					world.spawnEntityInWorld(entityfallingblock);
				}
			}else {
				world.setBlockToAir(xPos, yPos, zPos);

				while(func_149831_e(world, xPos, yPos - 1, zPos) && yPos > 0) {
					--yPos;
				}
				if(yPos > 0) {
					world.setBlock(xPos, yPos, zPos, this);
				}
			}
		}
	}
	
	public static boolean func_149831_e(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        
        if(block.isAir(world, x, y, z)){
            return true;
        }else if(block == Blocks.fire){
            return true;
        }else{
            Material material = block.getMaterial();
            return material == Material.water ? true : material == Material.lava;
        }
    }
	
	private boolean checkValidMaterial(Material material) {
		boolean valid = false;
		for(int i = 0; i < validMaterials.length; i++) {
			if(material == validMaterials[i]) {
				valid = true;
			}
		}
		return valid;
	}
	
	private void checkForLiquid(World world, int x, int y, int z) {
        if(world.getBlock(x, y, z) == this){
        	boolean flag = false;
        	
        	if(flag || checkValidMaterial(world.getBlock(x, y, z - 1).getMaterial())){
        		flag = true;
        	}
        	if(flag || checkValidMaterial(world.getBlock(x, y, z + 1).getMaterial())){
        		flag = true;
        	}
        	if(flag || checkValidMaterial(world.getBlock(x - 1, y, z).getMaterial())){
        		flag = true;
        	}
        	if(flag || checkValidMaterial(world.getBlock(x + 1, y, z).getMaterial())){
        		flag = true;
        	}
        	if(flag || checkValidMaterial(world.getBlock(x, y + 1, z).getMaterial())){
        		flag = true;
        	}
        	if(flag){
        		int l = world.getBlockMetadata(x, y, z);
        		world.setBlock(x, y, z, MBlocks.concrete);
        		world.setBlockMetadataWithNotify(x, y, z, l, 2);
        	}else {
				world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			}
        }
    }
	//Block Icons From 'BlockColored'
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int i1, int i2) {
        return this.iicon[i2 % this.iicon.length];
    }
	@Override
	public int damageDropped(int damage) {
        return damage;
    }
	
	public static int func_150032_b(int i) {
        return func_150031_c(i);
    }

    public static int func_150031_c(int i) {
        return ~i & 15;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < 16; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.iicon = new IIcon[16];

        for(int i = 0; i < this.iicon.length; ++i) {
            this.iicon[i] = reg.registerIcon(this.getTextureName() + "_" + ItemDye.field_150921_b[func_150031_c(i)]);
        }
    }
    @Override
    public MapColor getMapColor(int color) {
        return MapColor.getMapColorForBlockColored(color);
    }

}
