package com.ducishere.hyperworldgen.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import your.mod.utils.SnowUtils;

import java.util.Random;

public class SnowLayerGenerator {

    /**
     * Tạo tuyết 5–8 layer ở các biome lạnh sâu.
     */
    public static void generateSnowLayers(WorldAccess world, Chunk chunk, ChunkGenerator generator) {
        Random random = new Random();

        chunk.getBlockPositions().forEach(pos -> {
            Biome biome = world.getBiome(pos).value();
            float temp = biome.getTemperature(pos);

            // Chỉ gen tuyết ở biome có nhiệt độ < -4.0f (hardcore snow)
            if (temp < -3.0f && world.isSkyVisible(pos)) {
                int layers = 5 + random.nextInt(4); // 5–8 layer
                SnowUtils.setSnowLayer(world.toServerWorld(), pos.up(), layers);
            }
        });
    }
}
