package com.ducishere.hyperworldgen.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {

    public static void registerFeatures() {
        Registry.register(Registries.CONFIGURED_FEATURE,
                new Identifier("hyperworldgen", "hyper_tree"),
                Feature.TREE.configure(new Feature.TreeFeatureConfig.Builder(
                        /* trunkProvider */ null,
                        /* foliageProvider */ null,
                        /* foliagePlacer */ null,
                        /* trunkPlacer */ null
                ).build()));
    }
}
