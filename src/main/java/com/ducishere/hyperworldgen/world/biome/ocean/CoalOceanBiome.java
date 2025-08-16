package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class CoalOceanBiome {
    public static Biome create() {
        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.creatureSpawnProbability(0.1f);
        DefaultBiomeFeatures.addOceanMobs(spawn, 8, 1, 3);

        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addOceanCarvers(gen);
        DefaultBiomeFeatures.addOceanStructures(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.5f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(1118481) // màu than
                        .waterFogColor(1973026)
                        .fogColor(0x202020)
                        .skyColor(5000268)
                        .build())
                .spawnSettings(spawn.build())
                .generationSettings(gen.build())
                .build();
    }
}
