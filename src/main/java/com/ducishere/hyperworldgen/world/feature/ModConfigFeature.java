package com.ducishere.hyperworldgen.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOW_LAYER_8 = registerKey("snow_layer_8");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Feature tuyết dày
        context.register(SNOW_LAYER_8, new ConfiguredFeature<>(
                Feature.BLOCK_COLUMN,
                new SingleStateFeatureConfig(Blocks.SNOW_BLOCK.getDefaultState())
        ));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier("hyperworldgen", name));
    }
}