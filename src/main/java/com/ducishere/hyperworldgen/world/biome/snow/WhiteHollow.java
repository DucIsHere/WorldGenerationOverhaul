package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class WhiteHollow {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.9f)
            .downfall(0.7f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xE6FBFF)
                .skyColor(0xD7F2FF)
                .waterColor(0x5CC7E2)
                .waterFogColor(0x4398AB)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.white_hollow"),
                    8000, 16000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
