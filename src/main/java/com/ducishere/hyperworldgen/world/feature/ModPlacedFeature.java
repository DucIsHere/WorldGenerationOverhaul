package com.ducishere.hyperworldgen.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.InSquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> SNOW_LAYER_8_PLACED = registerKey("snow_layer_8_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(SNOW_LAYER_8_PLACED, new PlacedFeature(
                context.getHolderOrThrow(ModConfiguredFeatures.SNOW_LAYER_8),
                List.of(
                        CountPlacementModifier.of(8), // số lần spawn
                        InSquarePlacementModifier.spread(),
                        HeightmapPlacementModifier.of()
                )
        ));

        
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("hyperworldgen", name));
    }
}
