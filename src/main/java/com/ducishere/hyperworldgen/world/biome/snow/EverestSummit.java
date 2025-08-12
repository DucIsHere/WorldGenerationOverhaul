package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.sounds.Music;
import net.minecraft.resources.ResourceLocation;

public class EverestSummit {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-6.0)
            .downfall(1.0f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.EXTREME_HILLS)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xC9D9FF)
                .skyColor(0xBFDFFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.everest"),
                    12000, 24000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
