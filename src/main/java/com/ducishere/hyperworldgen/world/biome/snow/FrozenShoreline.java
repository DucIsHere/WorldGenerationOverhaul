package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class FrozenShoreline {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.7f) // Gần biển nên lạnh vừa
            .downfall(0.9f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.BEACH)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xE8FBFF)
                .skyColor(0xD6F1FF)
                .waterColor(0x57C0E3)
                .waterFogColor(0x4395AC)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.frozen_shoreline"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
