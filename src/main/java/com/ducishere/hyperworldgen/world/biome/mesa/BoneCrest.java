package com.ducishere.hyperworldgen.common.biome.mesa;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class BonecrestBiome {

    public static Biome createBiome() {
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xA57E4A)          // Màu sương mù vàng đất
            .waterColor(0x705030)        // Nước màu nâu đất
            .waterFogColor(0x503020)
            .build();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        // Thêm ores terracotta, đất đỏ, và cây dead bush
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.TERRACOTTA_ORE);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEAD_BUSH);

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        // Spawn mob nguy hiểm
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 90, 2, 4));  // Husks desert buffed
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 60, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 70, 1, 3));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(1.0F)
            .scale(0.7F)
            .temperature(1.7F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
