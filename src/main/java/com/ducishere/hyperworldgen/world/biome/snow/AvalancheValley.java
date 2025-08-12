package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class AvalancheValley {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.8f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.MOUNTAIN)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xAACCEE)
                .skyColor(0xBBDDEE)
                .waterColor(0x334455)
                .waterFogColor(0x223344)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.avalanche"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
