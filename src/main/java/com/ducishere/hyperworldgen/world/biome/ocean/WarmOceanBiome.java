package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class WarmOceanBiome {
    public static Biome create() {
        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.creatureSpawnProbability(0.2f);
        DefaultBiomeFeatures.addOceanMobs(spawn, 20, 3, 6);

        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addOceanCarvers(gen);
        DefaultBiomeFeatures.addOceanStructures(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);
        DefaultBiomeFeatures.addWarmOceanFeatures(gen);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(1.0f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4445678)
                        .waterFogColor(270131)
                        .fogColor(12638463)
                        .skyColor(8843041)
                        .build())
                .spawnSettings(spawn.build())
                .generationSettings(gen.build())
                .build();
    }
}
