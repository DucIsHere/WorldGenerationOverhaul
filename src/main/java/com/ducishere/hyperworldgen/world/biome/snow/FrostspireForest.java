package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class FrostspireForest {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.9f)
            .downfall(0.8f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.FOREST)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xCFEFFF)
                .skyColor(0xB3DEFF)
                .waterColor(0x46A3CE)
                .waterFogColor(0x347D99)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.frostspire_forest"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
