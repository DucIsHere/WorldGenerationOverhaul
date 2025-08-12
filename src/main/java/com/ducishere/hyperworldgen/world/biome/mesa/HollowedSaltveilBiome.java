package com.ducishere.hyperworldgen.common.biome.mesa;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class HollowedSaltveilBiome {

    public static Biome createBiome() {
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xE0E6E6)          // Màu sương mù trắng xám
            .waterColor(0xA0C0C0)        // Nước màu xanh nhạt
            .waterFogColor(0x708080)     // Sương nước nhẹ
            .build();

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // TODO: Thêm ore muối khoáng, cây cỏ hiếm

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        // Spawn mob hiếm và khó chịu
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 80, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SILVERFISH, 30, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 70, 1, 3));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.8F)
            .scale(0.6F)
            .temperature(1.4F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}