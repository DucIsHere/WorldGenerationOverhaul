package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class TundraWhisper {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.85f)
            .downfall(0.65f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.TAIGA)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xDAF7FF)
                .skyColor(0xC9EBFF)
                .waterColor(0x62C2DE)
                .waterFogColor(0x4C91A5)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.tundra_whisper"),
                    7000, 14000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
