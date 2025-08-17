package com.ducishere.hyperworldgen.environment;

import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.World;

public class ColdEnvironment {

    public static void tick(ServerWorld world){
        if(world.isClient) return;

        // Scan chunks loaded
        world.iterateEntities().forEach(entity -> {
            BlockPos playerPos = entity.getBlockPos();
            applyColdEffect(world, playerPos);
        });
    }

    private static void applyColdEffect(ServerWorld world, BlockPos center){
        int radius = 10; // radius xung quanh player
        for(int x = -radius; x <= radius; x++){
            for(int y = -2; y <= 5; y++){
                for(int z = -radius; z <= radius; z++){
                    BlockPos pos = center.add(x,y,z);
                    var biome = world.getBiome(pos).value();
                    
                    if((biome.getKey().get() == BiomeKeys.SNOWY_TUNDRA
                        || biome.getKey().get() == BiomeKeys.ICE_SPIKES) 
                        && getTemperature(pos) <= -6.0f){

                        var state = world.getBlockState(pos);

                        // Campfire tự tắt
                        if(state.isOf(Blocks.CAMPFIRE) || state.isOf(Blocks.SOUL_CAMPFIRE)){
                            world.setBlockState(pos, state.with(CampfireBlock.LIT, false), 3);
                        }

                        // Furnace tự tắt
                        if(state.isOf(Blocks.FURNACE)){
                            world.setBlockState(pos, state.with(FurnaceBlock.LIT, false), 3);
                        }

                        // Lava hoá Obsidian
                        if(state.isOf(Blocks.LAVA)){
                            world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }

    // Example: dummy temperature check
    private static float getTemperature(BlockPos pos){
        // Nếu m có system temperature player -> lấy ở đây
        // Tạm: giả lập biome temp
        return -6.0f; 
    }
}
