package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.biome.ModBiomeEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.*;

public class ObsidianRidgeBiome {

    public static Biome create() {
        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.OBSIDIAN_SPIRES);
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.MAGMA_CRACK);

        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 20, 1, 2));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 25, 1, 3));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 15, 1, 2));

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.MOUNTAIN)
                .depth(1.3F)
                .scale(1.4F)
                .temperature(2.0F)
                .downfall(0.0F)
                .effects(ModBiomeEffects.VOLCANO_LAVA_RED)
                .generationSettings(gen.build())
                .spawnSettings(spawn.build())
                .build();
    }
}
