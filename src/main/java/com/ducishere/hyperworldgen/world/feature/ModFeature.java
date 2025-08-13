package com.ducishere.hyperworldgen.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, "modid");

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOW_LAYER_FEATURE =
            FEATURES.register("snow_layer_feature", 
                () -> new SnowLayerFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<PlacedFeature> SNOW_LAYER_PLACED =
            RegistryObject.create(Registries.PLACED_FEATURE, "modid");

    public static PlacedFeature placedSnowFeature() {
        return PlacementUtils.register("snow_layer_placed", SNOW_LAYER_FEATURE.get()
                .configured(NoneFeatureConfiguration.INSTANCE)
                .placed(RarityFilter.onAverageOnceEvery(2), // xuất hiện thường xuyên
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(),
                        BiomeFilter.biome()));
    }
}