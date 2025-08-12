package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.biome.ModBiomeEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.*;

public class InfernoPlateauBiome {

    public static Biome create() {
        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.LAVA_PITS);
        gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.SULFUR_ORE);

        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 35, 1, 4));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 10, 1, 1));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 25, 1, 4));

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.PLAINS)
                .depth(0.5F)
                .scale(0.2F)
                .temperature(2.0F)
                .downfall(0.0F)
                .effects(ModBiomeEffects.VOLCANO_LAVA_RED)
                .generationSettings(gen.build())
                .spawnSettings(spawn.build())
                .build();
    }
}
