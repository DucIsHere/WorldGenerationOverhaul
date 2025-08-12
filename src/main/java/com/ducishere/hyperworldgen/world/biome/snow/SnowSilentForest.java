package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class SnowSilentForest {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-1.5f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.TAIGA)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xDDFFEE)
                .skyColor(0xBBFFEE)
                .waterColor(0x4D8F99)
                .waterFogColor(0x376C75)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.silentforest"),
                    9000, 20000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
