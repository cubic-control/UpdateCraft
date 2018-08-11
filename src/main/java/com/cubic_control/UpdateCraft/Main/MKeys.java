package com.cubic_control.UpdateCraft.Main;

import org.lwjgl.input.Keyboard;

import com.cubic_control.cubic_core.Utils.CubicUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

public class MKeys {
	public static KeyBinding advancements;
	
	@SideOnly(Side.CLIENT)
	public static void createKeys(){
		advancements = CubicUtils.addKeyBinding("Advancements", Keyboard.KEY_L, "Miscellaneous");
	}

}
