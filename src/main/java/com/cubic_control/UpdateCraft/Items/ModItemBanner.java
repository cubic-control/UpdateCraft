package com.cubic_control.UpdateCraft.Items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.cubic_control.UpdateCraft.Blocks.MBlocks;
import com.cubic_control.UpdateCraft.CreativeTabs.MCreativeTabs;
import com.cubic_control.UpdateCraft.TileEntities.ModTileEntityBanner;
import com.cubic_control.UpdateCraft.Utils.ModEnumDyeColor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModItemBanner extends ItemBlock
{
    public ModItemBanner(String par1)
    {
        super(MBlocks.standing_banner);
        this.maxStackSize = 16;
        this.setCreativeTab(MCreativeTabs.tabItems);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        GameRegistry.registerItem(this, par1);
    }
    
    /**
     * Get an NBTTagCompound from this stack's NBT data.
     */
    public static NBTTagCompound getSubCompound(String key, boolean create, ItemStack stack){
        if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(key, 10))
        {
            return stack.stackTagCompound.getCompoundTag(key);
        }
        else if (create)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            stack.setTagInfo(key, nbttagcompound);
            return nbttagcompound;
        }
        else
        {
            return null;
        }
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlock(x, y, z) == Blocks.cauldron) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta > 0) {
				stack.setTagCompound(null);
				world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
				return true;
			}
		}

		if (side == 0)
			return false;
		else if (!world.getBlock(x, y, z).getMaterial().isSolid())
			return false;
		else {
			if (side == 1)
				y++;
			if (side == 2)
				z--;
			if (side == 3)
				z++;
			if (side == 4)
				x--;
			if (side == 5)
				x++;

			if (!player.canPlayerEdit(x, y, z, side, stack))
				return false;
			else if (!field_150939_a.canPlaceBlockAt(world, x, y, z))
				return false;
			else {
				if (side == 1) {
					int meta = MathHelper.floor_double((player.rotationYaw + 180.0F) * 16.0F / 360.0F + 0.5D) & 15;
					world.setBlock(x, y, z, field_150939_a, meta, 3);
				} else
					world.setBlock(x, y, z, field_150939_a, side, 3);

				stack.stackSize--;
				ModTileEntityBanner banner = (ModTileEntityBanner) world.getTileEntity(x, y, z);
				if (banner != null) {
					banner.isStanding = side == 1;
					banner.setItemValues(stack);
				}
				return true;
			}
		}
	}

    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = "item.banner.";
        ModEnumDyeColor enumdyecolor = getBaseColor(stack);
        s = s + enumdyecolor.getUnlocalizedName() + ".name";
        return StatCollector.translateToLocal(s);
    }

    @SideOnly(Side.CLIENT)
    public static void func_185054_a(ItemStack stack, List<String> p_185054_1_)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", false, stack);

        if (nbttagcompound != null && nbttagcompound.hasKey("Patterns"))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Patterns", 10);

            for (int i = 0; i < nbttaglist.tagCount() && i < 6; ++i)
            {
                NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
                ModEnumDyeColor enumdyecolor = ModEnumDyeColor.byDyeDamage(nbttagcompound1.getInteger("Color"));
                ModTileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern = ModTileEntityBanner.EnumBannerPattern.getPatternByID(nbttagcompound1.getString("Pattern"));

                if (tileentitybanner$enumbannerpattern != null)
                {
                    p_185054_1_.add(StatCollector.translateToLocal("item.banner." + tileentitybanner$enumbannerpattern.getPatternName() + "." + enumdyecolor.getUnlocalizedName()));
                }
            }
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        func_185054_a(stack, tooltip);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (ModEnumDyeColor enumdyecolor : ModEnumDyeColor.values())
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            ModTileEntityBanner.setBaseColorAndPatterns(nbttagcompound, enumdyecolor.getDyeDamage(), (NBTTagList)null);
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setTag("BlockEntityTag", nbttagcompound);
            ItemStack itemstack = new ItemStack(itemIn, 1, enumdyecolor.getDyeDamage());
            itemstack.setTagCompound(nbttagcompound1);
            subItems.add(itemstack);
        }
    }

    /**
     * gets the CreativeTab this item is displayed on
     */
    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTab()
    {
        return CreativeTabs.tabDecorations;
    }

    public static ModEnumDyeColor getBaseColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", false, stack);
        ModEnumDyeColor enumdyecolor = null;

        if (nbttagcompound != null && nbttagcompound.hasKey("Base"))
        {
            enumdyecolor = ModEnumDyeColor.byDyeDamage(nbttagcompound.getInteger("Base"));
        }
        else
        {
            enumdyecolor = ModEnumDyeColor.byDyeDamage(stack.getItemDamage());
        }

        return enumdyecolor;
    }
}