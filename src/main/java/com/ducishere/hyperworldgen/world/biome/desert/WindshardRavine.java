package com.hyperworldgen.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.ClimateSettings;

public class WindshardRavine {
    public static Biome create() {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xC9D8D3)
            .waterColor(0xE2D2B2)
            .waterFogColor(0xA3A199)
            .skyColor(0xD2E0E5)
            .build();

        BiomeGenerationSettings generationSettings = new BiomeGenerationSettings.Builder()
            .surfaceBuilder(() -> ConfiguredSurfaceBuilders.DESERT)
            .build();

        MobSpawnSettings mobSettings = new MobSpawnSettings.Builder().build();

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.NONE)
            .temperature(3.0F)
            .downfall(0.0F)
            .specialEffects(effects)
            .mobSpawnSettings(mobSettings)
            .generationSettings(generationSettings)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(BiomeCategory.DESERT)
            .climateSettings(new ClimateSettings(70, 190, 120)) // min, max, avg
            .build();
    }
}
