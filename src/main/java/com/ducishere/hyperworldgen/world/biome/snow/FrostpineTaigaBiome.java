package com.hypergenworld.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.DefaultVegetationFeatures;

public class FrostpineTaigaBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addSnowyMobs(spawnSettings); // Cáo tuyết, thỏ, sói

        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
        DefaultBiomeFeatures.addTaigaTrees(genSettings); // Spruce thường
        DefaultBiomeFeatures.addExtraTaigaTrees(genSettings); // Spruce cao

        // Thêm tuyết dày 3 block bằng surface rules → phần worldgen config xử lý riêng

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-2.0F)
            .downfall(0.9F)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xC8D8F0)
                .skyColor(0xAADDEE)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(genSettings.build())
            .build();
    }
}
