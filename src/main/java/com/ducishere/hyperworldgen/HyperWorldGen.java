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

        
        ModBlocks.registerBlocks();

        
        ModItems.registerItems();

        
        ModEffects.registerModEffects();

        
        ModConfiguredFeatures.bootstrap();
        ModPlacedFeatures.bootstrap();

        ModBiomes.registerBiomes();
        FrostbiteHandler.register();
    }
}
