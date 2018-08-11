package com.cubic_control.UpdateCraft.Advancements;

import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum FrameType
{
    TASK("task", 0, EnumChatFormatting.GREEN),
    CHALLENGE("challenge", 26, EnumChatFormatting.DARK_PURPLE),
    GOAL("goal", 52, EnumChatFormatting.GREEN);

    private final String name;
    private final int icon;
    private final EnumChatFormatting format;

    private FrameType(String nameIn, int iconIn, EnumChatFormatting formatIn)
    {
        this.name = nameIn;
        this.icon = iconIn;
        this.format = formatIn;
    }

    public String getName()
    {
        return this.name;
    }

    public static FrameType byName(String nameIn)
    {
        for (FrameType frametype : values())
        {
            if (frametype.name.equals(nameIn))
            {
                return frametype;
            }
        }

        throw new IllegalArgumentException("Unknown frame type '" + nameIn + "'");
    }

    @SideOnly(Side.CLIENT)
    public int getIcon()
    {
        return this.icon;
    }

    public EnumChatFormatting getFormat()
    {
        return this.format;
    }
}