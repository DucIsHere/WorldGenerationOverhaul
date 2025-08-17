package com.ducishere.hyperworldgen.thirst;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.init.ModConfig;

public class ThirstHooksClient
{
    public static void onAiStepSetSprinting(LocalPlayer player, boolean sprinting)
    {
        if (sprinting && !canSprintWithThirst(player))
            sprinting = false;

        player.setSprinting(sprinting);
    }

    private static boolean canSprintWithThirst(LocalPlayer player)
    {
        return !ModConfig.thirst.thirstPreventSprint || ThirstHelper.getThirst(player).getThirst() > 6 || player.getAbilities().mayfly;
    }
}
