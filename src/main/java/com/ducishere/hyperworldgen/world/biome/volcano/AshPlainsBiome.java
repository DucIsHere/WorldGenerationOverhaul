package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.feature.ModConfiguredFeatures;
import com.ducishere.hyperworldgen.common.feature.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class AshPlainsBiome {

    public static Biome create() {
        // Hiệu ứng môi trường
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0x4A3C35)     // sương tro
            .waterColor(0x2B1A14)   // nước đục
            .waterFogColor(0x1E120C)
            .skyColor(0x5B463C)
            .build();

        // Thiết lập generation
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // Thêm tro bụi, basalt, và lava rãnh
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.ASH_LAYER);
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.MAGMA_CRACK);
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.MINI_VOLCANO);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.SULFUR_ORE);

        // Spawn mob
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 25, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 30, 1, 4));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 5, 1, 2)); // tro lạnh
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 20, 1, 4));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.1F)      // gần như đồng bằng
            .scale(0.05F)     // phẳng nhưng có điểm cao là volcano mini
            .temperature(1.9F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}
