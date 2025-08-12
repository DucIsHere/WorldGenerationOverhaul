package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class SnowyExpanse {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.6f)
            .downfall(0.9f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xEAF8FF)
                .skyColor(0xD4F0FF)
                .waterColor(0x5BAFD4)
                .waterFogColor(0x3F7E99)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.snowexpanse"),
                    10000, 20000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
