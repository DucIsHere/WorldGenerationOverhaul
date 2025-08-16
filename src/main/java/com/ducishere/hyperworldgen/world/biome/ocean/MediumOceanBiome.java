package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class MediumOceanBiome {
    public static Biome create() {
        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.creatureSpawnProbability(0.15f);
        DefaultBiomeFeatures.addOceanMobs(spawn, 15, 2, 5);

        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addOceanCarvers(gen);
        DefaultBiomeFeatures.addOceanStructures(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.7f)
                .downfall(0.7f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(3694022)
                        .waterFogColor(270131)
                        .fogColor(12638463)
                        .skyColor(8364543)
                        .build())
                .spawnSettings(spawn.build())
                .generationSettings(gen.build())
                .build();
    }
}
