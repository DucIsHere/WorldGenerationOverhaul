package com.ducishere.hyperworldgen.thirst;

import glitchcore.event.TickEvent;

public class ThirstClientTickHandler
{
    private static final int IN_WORLD_DRINK_COOLDOWN = 3 * 20;
    private static int inWorldDrinkTimer = 0;

    public static void onClientTick(TickEvent.Client event)
    {
        if (inWorldDrinkTimer > 0)
            inWorldDrinkTimer--;
    }

    public static boolean canHandDrink()
    {
        return inWorldDrinkTimer <= 0;
    }

    public static void resetHandDrinkCooldown()
    {
        inWorldDrinkTimer = IN_WORLD_DRINK_COOLDOWN;
    }
}
