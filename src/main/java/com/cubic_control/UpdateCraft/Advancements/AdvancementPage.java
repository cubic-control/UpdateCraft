package com.cubic_control.UpdateCraft.Advancements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdvancementPage{
    private String name;
    private LinkedList<Advancement> advancement;

    public AdvancementPage(String name, Advancement... advancement) {
        this.name = name;
        this.advancement = new LinkedList<Advancement>(Arrays.asList(advancement));
    }

    public String getName() {
        return name;
    }

    public List<Advancement> getAdvancement() {
        return advancement;
    }
    
    private static LinkedList<AdvancementPage> advancementPages = new LinkedList<AdvancementPage>();
    
    /**
     * Registers an advancement page.
     * @param page The page.
     */
    public static void registerAdvancementPage(AdvancementPage page) {
        if(getAdvancementPage(page.getName()) != null){
            throw new RuntimeException("Duplicate advancement page name \"" + page.getName() + "\"!");
        }
        advancementPages.add(page);
    }
    
    /**
     * Will return an advancement page by its index on the list.
     * @param index The page's index.
     * @return the advancement page corresponding to the index or null if invalid index
     */
    public static AdvancementPage getAdvancementPage(int index) {
        return advancementPages.get(index);
    }
    
    /**
     * Will return an advancement page by its name.
     * @param name The page's name.
     * @return the advancement page with the given name or null if no such page
     */
    public static AdvancementPage getAdvancementPage(String name) {
        for(AdvancementPage page : advancementPages){
            if(page.getName().equals(name)){
                return page;
            }
        }
        return null;
    }
    
    /**
     * Will return the list of advancement pages.
     * @return the list's size
     */
    public static Set<AdvancementPage> getAdvancementPages() {
        return new HashSet<AdvancementPage>(advancementPages);
    }
    
    /**
     * Will return whether an advancement is in any page or not.
     * @param advancement The advancement.
     */
    public static boolean isAdvancementInPages(Advancement advancement) {
        for(AdvancementPage page : advancementPages){
            if(page.getAdvancement().contains(advancement)) {
                return true;
            }
        }
        return false;
    }
    
    public static String getTitle(int index) {
        return index == -1 ? "Minecraft" : getAdvancementPage(index).getName();
    }
    
}