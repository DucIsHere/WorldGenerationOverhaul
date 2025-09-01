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
    public static final RegistryKey<PlacedFeature> WILD_RICE_PLACED = registerKey("wild_rice_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(SNOW_LAYER_8_PLACED, new PlacedFeature(
                context.getHolderOrThrow(ModConfiguredFeatures.SNOW_LAYER_8),
                List.of(
                        CountPlacementModifier.of(8), // số lần spawn
                        InSquarePlacementModifier.spread(),
                        HeightmapPlacementModifier.of()
                )
        ));

        context.register(WILD_RICE_PLACED, new PlacedFeature(
                context.getHolderOrThrow(ModConfiguredFeatures.WILD_RICE),
                List.of(
                        CountPlacementModifier.of(10),
                        InSquarePlacementModifier.spread(),
                        HeightmapPlacementModifier.of()
                        // Nếu có BiomeFilter.biome() thì thêm vào, còn không có thì có thể bỏ qua!
                )
        ));
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("hyperworldgen", name));
    }
}
