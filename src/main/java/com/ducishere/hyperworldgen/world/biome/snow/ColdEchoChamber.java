package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class ColdEchoChamber {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.95f)
            .downfall(0.6f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.UNDERGROUND)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xBBCDEE)
                .skyColor(0xAACCFF)
                .waterColor(0x1A2B3C)
                .waterFogColor(0x101820)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.echo"),
                    10000, 20000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
