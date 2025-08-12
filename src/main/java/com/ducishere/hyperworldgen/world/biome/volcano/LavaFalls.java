package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.biome.ModBiomeEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.*;

public class LavaFallsBiome {

    public static Biome create() {
        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.LAVA_FALLS);
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.BASALT_SPIKES);

        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 25, 1, 3));
        spawn.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 15, 1, 2));

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.EXTREME_HILLS)
                .depth(1.5F)
                .scale(1.2F)
                .temperature(2.0F)
                .downfall(0.0F)
                .effects(ModBiomeEffects.VOLCANO_LAVA_RED)
                .generationSettings(gen.build())
                .spawnSettings(spawn.build())
                .build();
    }
}
