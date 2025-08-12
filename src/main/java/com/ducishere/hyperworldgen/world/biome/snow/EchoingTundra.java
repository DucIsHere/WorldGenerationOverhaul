package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class EchoingTundra {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.88f)
            .downfall(0.75f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.PLAINS)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xE4F9FF)
                .skyColor(0xCFF0FF)
                .waterColor(0x60BFE1)
                .waterFogColor(0x4C90A7)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.echoing_tundra"),
                    7000, 14000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
