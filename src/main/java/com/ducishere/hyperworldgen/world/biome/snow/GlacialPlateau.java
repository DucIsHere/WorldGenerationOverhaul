package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class GlacialPlateau {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.5f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xBBDDEE)
                .skyColor(0xC8F4FF)
                .waterColor(0x2A94A0)
                .waterFogColor(0x1A6677)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.glacial"),
                    10000, 18000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
