package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class DeepOceanBiome {
    public static Biome create() {
        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.creatureSpawnProbability(0.05f);
        DefaultBiomeFeatures.addOceanMobs(spawn, 5, 1, 2);

        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addOceanCarvers(gen);
        DefaultBiomeFeatures.addOceanStructures(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.4f)
                .downfall(0.9f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(1973026)
                        .waterFogColor(329011)
                        .fogColor(0x0A0A1A)
                        .skyColor(4210783)
                        .build())
                .spawnSettings(spawn.build())
                .generationSettings(gen.build())
                .build();
    }
}
