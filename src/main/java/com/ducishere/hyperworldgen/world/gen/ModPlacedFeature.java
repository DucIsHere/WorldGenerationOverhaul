package com.ducishere.hyperworldgen.world.gen;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> STEEL_ABYSS_ORE_PLACED_KEY =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("hyperworldgen", "steel_abyss_ore"));

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(STEEL_ABYSS_ORE_PLACED_KEY,
                new PlacedFeature(
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
                                .getOrThrow(ModConfiguredFeatures.STEEL_ABYSS_ORE_KEY),
                        List.of(
                                RarityFilterPlacementModifier.of(10), // chance 10%
                                SquarePlacementModifier.of(),
                                HeightRangePlacementModifier.uniform(
                                        YOffset.fixed(-2500), YOffset.fixed(-1750)
                                ),
                                BiomePlacementModifier.of()
                        )
                ));
    }
}
