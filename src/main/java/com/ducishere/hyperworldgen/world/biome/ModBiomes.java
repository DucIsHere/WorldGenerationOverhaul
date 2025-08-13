package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static Biome BLIZZARD_HELL;

    public static void registerBiomes() {
        BLIZZARD_HELL = Registry.register(
                Registries.BIOME,
                new Identifier("hyperworldgen", "blizzard_hell"),
                BlizzardHellBiome.createBiome()
        );

        System.out.println("Biomes registered");
    }
}
