package com.ducishere.hyperworldgen;

import com.ducishere.hyperworldgen.block.ModBlocks;
import com.ducishere.hyperworldgen.effect.ModEffects;
import com.ducishere.hyperworldgen.item.ModItems;
import com.ducishere.hyperworldgen.world.feature.ModConfiguredFeatures;
import com.ducishere.hyperworldgen.world.feature.ModPlacedFeatures;
import com.ducishere.hyperworldgen.handler.FrostbiteHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import com.ducishere.hyperworldgen.environment.ColdEnvironment;

import net.fabricmc.api.ModInitializer;

public class HyperWorldGen implements ModInitializer {

    public static final String MOD_ID = "hyperworldgen";

    @Override
    public void onInitialize() {
        System.out.println("Initializing HyperWorldGen...");
        
        Registry.register(Registries.CHUNK_GENERATOR, 
        new Identifier("hyperworldgen", "hyper_chunk_generator"), 
        HyperChunkGenerator.CODEC);



        ModConfiguredFeatures.bootstrap();
        ModPlacedFeatures.bootstrap();
        
        HyperNoiseRegistry.register();
        HyperPipelineRegistry.register();
        ModBiomes.registerBiomes();

        // Nếu cần truyền registry, truyền đúng args
        // ModConfiguredFeatures.bootstrap(registry);
        // ModPlacedFeatures.bootstrap(registry);

        ModWorldGen.generateWorldGen();


        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if(world instanceof ServerWorld serverWorld){
                ColdEnvironment.tick(serverWorld);
            }
        });
    }
}
