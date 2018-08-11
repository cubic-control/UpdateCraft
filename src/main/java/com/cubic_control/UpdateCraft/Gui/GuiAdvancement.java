package com.cubic_control.UpdateCraft.Gui;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import com.cubic_control.UpdateCraft.Advancements.Advancement;
import com.cubic_control.UpdateCraft.Advancements.AdvancementProgress;
import com.cubic_control.UpdateCraft.Advancements.DisplayInfo;
import com.cubic_control.UpdateCraft.Lib.RefStrings;
import com.cubic_control.cubic_core.Utils.GlStateManager;
import com.google.common.collect.Lists;
import com.sun.istack.internal.Nullable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvancement extends Gui
{
    private static final ResourceLocation WIDGETS = new ResourceLocation(RefStrings.MODID+":textures/gui/advancements/widgets.png");
    private static final Pattern PATTERN = Pattern.compile("(.+) \\S+");
    private final GuiAdvancementTab guiAdvancementTab;
    private final Advancement advancement;
    private final DisplayInfo displayInfo;
    private final String field_191831_i;
    private final int field_191832_j;
    private final List<String> field_192997_l;
    private final Minecraft minecraft;
    private GuiAdvancement guiAdvancement;
    private final List<GuiAdvancement> field_191835_m = Lists.<GuiAdvancement>newArrayList();
    private AdvancementProgress advancementProgress;
    private final int field_191837_o;
    private final int field_191826_p;

    public GuiAdvancement(GuiAdvancementTab p_i47385_1_, Minecraft p_i47385_2_, Advancement p_i47385_3_, DisplayInfo p_i47385_4_)
    {
        this.guiAdvancementTab = p_i47385_1_;
        this.advancement = p_i47385_3_;
        this.displayInfo = p_i47385_4_;
        this.minecraft = p_i47385_2_;
        this.field_191831_i = p_i47385_2_.fontRenderer.trimStringToWidth(p_i47385_4_.getTitle().getFormattedText(), 163);
        this.field_191837_o = MathHelper.floor_float(p_i47385_4_.getX() * 28.0F);
        this.field_191826_p = MathHelper.floor_float(p_i47385_4_.getY() * 27.0F);
        int i = p_i47385_3_.getRequirementCount();
        int j = String.valueOf(i).length();
        int k = i > 1 ? p_i47385_2_.fontRenderer.getStringWidth("  ") + p_i47385_2_.fontRenderer.getStringWidth("0") * j * 2 + p_i47385_2_.fontRenderer.getStringWidth("/") : 0;
        int l = 29 + p_i47385_2_.fontRenderer.getStringWidth(this.field_191831_i) + k;
        String s = p_i47385_4_.getDescription().getFormattedText();
        this.field_192997_l = this.func_192995_a(s, l);

        for (String s1 : this.field_192997_l)
        {
            l = Math.max(l, p_i47385_2_.fontRenderer.getStringWidth(s1));
        }

        this.field_191832_j = l + 3 + 5;
    }

    private List<String> func_192995_a(String p_192995_1_, int p_192995_2_)
    {
        if (p_192995_1_.isEmpty())
        {
            return Collections.<String>emptyList();
        }
        else
        {
            List<String> list = this.minecraft.fontRenderer.listFormattedStringToWidth(p_192995_1_, p_192995_2_);

            if (list.size() < 2)
            {
                return list;
            }
            else
            {
                String s = list.get(0);
                String s1 = list.get(1);
                int i = this.minecraft.fontRenderer.getStringWidth(s + ' ' + s1.split(" ")[0]);

                if (i - p_192995_2_ <= 10)
                {
                    return this.minecraft.fontRenderer.listFormattedStringToWidth(p_192995_1_, i);
                }
                else
                {
                    Matcher matcher = PATTERN.matcher(s);

                    if (matcher.matches())
                    {
                        int j = this.minecraft.fontRenderer.getStringWidth(matcher.group(1));

                        if (p_192995_2_ - j <= 10)
                        {
                            return this.minecraft.fontRenderer.listFormattedStringToWidth(p_192995_1_, j);
                        }
                    }

                    return list;
                }
            }
        }
    }

    @Nullable
    private GuiAdvancement func_191818_a(Advancement p_191818_1_)
    {
        while (true)
        {
            p_191818_1_ = p_191818_1_.getParent();

            if (p_191818_1_ == null || p_191818_1_.getDisplay() != null)
            {
                break;
            }
        }

        if (p_191818_1_ != null && p_191818_1_.getDisplay() != null)
        {
            return this.guiAdvancementTab.func_191794_b(p_191818_1_);
        }
        else
        {
            return null;
        }
    }

    public void func_191819_a(int p_191819_1_, int p_191819_2_, boolean p_191819_3_)
    {
        if (this.guiAdvancement != null)
        {
            int i = p_191819_1_ + this.guiAdvancement.field_191837_o + 13;
            int j = p_191819_1_ + this.guiAdvancement.field_191837_o + 26 + 4;
            int k = p_191819_2_ + this.guiAdvancement.field_191826_p + 13;
            int l = p_191819_1_ + this.field_191837_o + 13;
            int i1 = p_191819_2_ + this.field_191826_p + 13;
            int j1 = p_191819_3_ ? -16777216 : -1;

            if (p_191819_3_)
            {
                this.drawHorizontalLine(j, i, k - 1, j1);
                this.drawHorizontalLine(j + 1, i, k, j1);
                this.drawHorizontalLine(j, i, k + 1, j1);
                this.drawHorizontalLine(l, j - 1, i1 - 1, j1);
                this.drawHorizontalLine(l, j - 1, i1, j1);
                this.drawHorizontalLine(l, j - 1, i1 + 1, j1);
                this.drawVerticalLine(j - 1, i1, k, j1);
                this.drawVerticalLine(j + 1, i1, k, j1);
            }
            else
            {
                this.drawHorizontalLine(j, i, k, j1);
                this.drawHorizontalLine(l, j, i1, j1);
                this.drawVerticalLine(j, i1, k, j1);
            }
        }

        for (GuiAdvancement guiadvancement : this.field_191835_m)
        {
            guiadvancement.func_191819_a(p_191819_1_, p_191819_2_, p_191819_3_);
        }
    }

    public void func_191817_b(int p_191817_1_, int p_191817_2_)
    {
        if (!this.displayInfo.isHidden() || this.advancementProgress != null && this.advancementProgress.func_192105_a())
        {
            float f = this.advancementProgress == null ? 0.0F : this.advancementProgress.func_192103_c();
            AdvancementState advancementstate;

            if (f >= 1.0F)
            {
                advancementstate = AdvancementState.OBTAINED;
            }
            else
            {
                advancementstate = AdvancementState.UNOBTAINED;
            }

            this.minecraft.getTextureManager().bindTexture(WIDGETS);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            this.drawTexturedModalRect(p_191817_1_ + this.field_191837_o + 3, p_191817_2_ + this.field_191826_p, this.displayInfo.getFrame().getIcon(), 128 + advancementstate.getId() * 26, 26, 26);
            RenderHelper.enableGUIStandardItemLighting();
            //TODO:
            //this.minecraft.getRenderItem().renderItemAndEffectIntoGUI((EntityLivingBase)null, this.displayInfo.getIcon(), p_191817_1_ + this.field_191837_o + 8, p_191817_2_ + this.field_191826_p + 5);
        }

        for (GuiAdvancement guiadvancement : this.field_191835_m)
        {
            guiadvancement.func_191817_b(p_191817_1_, p_191817_2_);
        }
    }

    public void getAdvancementProgress(AdvancementProgress advancementProgressIn)
    {
        this.advancementProgress = advancementProgressIn;
    }

    public void addGuiAdvancement(GuiAdvancement guiAdvancementIn)
    {
        this.field_191835_m.add(guiAdvancementIn);
    }

    public void func_191821_a(int p_191821_1_, int p_191821_2_, float p_191821_3_, int p_191821_4_, int p_191821_5_)
    {
        boolean flag = p_191821_4_ + p_191821_1_ + this.field_191837_o + this.field_191832_j + 26 >= this.guiAdvancementTab.func_193934_g().width;
        String s = this.advancementProgress == null ? null : this.advancementProgress.func_193126_d();
        int i = s == null ? 0 : this.minecraft.fontRenderer.getStringWidth(s);
        boolean flag1 = 113 - p_191821_2_ - this.field_191826_p - 26 <= 6 + this.field_192997_l.size() * this.minecraft.fontRenderer.FONT_HEIGHT;
        float f = this.advancementProgress == null ? 0.0F : this.advancementProgress.func_192103_c();
        int j = MathHelper.floor_float(f * (float)this.field_191832_j);
        AdvancementState advancementstate;
        AdvancementState advancementstate1;
        AdvancementState advancementstate2;

        if (f >= 1.0F)
        {
            j = this.field_191832_j / 2;
            advancementstate = AdvancementState.OBTAINED;
            advancementstate1 = AdvancementState.OBTAINED;
            advancementstate2 = AdvancementState.OBTAINED;
        }
        else if (j < 2)
        {
            j = this.field_191832_j / 2;
            advancementstate = AdvancementState.UNOBTAINED;
            advancementstate1 = AdvancementState.UNOBTAINED;
            advancementstate2 = AdvancementState.UNOBTAINED;
        }
        else if (j > this.field_191832_j - 2)
        {
            j = this.field_191832_j / 2;
            advancementstate = AdvancementState.OBTAINED;
            advancementstate1 = AdvancementState.OBTAINED;
            advancementstate2 = AdvancementState.UNOBTAINED;
        }
        else
        {
            advancementstate = AdvancementState.OBTAINED;
            advancementstate1 = AdvancementState.UNOBTAINED;
            advancementstate2 = AdvancementState.UNOBTAINED;
        }

        int k = this.field_191832_j - j;
        this.minecraft.getTextureManager().bindTexture(WIDGETS);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        int l = p_191821_2_ + this.field_191826_p;
        int i1;

        if (flag)
        {
            i1 = p_191821_1_ + this.field_191837_o - this.field_191832_j + 26 + 6;
        }
        else
        {
            i1 = p_191821_1_ + this.field_191837_o;
        }

        int j1 = 32 + this.field_192997_l.size() * this.minecraft.fontRenderer.FONT_HEIGHT;

        if (!this.field_192997_l.isEmpty())
        {
            if (flag1)
            {
                this.func_192994_a(i1, l + 26 - j1, this.field_191832_j, j1, 10, 200, 26, 0, 52);
            }
            else
            {
                this.func_192994_a(i1, l, this.field_191832_j, j1, 10, 200, 26, 0, 52);
            }
        }

        this.drawTexturedModalRect(i1, l, 0, advancementstate.getId() * 26, j, 26);
        this.drawTexturedModalRect(i1 + j, l, 200 - k, advancementstate1.getId() * 26, k, 26);
        this.drawTexturedModalRect(p_191821_1_ + this.field_191837_o + 3, p_191821_2_ + this.field_191826_p, this.displayInfo.getFrame().getIcon(), 128 + advancementstate2.getId() * 26, 26, 26);

        if (flag)
        {
            this.minecraft.fontRenderer.drawString(this.field_191831_i, (i1 + 5), (p_191821_2_ + this.field_191826_p + 9), -1, true);

            if (s != null)
            {
                this.minecraft.fontRenderer.drawString(s, (p_191821_1_ + this.field_191837_o - i), (p_191821_2_ + this.field_191826_p + 9), -1, true);
            }
        }
        else
        {
            this.minecraft.fontRenderer.drawString(this.field_191831_i, (p_191821_1_ + this.field_191837_o + 32), (p_191821_2_ + this.field_191826_p + 9), -1, true);

            if (s != null)
            {
                this.minecraft.fontRenderer.drawString(s, (p_191821_1_ + this.field_191837_o + this.field_191832_j - i - 5), (p_191821_2_ + this.field_191826_p + 9), -1, true);
            }
        }

        if (flag1)
        {
            for (int k1 = 0; k1 < this.field_192997_l.size(); ++k1)
            {
                this.minecraft.fontRenderer.drawString(this.field_192997_l.get(k1), (i1 + 5), (l + 26 - j1 + 7 + k1 * this.minecraft.fontRenderer.FONT_HEIGHT), -5592406, false);
            }
        }
        else
        {
            for (int l1 = 0; l1 < this.field_192997_l.size(); ++l1)
            {
                this.minecraft.fontRenderer.drawString(this.field_192997_l.get(l1), (i1 + 5), (p_191821_2_ + this.field_191826_p + 9 + 17 + l1 * this.minecraft.fontRenderer.FONT_HEIGHT), -5592406, false);
            }
        }

        RenderHelper.enableGUIStandardItemLighting();
        //TODO:
        //this.minecraft.getRenderItem().renderItemAndEffectIntoGUI((EntityLivingBase)null, this.displayInfo.getIcon(), p_191821_1_ + this.field_191837_o + 8, p_191821_2_ + this.field_191826_p + 5);
    }

    protected void func_192994_a(int p_192994_1_, int p_192994_2_, int p_192994_3_, int p_192994_4_, int p_192994_5_, int p_192994_6_, int p_192994_7_, int p_192994_8_, int p_192994_9_)
    {
        this.drawTexturedModalRect(p_192994_1_, p_192994_2_, p_192994_8_, p_192994_9_, p_192994_5_, p_192994_5_);
        this.func_192993_a(p_192994_1_ + p_192994_5_, p_192994_2_, p_192994_3_ - p_192994_5_ - p_192994_5_, p_192994_5_, p_192994_8_ + p_192994_5_, p_192994_9_, p_192994_6_ - p_192994_5_ - p_192994_5_, p_192994_7_);
        this.drawTexturedModalRect(p_192994_1_ + p_192994_3_ - p_192994_5_, p_192994_2_, p_192994_8_ + p_192994_6_ - p_192994_5_, p_192994_9_, p_192994_5_, p_192994_5_);
        this.drawTexturedModalRect(p_192994_1_, p_192994_2_ + p_192994_4_ - p_192994_5_, p_192994_8_, p_192994_9_ + p_192994_7_ - p_192994_5_, p_192994_5_, p_192994_5_);
        this.func_192993_a(p_192994_1_ + p_192994_5_, p_192994_2_ + p_192994_4_ - p_192994_5_, p_192994_3_ - p_192994_5_ - p_192994_5_, p_192994_5_, p_192994_8_ + p_192994_5_, p_192994_9_ + p_192994_7_ - p_192994_5_, p_192994_6_ - p_192994_5_ - p_192994_5_, p_192994_7_);
        this.drawTexturedModalRect(p_192994_1_ + p_192994_3_ - p_192994_5_, p_192994_2_ + p_192994_4_ - p_192994_5_, p_192994_8_ + p_192994_6_ - p_192994_5_, p_192994_9_ + p_192994_7_ - p_192994_5_, p_192994_5_, p_192994_5_);
        this.func_192993_a(p_192994_1_, p_192994_2_ + p_192994_5_, p_192994_5_, p_192994_4_ - p_192994_5_ - p_192994_5_, p_192994_8_, p_192994_9_ + p_192994_5_, p_192994_6_, p_192994_7_ - p_192994_5_ - p_192994_5_);
        this.func_192993_a(p_192994_1_ + p_192994_5_, p_192994_2_ + p_192994_5_, p_192994_3_ - p_192994_5_ - p_192994_5_, p_192994_4_ - p_192994_5_ - p_192994_5_, p_192994_8_ + p_192994_5_, p_192994_9_ + p_192994_5_, p_192994_6_ - p_192994_5_ - p_192994_5_, p_192994_7_ - p_192994_5_ - p_192994_5_);
        this.func_192993_a(p_192994_1_ + p_192994_3_ - p_192994_5_, p_192994_2_ + p_192994_5_, p_192994_5_, p_192994_4_ - p_192994_5_ - p_192994_5_, p_192994_8_ + p_192994_6_ - p_192994_5_, p_192994_9_ + p_192994_5_, p_192994_6_, p_192994_7_ - p_192994_5_ - p_192994_5_);
    }

    protected void func_192993_a(int p_192993_1_, int p_192993_2_, int p_192993_3_, int p_192993_4_, int p_192993_5_, int p_192993_6_, int p_192993_7_, int p_192993_8_)
    {
        for (int i = 0; i < p_192993_3_; i += p_192993_7_)
        {
            int j = p_192993_1_ + i;
            int k = Math.min(p_192993_7_, p_192993_3_ - i);

            for (int l = 0; l < p_192993_4_; l += p_192993_8_)
            {
                int i1 = p_192993_2_ + l;
                int j1 = Math.min(p_192993_8_, p_192993_4_ - l);
                this.drawTexturedModalRect(j, i1, p_192993_5_, p_192993_6_, k, j1);
            }
        }
    }

    public boolean func_191816_c(int p_191816_1_, int p_191816_2_, int p_191816_3_, int p_191816_4_)
    {
        if (!this.displayInfo.isHidden() || this.advancementProgress != null && this.advancementProgress.func_192105_a())
        {
            int i = p_191816_1_ + this.field_191837_o;
            int j = i + 26;
            int k = p_191816_2_ + this.field_191826_p;
            int l = k + 26;
            return p_191816_3_ >= i && p_191816_3_ <= j && p_191816_4_ >= k && p_191816_4_ <= l;
        }
        else
        {
            return false;
        }
    }

    public void func_191825_b()
    {
        if (this.guiAdvancement == null && this.advancement.getParent() != null)
        {
            this.guiAdvancement = this.func_191818_a(this.advancement);

            if (this.guiAdvancement != null)
            {
                this.guiAdvancement.addGuiAdvancement(this);
            }
        }
    }

    public int func_191820_c()
    {
        return this.field_191826_p;
    }

    public int func_191823_d()
    {
        return this.field_191837_o;
    }
}