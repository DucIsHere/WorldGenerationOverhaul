package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;

public class ColdOceanBiome {
    public static Biome create() {
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
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();
    }
}
