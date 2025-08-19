package com.ducishere.hyperworldgen.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGen {
    public static void generateWorldGen() {
        addOres();
    }

    private static void addOres() {
        // Steel Abyss Ore -> Ocean Abyss biome (tag riêng)
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(new Identifier("hyperworldgen:ocean_abyss")), // biome m tạo
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.STEEL_ABYSS_ORE_PLACED_KEY
        );
    }
}
