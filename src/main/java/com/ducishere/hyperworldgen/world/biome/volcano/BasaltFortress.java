package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.biome.ModBiomeEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.*;

public class BasaltFortressBiome {

    public static Biome create() {
        GenerationSettings.Builder gen = new GenerationSettings.Builder();
        gen.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.BASALT_FORTRESS);
        gen.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.NETHERITE_ORE);

        SpawnSettings.Builder spawn = new SpawnSettings.Builder();
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 15, 1, 2));
        spawn.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 25, 1, 3));

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.NETHER)
                .depth(1.0F)
                .scale(0.8F)
                .temperature(1.8F)
                .downfall(0.0F)
                .effects(ModBiomeEffects.VOLCANO_ASH_GRAY)
                .generationSettings(gen.build())
                .spawnSettings(spawn.build())
                .build();
    }
}
