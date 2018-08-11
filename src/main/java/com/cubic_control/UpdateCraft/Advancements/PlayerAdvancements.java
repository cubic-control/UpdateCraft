package com.cubic_control.UpdateCraft.Advancements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.Nullable;

public class PlayerAdvancements
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(AdvancementProgress.class, new AdvancementProgress.Serializer()).registerTypeAdapter(ResourceLocation.class, new Object()).setPrettyPrinting().create();
    private static final TypeToken<Map<ResourceLocation, AdvancementProgress>> MAP_TOKEN = new TypeToken<Map<ResourceLocation, AdvancementProgress>>()
    {
    };
    private final MinecraftServer server;
    private final File progressFile;
    private final Map<Advancement, AdvancementProgress> progress = Maps.<Advancement, AdvancementProgress>newLinkedHashMap();
    private final Set<Advancement> field_192759_g = Sets.<Advancement>newLinkedHashSet();
    private final Set<Advancement> field_192760_h = Sets.<Advancement>newLinkedHashSet();
    private final Set<Advancement> field_192761_i = Sets.<Advancement>newLinkedHashSet();
    private EntityPlayerMP player;
    @Nullable
    private Advancement field_194221_k;
    private boolean field_192763_k = true;

    public PlayerAdvancements(MinecraftServer server, File p_i47422_2_, EntityPlayerMP player)
    {
        this.server = server;
        this.progressFile = p_i47422_2_;
        this.player = player;
        this.load();
    }

    public void setPlayer(EntityPlayerMP player)
    {
        this.player = player;
    }

    public void dispose()
    {
        for (ICriterionTrigger<?> icriteriontrigger : CriteriaTriggers.getAll())
        {
            icriteriontrigger.removeAllListeners(this);
        }
    }

    public void func_193766_b()
    {
        this.dispose();
        this.progress.clear();
        this.field_192759_g.clear();
        this.field_192760_h.clear();
        this.field_192761_i.clear();
        this.field_192763_k = true;
        this.field_194221_k = null;
        this.load();
    }

    private void func_192751_c()
    {
    	//TODO:
        //for (Advancement advancement : this.server.getAdvancementManager().getAdvancements())
        //{
            //this.func_193764_b(advancement);
        //}
    }

    private void func_192752_d()
    {
        List<Advancement> list = Lists.<Advancement>newArrayList();

        for (Entry<Advancement, AdvancementProgress> entry : this.progress.entrySet())
        {
            if (((AdvancementProgress)entry.getValue()).func_192105_a())
            {
                list.add(entry.getKey());
                this.field_192761_i.add(entry.getKey());
            }
        }

        for (Advancement advancement : list)
        {
            this.func_192742_b(advancement);
        }
    }

    private void func_192748_e()
    {
    	//TODO:
        //for (Advancement advancement : this.server.getAdvancementManager().getAdvancements())
        //{
            //if (advancement.getCriteria().isEmpty())
            //{
                //this.grantCriterion(advancement, "");
                //advancement.getRewards().apply(this.player);
            //}
        //}
    }

    private void load()
    {
        if (this.progressFile.isFile())
        {
            try
            {
                String s = Files.toString(this.progressFile, StandardCharsets.UTF_8);
                //TODO:
                Map<ResourceLocation, AdvancementProgress> map = (Map)/*JsonUtils.gsonDeserialize(GSON, s, MAP_TOKEN.getType())*/null;

                if(map == null){
                    throw new JsonParseException("Found null for advancements");
                }
                //Stream<Entry<ResourceLocation, AdvancementProgress>> stream = map.entrySet().stream().sorted(Comparator.comparing(Entry::getValue));

                //TODO:
                //for(Entry<ResourceLocation, AdvancementProgress> entry : stream.collect(Collectors.toList())){
                    //Advancement advancement = this.server.getAdvancementManager().getAdvancement(entry.getKey());

                    //if (advancement == null)
                    //{
                       // LOGGER.warn("Ignored advancement '" + entry.getKey() + "' in progress file " + this.progressFile + " - it doesn't exist anymore?");
                    //}
                    //else
                    //{
                        //this.func_192743_a(advancement, entry.getValue());
                    //}
                //}
            }
            catch (JsonParseException jsonparseexception)
            {
                LOGGER.error("Couldn't parse player advancements in " + this.progressFile, (Throwable)jsonparseexception);
            }
            catch (IOException ioexception)
            {
                LOGGER.error("Couldn't access player advancements in " + this.progressFile, (Throwable)ioexception);
            }
        }

        this.func_192748_e();
        this.func_192752_d();
        this.func_192751_c();
    }

    public void save()
    {
        Map<ResourceLocation, AdvancementProgress> map = Maps.<ResourceLocation, AdvancementProgress>newHashMap();

        for (Entry<Advancement, AdvancementProgress> entry : this.progress.entrySet())
        {
            AdvancementProgress advancementprogress = entry.getValue();

            if (advancementprogress.func_192108_b())
            {
                map.put(((Advancement)entry.getKey()).getId(), advancementprogress);
            }
        }

        if (this.progressFile.getParentFile() != null)
        {
            this.progressFile.getParentFile().mkdirs();
        }

        try
        {
            Files.write(GSON.toJson(map), this.progressFile, StandardCharsets.UTF_8);
        }
        catch (IOException ioexception)
        {
            LOGGER.error("Couldn't save player advancements to " + this.progressFile, (Throwable)ioexception);
        }
    }

    public boolean grantCriterion(Advancement p_192750_1_, String p_192750_2_)
    {
        boolean flag = false;
        AdvancementProgress advancementprogress = this.getProgress(p_192750_1_);
        boolean flag1 = advancementprogress.func_192105_a();

        if (advancementprogress.grantCriterion(p_192750_2_))
        {
            this.func_193765_c(p_192750_1_);
            this.field_192761_i.add(p_192750_1_);
            flag = true;

            if (!flag1 && advancementprogress.func_192105_a())
            {
                //p_192750_1_.getRewards().apply(this.player);

                if (p_192750_1_.getDisplay() != null && p_192750_1_.getDisplay().shouldAnnounceToChat() && this.player.worldObj.getGameRules().getGameRuleBooleanValue("announceAdvancements"))
                {
                	//TODO:
                    //this.server.getPlayerList().sendMessage(new ChatComponentTranslation("chat.type.advancement." + p_192750_1_.getDisplay().getFrame().getName(), new Object[] {this.player.getDisplayName(), p_192750_1_.getDisplayText()}));
                }
            }
        }

        if (advancementprogress.func_192105_a())
        {
            this.func_192742_b(p_192750_1_);
        }

        return flag;
    }

    public boolean revokeCriterion(Advancement p_192744_1_, String p_192744_2_)
    {
        boolean flag = false;
        AdvancementProgress advancementprogress = this.getProgress(p_192744_1_);

        if (advancementprogress.revokeCriterion(p_192744_2_))
        {
            this.func_193764_b(p_192744_1_);
            this.field_192761_i.add(p_192744_1_);
            flag = true;
        }

        if (!advancementprogress.func_192108_b())
        {
            this.func_192742_b(p_192744_1_);
        }

        return flag;
    }

    private void func_193764_b(Advancement p_193764_1_)
    {
        AdvancementProgress advancementprogress = this.getProgress(p_193764_1_);

        if (!advancementprogress.func_192105_a())
        {
            for (Entry<String, Criterion> entry : p_193764_1_.getCriteria().entrySet())
            {
                CriterionProgress criterionprogress = advancementprogress.getCriterionProgress(entry.getKey());

                if (criterionprogress != null && !criterionprogress.isObtained())
                {
                    ICriterionInstance icriterioninstance = ((Criterion)entry.getValue()).func_192143_a();

                    if (icriterioninstance != null)
                    {
                        ICriterionTrigger<ICriterionInstance> icriteriontrigger = CriteriaTriggers.<ICriterionInstance>get(icriterioninstance.getId());

                        if (icriteriontrigger != null)
                        {
                            icriteriontrigger.addListener(this, new ICriterionTrigger.Listener(icriterioninstance, p_193764_1_, entry.getKey()));
                        }
                    }
                }
            }
        }
    }

    private void func_193765_c(Advancement p_193765_1_)
    {
        AdvancementProgress advancementprogress = this.getProgress(p_193765_1_);

        for (Entry<String, Criterion> entry : p_193765_1_.getCriteria().entrySet())
        {
            CriterionProgress criterionprogress = advancementprogress.getCriterionProgress(entry.getKey());

            if (criterionprogress != null && (criterionprogress.isObtained() || advancementprogress.func_192105_a()))
            {
                ICriterionInstance icriterioninstance = ((Criterion)entry.getValue()).func_192143_a();

                if (icriterioninstance != null)
                {
                    ICriterionTrigger<ICriterionInstance> icriteriontrigger = CriteriaTriggers.<ICriterionInstance>get(icriterioninstance.getId());

                    if (icriteriontrigger != null)
                    {
                        icriteriontrigger.removeListener(this, new ICriterionTrigger.Listener(icriterioninstance, p_193765_1_, entry.getKey()));
                    }
                }
            }
        }
    }

    public void func_192741_b(EntityPlayerMP p_192741_1_)
    {
        if (!this.field_192760_h.isEmpty() || !this.field_192761_i.isEmpty())
        {
            Map<ResourceLocation, AdvancementProgress> map = Maps.<ResourceLocation, AdvancementProgress>newHashMap();
            Set<Advancement> set = Sets.<Advancement>newLinkedHashSet();
            Set<ResourceLocation> set1 = Sets.<ResourceLocation>newLinkedHashSet();

            for (Advancement advancement : this.field_192761_i)
            {
                if (this.field_192759_g.contains(advancement))
                {
                    map.put(advancement.getId(), this.progress.get(advancement));
                }
            }

            for (Advancement advancement1 : this.field_192760_h)
            {
                if (this.field_192759_g.contains(advancement1))
                {
                    set.add(advancement1);
                }
                else
                {
                    set1.add(advancement1.getId());
                }
            }

            if (!map.isEmpty() || !set.isEmpty() || !set1.isEmpty())
            {
            	//TODO:
                //p_192741_1_.connection.sendPacket(new SPacketAdvancementInfo(this.field_192763_k, set, set1, map));
                this.field_192760_h.clear();
                this.field_192761_i.clear();
            }
        }

        this.field_192763_k = false;
    }

    public void func_194220_a(@Nullable Advancement p_194220_1_)
    {
        Advancement advancement = this.field_194221_k;

        if (p_194220_1_ != null && p_194220_1_.getParent() == null && p_194220_1_.getDisplay() != null)
        {
            this.field_194221_k = p_194220_1_;
        }
        else
        {
            this.field_194221_k = null;
        }

        if (advancement != this.field_194221_k)
        {
        	//TODO:
            //this.player.connection.sendPacket(new SPacketSelectAdvancementsTab(this.field_194221_k == null ? null : this.field_194221_k.getId()));
        }
    }

    public AdvancementProgress getProgress(Advancement advancementIn)
    {
        AdvancementProgress advancementprogress = this.progress.get(advancementIn);

        if (advancementprogress == null)
        {
            advancementprogress = new AdvancementProgress();
            this.func_192743_a(advancementIn, advancementprogress);
        }

        return advancementprogress;
    }

    private void func_192743_a(Advancement p_192743_1_, AdvancementProgress p_192743_2_)
    {
        p_192743_2_.update(p_192743_1_.getCriteria(), p_192743_1_.getRequirements());
        this.progress.put(p_192743_1_, p_192743_2_);
    }

    private void func_192742_b(Advancement p_192742_1_)
    {
        boolean flag = this.func_192738_c(p_192742_1_);
        boolean flag1 = this.field_192759_g.contains(p_192742_1_);

        if (flag && !flag1)
        {
            this.field_192759_g.add(p_192742_1_);
            this.field_192760_h.add(p_192742_1_);

            if (this.progress.containsKey(p_192742_1_))
            {
                this.field_192761_i.add(p_192742_1_);
            }
        }
        else if (!flag && flag1)
        {
            this.field_192759_g.remove(p_192742_1_);
            this.field_192760_h.add(p_192742_1_);
        }

        if (flag != flag1 && p_192742_1_.getParent() != null)
        {
            this.func_192742_b(p_192742_1_.getParent());
        }

        for (Advancement advancement : p_192742_1_.getChildren())
        {
            this.func_192742_b(advancement);
        }
    }

    private boolean func_192738_c(Advancement p_192738_1_)
    {
        for (int i = 0; p_192738_1_ != null && i <= 2; ++i)
        {
            if (i == 0 && this.func_192746_d(p_192738_1_))
            {
                return true;
            }

            if (p_192738_1_.getDisplay() == null)
            {
                return false;
            }

            AdvancementProgress advancementprogress = this.getProgress(p_192738_1_);

            if (advancementprogress.func_192105_a())
            {
                return true;
            }

            if (p_192738_1_.getDisplay().isHidden())
            {
                return false;
            }

            p_192738_1_ = p_192738_1_.getParent();
        }

        return false;
    }

    private boolean func_192746_d(Advancement p_192746_1_)
    {
        AdvancementProgress advancementprogress = this.getProgress(p_192746_1_);

        if (advancementprogress.func_192105_a())
        {
            return true;
        }
        else
        {
            for (Advancement advancement : p_192746_1_.getChildren())
            {
                if (this.func_192746_d(advancement))
                {
                    return true;
                }
            }

            return false;
        }
    }
}