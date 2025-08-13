package com.ducishere.hyperworldgen.registry;

import com.ducishere.hyperworldgen.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class ModRegistries {
    public static void registerEffects() {
        Registry.register(Registries.MOB_EFFECT, new ResourceLocation("hyperworldgen", "frostbite"), ModEffects.FROSTBITE);
    }
}
