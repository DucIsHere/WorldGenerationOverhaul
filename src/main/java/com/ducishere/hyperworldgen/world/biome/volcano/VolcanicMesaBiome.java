package com.ducishere.hyperworldgen.common.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class VolcanicMesaBiome {
    public static Biome create() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 5, 1, 2));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 2, 1, 2));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 3));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();
        genBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.BADLANDS);

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .temperature(2.0F)
            .downfall(0.0F)
            .effects(new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(7907327)
                .build())
            .spawnSettings(spawnBuilder.build())
            .generationSettings(genBuilder.build())
            .build();
    }
}
