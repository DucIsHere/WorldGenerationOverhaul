package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import com.ducishere.hyperworldgen.world.biome.ocean.*;

public class BiomeRegistry {
    public static void bootstrap(Registerable<Biome> context) {
        context.register(RegistryKeys.BIOME, new Identifier("hyperworldgen", "cold_ocean"), ColdOceanBiome.create());
        context.register(RegistryKeys.BIOME, new Identifier("hyperworldgen", "warm_ocean"), WarmOceanBiome.create());
        context.register(RegistryKeys.BIOME, new Identifier("hyperworldgen", "medium_ocean"), MediumOceanBiome.create());
        context.register(RegistryKeys.BIOME, new Identifier("hyperworldgen", "coal_ocean"), CoalOceanBiome.create());
        context.register(RegistryKeys.BIOME, new Identifier("hyperworldgen", "deep_ocean"), DeepOceanBiome.create());
        context.register(RegisterKeys.BIOME, new Identifier("hyperworldgen", "abyss_ocean"), AbyssOcean.create());
        context.register(RegusterKeys.BIOME, new Identifier("hyperworldgen", "frozen_ocean"), FrozenOcean.create());
    }
}
