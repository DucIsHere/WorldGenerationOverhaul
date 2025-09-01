package com.ducishere.hyperworldgen.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguaredFeature<?, ?>> WILD_TEA_BUSH =
            registerKey("wild_tea_bush")(

    // key cho wild rice
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_RICE =
            registerKey("wild_rice");

    // key cho snow layer 8 (ví dụ)
    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOW_LAYER_8 =
            registerKey("snow_layer_8");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // Wild Rice (dùng farmersdelight:wild_rice block)
        context.register(WILD_RICE, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        32, // tries per chunk
                        6,  // spread xz
                        2,  // spread y
                        () -> Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                                BlockStateProvider.of(
                                        net.minecraft.block.BlocksRegistry.WILD_RICE // nếu Farmers Delight đăng ký block này
                                )
                        ))
                )
        ));

        context.register(WILD_TEA_BUSH, new ConfiguaredFeature<>(
                Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                    32,
                    6,
                    2,
                    () -> Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
                            BlockStateProvider.of(
                                    net.minecraft.block.BlockRegistry.WILD_TEA_BUSH
                            )
                    ))
                )
        ));

        // Snow Layer 8 (ví dụ)
        context.register(SNOW_LAYER_8, new ConfiguredFeature<>(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SNOW))
        ));
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier("hyperworldgen", name));
    }
}
