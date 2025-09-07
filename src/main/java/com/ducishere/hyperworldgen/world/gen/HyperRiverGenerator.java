package com.ducishere.hyperworldgen.world.gen;

import com.ducishere.hyperworldgen.world.noise.NoiseBackendManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HyperRiverGenerator {

    // river source threshold
    private static final double RIVER_THRESHOLD = 0.7;

    public static void generateSurfaceRiver(World world, int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;
                int height = world.getHeight(worldX, worldZ); // surface height

                double riverNoise = NoiseBackendManager.sample("river", worldX, height, worldZ);
                if (riverNoise > RIVER_THRESHOLD) {
                    carveRiverSurface(world, worldX, height, worldZ);
                    flowRiverToOcean(world, worldX, height, worldZ);
                }
            }
        }
    }

    private static void carveRiverSurface(World world, int x, int y, int z) {
        // Carve basic river channel
        for (int i = 0; i < 3; i++) { // simple depth
            BlockPos pos = new BlockPos(x, y - i, z);
            world.setBlockState(pos, net.minecraft.block.Blocks.WATER.getDefaultState());
        }
    }

    private static void flowRiverToOcean(World world, int startX, int startY, int startZ) {
        int x = startX;
        int z = startZ;
        int y = startY;

        while (!isOcean(world, x, y, z)) {
            // gradient descent
            int nextX = x + gradientStepX(world, x, y, z);
            int nextZ = z + gradientStepZ(world, x, y, z);
            int nextY = world.getHeight(nextX, nextZ);

            carveRiverSurface(world, nextX, nextY, nextZ);
            x = nextX;
            z = nextZ;
            y = nextY;
        }
    }

    private static boolean isOcean(World world, int x, int y, int z) {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock() == net.minecraft.block.Blocks.WATER;
    }

    private static int gradientStepX(World world, int x, int y, int z) {
        // simple gradient step logic, could use noise warp
        return (Math.random() > 0.5 ? 1 : -1);
    }

    private static int gradientStepZ(World world, int x, int y, int z) {
        return (Math.random() > 0.5 ? 1 : -1);
    }

    // Underground rivers start from surface river or waterfall
    public static void generateUndergroundRiver(World world, int x, int y, int z) {
        double caveNoise = NoiseBackendManager.sample("cave", x, y, z);
        if (caveNoise > 0.6) {
            // carve cave and water
            for (int dy = 0; dy < 3; dy++) {
                BlockPos pos = new BlockPos(x, y - dy, z);
                world.setBlockState(pos, net.minecraft.block.Blocks.WATER.getDefaultState());
            }
        }
    }
}
