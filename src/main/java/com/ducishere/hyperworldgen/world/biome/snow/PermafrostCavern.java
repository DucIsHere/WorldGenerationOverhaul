package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class PermafrostCavern {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-1.1f) // Lạnh vừa sâu, kiểu dưới lòng đất
            .downfall(0.7f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.UNDERGROUND)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xB9E8FF)
                .skyColor(0xA8DAFF)
                .waterColor(0x379DCB)
                .waterFogColor(0x2C7E99)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.permafrost_cavern"),
                    8000, 16000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
