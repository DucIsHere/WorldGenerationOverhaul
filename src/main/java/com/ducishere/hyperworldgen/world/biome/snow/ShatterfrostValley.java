package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class ShatterfrostValley {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-1.0f)
            .downfall(0.8f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.EXTREME_HILLS)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xDBF9FF)
                .skyColor(0xC5EDFF)
                .waterColor(0x4AAED9)
                .waterFogColor(0x3C89A3)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.shatterfrost_valley"),
                    7000, 14000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
