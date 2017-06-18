package com.cubic_control.UpdateCraft.Utils;

import net.minecraft.block.material.MapColor;

public enum ModEnumDyeColor implements IStringSerializable
{
    WHITE(0, 15, "white", "white", MapColor.snowColor, ModTextFormatting.WHITE),
    ORANGE(1, 14, "orange", "orange", MapColor.adobeColor, ModTextFormatting.GOLD),
    MAGENTA(2, 13, "magenta", "magenta", MapColor.magentaColor, ModTextFormatting.AQUA),
    LIGHT_BLUE(3, 12, "light_blue", "lightBlue", MapColor.lightBlueColor, ModTextFormatting.BLUE),
    YELLOW(4, 11, "yellow", "yellow", MapColor.yellowColor, ModTextFormatting.YELLOW),
    LIME(5, 10, "lime", "lime", MapColor.limeColor, ModTextFormatting.GREEN),
    PINK(6, 9, "pink", "pink", MapColor.pinkColor, ModTextFormatting.LIGHT_PURPLE),
    GRAY(7, 8, "gray", "gray", MapColor.grayColor, ModTextFormatting.DARK_GRAY),
    SILVER(8, 7, "silver", "silver", MapColor.silverColor, ModTextFormatting.GRAY),
    CYAN(9, 6, "cyan", "cyan", MapColor.cyanColor, ModTextFormatting.DARK_AQUA),
    PURPLE(10, 5, "purple", "purple", MapColor.purpleColor, ModTextFormatting.DARK_PURPLE),
    BLUE(11, 4, "blue", "blue", MapColor.blueColor, ModTextFormatting.DARK_BLUE),
    BROWN(12, 3, "brown", "brown", MapColor.brownColor, ModTextFormatting.GOLD),
    GREEN(13, 2, "green", "green", MapColor.greenColor, ModTextFormatting.DARK_GREEN),
    RED(14, 1, "red", "red", MapColor.redColor, ModTextFormatting.DARK_RED),
    BLACK(15, 0, "black", "black", MapColor.blackColor, ModTextFormatting.BLACK);

    private static final ModEnumDyeColor[] META_LOOKUP = new ModEnumDyeColor[values().length];
    private static final ModEnumDyeColor[] DYE_DMG_LOOKUP = new ModEnumDyeColor[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;
    private final MapColor mapColor;
    private final ModTextFormatting chatColor;

    private ModEnumDyeColor(int meta, int dyeDamage, String name, String unlocalizedName, MapColor mapColorIn, ModTextFormatting chatColor)
    {
        this.meta = meta;
        this.dyeDamage = dyeDamage;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.mapColor = mapColorIn;
        this.chatColor = chatColor;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public int getDyeDamage()
    {
        return this.dyeDamage;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    public static ModEnumDyeColor byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static ModEnumDyeColor byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString()
    {
        return this.unlocalizedName;
    }

    public String getName()
    {
        return this.name;
    }

    static
    {
        for (ModEnumDyeColor enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
            DYE_DMG_LOOKUP[enumdyecolor.getDyeDamage()] = enumdyecolor;
        }
    }
}