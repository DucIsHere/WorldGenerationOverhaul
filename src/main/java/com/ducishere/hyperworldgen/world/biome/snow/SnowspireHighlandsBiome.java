package com.hypergenworld.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class SnowspireHighlandsBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addSnowyMobs(spawnSettings); // cáo, sói

        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addTaigaTrees(genSettings); // cây spruce
        DefaultBiomeFeatures.addIcebergs(genSettings); // để fake snow spike
        DefaultBiomeFeatures.addFrozenTopLayer(genSettings);

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-3.2F)
            .downfall(0.9F)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0x91B7CC)
                .skyColor(0x88C0E5)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(genSettings.build())
            .build();
    }
}
