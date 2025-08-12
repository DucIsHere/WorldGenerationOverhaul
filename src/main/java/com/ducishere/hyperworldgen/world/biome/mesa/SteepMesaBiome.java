package com.ducishere.hyperworldgen.common.biome.mesa;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class BurntMesaBiome {

    public static Biome createBiome() {
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0x4B2E0F)          // Màu sương mù khói than
            .waterColor(0x3A1F07)        // Nước màu đen cháy
            .waterFogColor(0x1C0E03)
            .build();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // TODO: Thêm ore than, bụi than, cây khô cháy

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        // Spawn mob nguy hiểm, có thể thêm mob độc hại hoặc buff
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 90, 1, 4));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 70, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 50, 1, 2));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(1.1F)
            .scale(0.75F)
            .temperature(1.9F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}