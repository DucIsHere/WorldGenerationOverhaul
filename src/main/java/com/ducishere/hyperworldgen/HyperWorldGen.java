package com.ducishere.hyperworldgen;

import com.ducishere.hyperworldgen.block.ModBlocks;
import com.ducishere.hyperworldgen.effect.ModEffects;
import com.ducishere.hyperworldgen.item.ModItems;
import com.ducishere.hyperworldgen.world.feature.ModConfiguredFeatures;
import com.ducishere.hyperworldgen.world.feature.ModPlacedFeatures;
import com.ducishere.hyperworldgen.handler.FrostbiteHandler;

import net.fabricmc.api.ModInitializer;

public class HyperWorldGen implements ModInitializer {

    public static final String MOD_ID = "hyperworldgen";

    @Override
    public void onInitialize() {
        System.out.println("Initializing HyperWorldGen...");

        // 1. Register Blocks
        ModBlocks.registerBlocks();

        // 2. Register Items
        ModItems.registerItems();

        // 3. Register Effects
        ModEffects.registerModEffects();

        // 4. Register Features
        ModConfiguredFeatures.bootstrap();
        ModPlacedFeatures.bootstrap();

        // 5. Register Biome-specific structures or features
        // Example: add SnowLayer feature
        // BiomeRegistry.addFeatureToBiome(Biomes.ICE_PLAINS, ModPlacedFeatures.SNOW_LAYER_8_PLACED);

        // 6. Register handlers
        FrostbiteHandler.register();
    }
}
