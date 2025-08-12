package com.hypergenworld.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class SilentSnowforestBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // Không thêm passive mob
        DefaultBiomeFeatures.addSnowyMobs(spawnSettings); // Thêm cáo tuyết, sói

        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addTaigaTrees(genSettings); // cây spruce
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-3.5F)
            .downfall(0.8F)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xAABBDD)
                .skyColor(0x99CCFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE) // chỉ có âm thanh động
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(genSettings.build())
            .build();
    }
}
