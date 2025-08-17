package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;

public class MediumOceanBiome {
    public static Biome create() {
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
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();
    }
}
