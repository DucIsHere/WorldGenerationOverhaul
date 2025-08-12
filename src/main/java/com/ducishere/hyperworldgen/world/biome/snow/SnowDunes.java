package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class SnowDunes {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-1.0f)
            .downfall(0.9f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xE0F8FF)
                .skyColor(0xC0F0FF)
                .waterColor(0x66BBDD)
                .waterFogColor(0x5588AA)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.dunes"),
                    8000, 16000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
