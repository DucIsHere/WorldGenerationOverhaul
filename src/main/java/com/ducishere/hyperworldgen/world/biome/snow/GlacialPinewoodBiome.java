package com.hypergenworld.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class GlacialPinewoodBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addSnowyMobs(spawnSettings); // cáo, sói

        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addTaigaTrees(genSettings); // spruce
        DefaultBiomeFeatures.addIcebergs(genSettings); // thêm ice patch
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-3.0F)
            .downfall(1.0F)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0x92B8CC)
                .skyColor(0x8BBEE2)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(genSettings.build())
            .build();
    }
}
