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

@Override
public double getHeight(double x, double y, double z, long seed) {
    return HyperPipelineRegistry.get("desert")
            .apply(new HyperPipelineRegistry.PipelineContext(x, y, z, seed));
}


        System.out.println("Biomes registered");
    }
}
