package com.ducishere.hyperworldgen.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect FROSTBITE = register("frostbite",
            new FrostbiteEffect(StatusEffectCategory.HARMFUL, 0xAEEFFF)); // màu xanh nhạt

    public static void registerModEffects() {
        System.out.println("Đang đăng ký ModEffects cho " + com.ducishere.hyperworldgen.HyperWorldGen.MOD_ID);
    }

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(com.ducishere.hyperworldgen.HyperWorldGen.MOD_ID, name), effect);
    }
}
