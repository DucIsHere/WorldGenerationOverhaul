package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class SnowCanyon {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-1.0f)
            .downfall(0.75f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.EXTREME_HILLS)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xD0F4FF)
                .skyColor(0xB4E4FF)
                .waterColor(0x58B3D7)
                .waterFogColor(0x3F8DA1)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.snow_canyon"),
                    7000, 14000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
