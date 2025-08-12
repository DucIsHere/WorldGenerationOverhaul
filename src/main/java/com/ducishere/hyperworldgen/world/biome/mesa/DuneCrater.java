package com.ducishere.hyperworldgen.common.biome.mesa;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class DuneCraterBiome {

    public static Biome createBiome() {
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xD9C27C)          // Màu sương vàng cát
            .waterColor(0xC2B280)        // Nước màu vàng nhạt
            .waterFogColor(0x9E8B60)
            .build();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // TODO: Thêm ore, cactus, dead bush, đụn cát (sand dunes)

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        // Spawn mob desert hardcore
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 100, 2, 5));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 50, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 60, 1, 4));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(2.0F)
            .scale(1.0F)
            .temperature(1.6F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}