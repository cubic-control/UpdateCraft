package com.cubic_control.UpdateCraft.Achievements;

import com.cubic_control.UpdateCraft.Items.MItems;

import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class MAchievements {
	public static Achievement postmortal;
	
	public static void createAchievements(){
		postmortal = new Achievement("achievement.postmortal", "postmortal", 10, -1, MItems.totem_of_undying, AchievementList.killEnemy).registerStat();
		
		AchievementList.achievementList.add(postmortal);
	}

}
