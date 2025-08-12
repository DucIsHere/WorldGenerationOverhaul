package com.hypergenworld.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class IcyNeedlewoodBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addSnowyMobs(spawnSettings); // cáo, sói

        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addTaigaTrees(genSettings); // sau này thay bằng cây custom
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-2.5F)
            .downfall(0.85F)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xA1BBD1)
                .skyColor(0x90C8E0)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(genSettings.build())
            .build();
    }
}
