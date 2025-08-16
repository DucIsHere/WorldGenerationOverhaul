package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ColdOceanBiome {
    public static Biome create() {
        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.creatureSpawnProbability(0.1f);
        DefaultBiomeFeatures.addOceanMobs(spawn, 10, 2, 4);

        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addOceanCarvers(gen);
        DefaultBiomeFeatures.addOceanStructures(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.3f)
                .downfall(0.9f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4020182)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8037887)
                        .build())
                .spawnSettings(spawn.build())
                .generationSettings(gen.build())
                .build();
    }
}
