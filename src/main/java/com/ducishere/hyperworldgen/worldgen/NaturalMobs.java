package com.ducishere.hyperworldgen.worldgen.entities;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;

public class NaturalMobs {

    public static void register() {
        // Thêm zombie spawn ở tất cả biome rừng
        BiomeModifications.addSpawn(
            BiomeSelectors.categories(Biome.Category.FOREST),
            SpawnGroup.MONSTER,
            EntityType.ZOMBIE,
            100, // trọng số spawn
            2,   // min group size
            4    // max group size
        );

        // Thêm bò spawn ở plains
        BiomeModifications.addSpawn(
            BiomeSelectors.categories(Biome.Category.PLAINS),
            SpawnGroup.CREATURE,
            EntityType.COW,
            8,
            2,
            5
        );
    }
}
