package com.ducishere.hyperworldgen.world.gen;

import com.ducishere.hyperworldgen.world.biome.noise.NoiseBackendManager;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

public class MountainGenerator {

    public static void generateMountain(World world, int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                double ridged = NoiseBackendManager.sample("ridged", worldX, 0, worldZ);
                int peakHeight = (int)(ridged * 18500); // max peak 18.5k

                for (int y = 0; y <= peakHeight; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    world.setBlockState(pos, net.minecraft.block.Blocks.STONE.getDefaultState());
                }

                // define waterfall start
                if (peakHeight > 15000) {
                    HyperRiverGenerator.generateUndergroundRiver(world, worldX, peakHeight, worldZ);
                }
            }
        }
    }
}
