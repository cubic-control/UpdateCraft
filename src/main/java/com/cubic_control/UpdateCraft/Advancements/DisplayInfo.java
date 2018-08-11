package com.cubic_control.UpdateCraft.Advancements;

import java.io.IOException;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.sun.istack.internal.Nullable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DisplayInfo
{
    private final IChatComponent title;
    private final IChatComponent description;
    private final ItemStack icon;
    private final ResourceLocation background;
    private final FrameType frame;
    private final boolean showToast;
    private final boolean announceToChat;
    private final boolean hidden;
    private float x;
    private float y;

    public DisplayInfo(ItemStack icon, IChatComponent title, IChatComponent description, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden)
    {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.background = background;
        this.frame = frame;
        this.showToast = showToast;
        this.announceToChat = announceToChat;
        this.hidden = hidden;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public IChatComponent getTitle()
    {
        return this.title;
    }

    public IChatComponent getDescription()
    {
        return this.description;
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIcon()
    {
        return this.icon;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public ResourceLocation getBackground()
    {
        return this.background;
    }

    public FrameType getFrame()
    {
        return this.frame;
    }

    @SideOnly(Side.CLIENT)
    public float getX()
    {
        return this.x;
    }

    @SideOnly(Side.CLIENT)
    public float getY()
    {
        return this.y;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldShowToast()
    {
        return this.showToast;
    }

    public boolean shouldAnnounceToChat()
    {
        return this.announceToChat;
    }

    public boolean isHidden()
    {
        return this.hidden;
    }

    @SuppressWarnings("unused")
	public static DisplayInfo deserialize(JsonObject object, JsonDeserializationContext context)
    {
    	//TODO:
        IChatComponent itextcomponent = (IChatComponent)/*JsonUtils.deserializeClass(object, "title", context, IChatComponent.class)*/null;
        IChatComponent itextcomponent1 = (IChatComponent)/*JsonUtils.deserializeClass(object, "description", context, IChatComponent.class)*/null;

        if (itextcomponent != null && itextcomponent1 != null)
        {
            ItemStack itemstack = deserializeIcon(JsonUtils.getJsonElementAsJsonObject(object, "icon"));
            ResourceLocation resourcelocation = object.has("background") ? new ResourceLocation(JsonUtils.getJsonElementStringValue(object, "background")) : null;
            FrameType frametype = object.has("frame") ? FrameType.byName(JsonUtils.getJsonElementStringValue(object, "frame")) : FrameType.TASK;
            boolean flag = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(object, "show_toast", true);
            boolean flag1 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(object, "announce_to_chat", true);
            boolean flag2 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(object, "hidden", false);
            return new DisplayInfo(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, flag1, flag2);
        }
        else
        {
            throw new JsonSyntaxException("Both title and description must be set");
        }
    }

    private static ItemStack deserializeIcon(JsonObject object)
    {
        if (!object.has("item"))
        {
            throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
        }
        else
        {
        	//TODO:
            //Item item = JsonUtils.getItem(object, "item");
            int i = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(object, "data", 0);
            //TODO:
            return new ItemStack(/*item*/(Item)null, 1, i);
        }
    }

    public void write(PacketBuffer buf)
    {
    	//TODO:
        //buf.writeTextComponent(this.title);
        //buf.writeTextComponent(this.description);
        //buf.writeItemStackToBuffer(this.icon);
        //buf.writeEnumValue(this.frame);
        int i = 0;

        if (this.background != null)
        {
            i |= 1;
        }

        if (this.showToast)
        {
            i |= 2;
        }

        if (this.hidden)
        {
            i |= 4;
        }

        buf.writeInt(i);

        if (this.background != null)
        {
        	//TODO:
            //buf.writeResourceLocation(this.background);
        }

        buf.writeFloat(this.x);
        buf.writeFloat(this.y);
    }

    public static DisplayInfo read(PacketBuffer buf) throws IOException
    {
    	//TODO:
        IChatComponent itextcomponent = /*buf.readTextComponent()*/null;
        IChatComponent itextcomponent1 = /*buf.readTextComponent()*/null;
        ItemStack itemstack = buf.readItemStackFromBuffer();
        FrameType frametype = (FrameType)/*buf.readEnumValue(FrameType.class)*/null;
        int i = buf.readInt();
        ResourceLocation resourcelocation = /*(i & 1) != 0 ? buf.readResourceLocation() :*/ null;
        boolean flag = (i & 2) != 0;
        boolean flag1 = (i & 4) != 0;
        DisplayInfo displayinfo = new DisplayInfo(itemstack, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, false, flag1);
        displayinfo.setPosition(buf.readFloat(), buf.readFloat());
        return displayinfo;
    }
}