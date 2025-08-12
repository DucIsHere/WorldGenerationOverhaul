package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.biome.ModBiomeEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.*;

public class MagmaSpireBiome {

    public static Biome create() {
        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.MAGMA_SPIRE);
        gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.SULFUR_ORE);

        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 30, 1, 3));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 40, 2, 5));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 5, 1, 1));

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.MESA)
                .depth(2.0F)
                .scale(1.5F)
                .temperature(2.0F)
                .downfall(0.0F)
                .effects(ModBiomeEffects.VOLCANO_LAVA_RED)
                .generationSettings(gen.build())
                .spawnSettings(spawn.build())
                .build();
    }
}
