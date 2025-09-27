package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.biome.Biome;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBiomes {
    private static final Map<String, Biome> BIOMES = new LinkedHashMap<>();

    // Thêm biome (Java factory)
    public static void addBiome(String id, Biome biome) {
        BIOMES.put(id, biome);
    }

    // Load tất cả biome
    public static void registerBiomes() {
        BIOMES.forEach((id, biome) ->
            Registry.register(Registries.BIOME, new Identifier("hyperworldgen", id), biome)
        );

        System.out.println("Registered " + BIOMES.size() + " biomes!");
    }

    // Lấy biome (tùy chọn)
    public static Biome get(String id) {
        return BIOMES.get(id);
    }
}
