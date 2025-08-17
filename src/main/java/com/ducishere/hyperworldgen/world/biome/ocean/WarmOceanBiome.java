package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;

public class WarmOceanBiome {
    public static Biome create() {
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
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();
    }
}
