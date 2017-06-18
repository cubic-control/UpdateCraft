package com.cubic_control.UpdateCraft.TileEntities;

import java.util.List;

import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.cubic_control.UpdateCraft.Items.ModItemBanner;
import com.cubic_control.UpdateCraft.Utils.ModEnumDyeColor;
import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModTileEntityBanner extends TileEntity
{
    private int baseColor;
    /** A list of all the banner patterns. */
    private NBTTagList patterns;
    private boolean patternDataSet;
    private List<ModTileEntityBanner.EnumBannerPattern> patternList;
    private List<ModEnumDyeColor> colorList;
    /** This is a String representation of this banners pattern and color lists, used for texture caching. */
    private String patternResourceLocation;
    
    public boolean isStanding;

    public void setItemValues(ItemStack stack)
    {
        this.patterns = null;

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag", 10))
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("BlockEntityTag");

            if (nbttagcompound.hasKey("Patterns"))
            {
                this.patterns = (NBTTagList)nbttagcompound.getTagList("Patterns", 10).copy();
            }

            if (nbttagcompound.hasKey("Base", 99))
            {
                this.baseColor = nbttagcompound.getInteger("Base");
            }
            else
            {
                this.baseColor = stack.getItemDamage() & 15;
            }
        }
        else
        {
            this.baseColor = stack.getItemDamage() & 15;
        }

        this.patternList = null;
        this.colorList = null;
        this.patternResourceLocation = "";
        this.patternDataSet = true;
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        setBaseColorAndPatterns(compound, this.baseColor, this.patterns);
        
        compound.setBoolean("IsStanding", isStanding);
    }

    public static void setBaseColorAndPatterns(NBTTagCompound compound, int baseColorIn, NBTTagList patternsIn)
    {
        compound.setInteger("Base", baseColorIn);

        if (patternsIn != null)
        {
            compound.setTag("Patterns", patternsIn);
        }
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.baseColor = compound.getInteger("Base");
        this.patterns = compound.getTagList("Patterns", 10);
        this.patternList = null;
        this.colorList = null;
        this.patternResourceLocation = null;
        this.patternDataSet = true;
        
        isStanding = compound.getBoolean("IsStanding");
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbttagcompound);
    }

    public int getBaseColor()
    {
        return this.baseColor;
    }

    public static int getBaseColor(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", false, stack);
        return nbttagcompound != null && nbttagcompound.hasKey("Base") ? nbttagcompound.getInteger("Base") : stack.getItemDamage();
    }

    /**
     * Retrieves the amount of patterns stored on an ItemStack. If the tag does not exist this value will be 0.
     */
    public static int getPatterns(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", false, stack);
        return nbttagcompound != null && nbttagcompound.hasKey("Patterns") ? nbttagcompound.getTagList("Patterns", 10).tagCount() : 0;
    }

    @SideOnly(Side.CLIENT)
    public List<ModTileEntityBanner.EnumBannerPattern> getPatternList()
    {
        this.initializeBannerData();
        return this.patternList;
    }

    public NBTTagList getPatterns()
    {
        return this.patterns;
    }

    @SideOnly(Side.CLIENT)
    public List<ModEnumDyeColor> getColorList()
    {
        this.initializeBannerData();
        return this.colorList;
    }

    @SideOnly(Side.CLIENT)
    public String getPatternResourceLocation()
    {
        this.initializeBannerData();
        return this.patternResourceLocation;
    }

    /**
     * Establishes all of the basic properties for the banner. This will also apply the data from the tile entities nbt
     * tag compounds.
     */
    @SideOnly(Side.CLIENT)
    private void initializeBannerData()
    {
        if (this.patternList == null || this.colorList == null || this.patternResourceLocation == null)
        {
            if (!this.patternDataSet)
            {
                this.patternResourceLocation = "";
            }
            else
            {
                this.patternList = Lists.<ModTileEntityBanner.EnumBannerPattern>newArrayList();
                this.colorList = Lists.<ModEnumDyeColor>newArrayList();
                this.patternList.add(ModTileEntityBanner.EnumBannerPattern.BASE);
                this.colorList.add(ModEnumDyeColor.byDyeDamage(this.baseColor));
                this.patternResourceLocation = "b" + this.baseColor;

                if (this.patterns != null)
                {
                    for (int i = 0; i < this.patterns.tagCount(); ++i)
                    {
                        NBTTagCompound nbttagcompound = this.patterns.getCompoundTagAt(i);
                        ModTileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern = ModTileEntityBanner.EnumBannerPattern.getPatternByID(nbttagcompound.getString("Pattern"));

                        if (tileentitybanner$enumbannerpattern != null)
                        {
                            this.patternList.add(tileentitybanner$enumbannerpattern);
                            int j = nbttagcompound.getInteger("Color");
                            this.colorList.add(ModEnumDyeColor.byDyeDamage(j));
                            this.patternResourceLocation = this.patternResourceLocation + tileentitybanner$enumbannerpattern.getPatternID() + j;
                        }
                    }
                }
            }
        }
    }

    public static void func_184248_a(ItemStack p_184248_0_, ModEnumDyeColor p_184248_1_)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", true, p_184248_0_);
        nbttagcompound.setInteger("Base", p_184248_1_.getDyeDamage());
    }

    /**
     * Removes all the banner related data from a provided instance of ItemStack.
     */
    public static void removeBannerData(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = ModItemBanner.getSubCompound("BlockEntityTag", false, stack);

        if (nbttagcompound != null && nbttagcompound.hasKey("Patterns", 9))
        {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Patterns", 10);

            if (nbttaglist.tagCount() > 0)
            {
                nbttaglist.removeTag(nbttaglist.tagCount() - 1);

                if (nbttaglist.tagCount() == 0)
                {
                    stack.getTagCompound().removeTag("BlockEntityTag");

                    if (stack.getTagCompound().hasNoTags())
                    {
                        stack.setTagCompound((NBTTagCompound)null);
                    }
                }
            }
        }
    }

    public static enum EnumBannerPattern
    {
        BASE("base", "b"),
        SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
        SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
        SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
        SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
        STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
        STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
        STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
        STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
        STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
        STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
        STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
        STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
        STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
        CROSS("cross", "cr", "# #", " # ", "# #"),
        STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
        TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
        TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
        TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
        TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
        DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
        DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
        DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
        DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
        CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
        RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
        HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
        HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
        HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
        HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
        BORDER("border", "bo", "###", "# #", "###"),
        CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.vine)),
        CREEPER("creeper", "cre", new ItemStack(Items.skull, 1, 4)),
        GRADIENT("gradient", "gra", "# #", " # ", " # "),
        GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
        BRICKS("bricks", "bri", new ItemStack(Blocks.brick_block)),
        SKULL("skull", "sku", new ItemStack(Items.skull, 1, 1)),
        FLOWER("flower", "flo", new ItemStack(Blocks.red_flower, 1, 8)),
        MOJANG("mojang", "moj", new ItemStack(Items.golden_apple, 1, 1));

        /** The name used to represent this pattern. */
        private String patternName;
        /** A short string used to represent the pattern. */
        private String patternID;
        /** An array of three strings where each string represents a layer in the crafting grid. Goes from top to bottom. */
        private String[] craftingLayers;
        /** An ItemStack used to apply this pattern. */
        private ItemStack patternCraftingStack;

        private EnumBannerPattern(String name, String id)
        {
            this.craftingLayers = new String[3];
            this.patternName = name;
            this.patternID = id;
        }

        private EnumBannerPattern(String name, String id, ItemStack craftingItem)
        {
            this(name, id);
            this.patternCraftingStack = craftingItem;
        }

        private EnumBannerPattern(String name, String id, String craftingTop, String craftingMid, String craftingBot)
        {
            this(name, id);
            this.craftingLayers[0] = craftingTop;
            this.craftingLayers[1] = craftingMid;
            this.craftingLayers[2] = craftingBot;
        }

        /**
         * Retrieves the name used to represent this pattern.
         */
        @SideOnly(Side.CLIENT)
        public String getPatternName()
        {
            return this.patternName;
        }

        /**
         * Retrieves the short string used to represent this pattern.
         */
        public String getPatternID()
        {
            return this.patternID;
        }

        /**
         * Retrieves the string array which represents the associated crafting recipe for this banner effect. The first
         * object in the array is the top layer while the second is middle and third is last.
         */
        public String[] getCraftingLayers()
        {
            return this.craftingLayers;
        }

        /**
         * Checks to see if this pattern has a valid crafting stack, or if the top crafting layer is not null.
         */
        public boolean hasValidCrafting()
        {
            return this.patternCraftingStack != null || this.craftingLayers[0] != null;
        }

        /**
         * Checks to see if this pattern has a specific ItemStack associated with it's crafting.
         */
        public boolean hasCraftingStack()
        {
            return this.patternCraftingStack != null;
        }

        /**
         * Retrieves the ItemStack associated with the crafting of this pattern.
         */
        public ItemStack getCraftingStack()
        {
            return this.patternCraftingStack;
        }

        /**
         * Retrieves an instance of a banner pattern by its short string id.
         */
        @SideOnly(Side.CLIENT)
        public static ModTileEntityBanner.EnumBannerPattern getPatternByID(String id)
        {
            for (ModTileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern : values())
            {
                if (tileentitybanner$enumbannerpattern.patternID.equals(id))
                {
                    return tileentitybanner$enumbannerpattern;
                }
            }

            return null;
        }
    }
}