package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class IcySpires {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.8f)
            .downfall(0.7f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xC0DDFE)
                .skyColor(0xB5DDFE)
                .waterColor(0x3A5F80)
                .waterFogColor(0x284460)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.spires"),
                    7000, 16000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
