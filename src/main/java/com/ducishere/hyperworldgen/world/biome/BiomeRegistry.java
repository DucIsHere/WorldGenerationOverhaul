package com.ducishere.hyperworldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BiomeRegistry {
    public static final ResourceKey<Biome> SKY_VALLEY = createKey("sky_valley");
    public static final ResourceKey<Biome> DESERT_DUNE = createKey("desert_dune");
    public static final ResourceKey<Biome> FROZEN_PEAK = createKey("frozen_peak");

    private static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation("hyperworldgen", name));
    }
}
