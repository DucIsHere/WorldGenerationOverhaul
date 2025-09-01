package com.ducishere.hyperchain.worldgen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.World;

public class RiverGen {

    public static void registerRiverGen() {
        System.out.println("[Hyperchain] River Generator Registered");
        // Hook vào ChunkGenerator / BiomeModifications
    }

    public static void generateRiverFromSource(BlockPos source, Chunk chunk, World world) {
        BlockPos current = source;
        int riverDepth = getBiomeDepth(chunk.getBiome(current)); // Mountain 15–20, Plains 15–20
        int riverWidth = getBiomeWidth(chunk.getBiome(current)); // Mountain 10–15, Plains 20–25

        while (!isOcean(current, world)) {
            carveRiverSegment(current, riverWidth, riverDepth, world);

            current = nextRiverBlock(current, world);

            if (chunk.getBiome(current).isSnow()) freezeSurface(current, riverWidth, world);
            if (chunk.getBiome(current).isRiceTropical()) waterRiceTerraces(current, world);
        }

        carveRiverMouth(current, riverWidth, riverDepth, world);
        spawnDeltaFeatures(current, world);
        addAmbientParticles(current, world);
    }

    private static void carveRiverSegment(BlockPos pos, int width, int depth, World world) {
        for (int x = -width/2; x <= width/2; x++) {
            for (int y = 0; y < depth; y++) {
                BlockPos target = pos.add(x, -y, 0);
                world.setBlockState(target, net.minecraft.block.Blocks.WATER.getDefaultState());
            }
        }
    }

    private static BlockPos nextRiverBlock(BlockPos current, World world) {
        int nextX = current.getX() + randomSlopeX();
        int nextZ = current.getZ() + randomSlopeZ();
        int nextY = world.getHeightmap(net.minecraft.world.Heightmap.Type.WORLD_SURFACE_WG, nextX, nextZ) - 1;
        return new BlockPos(nextX, nextY, nextZ);
    }

    private static void carveRiverMouth(BlockPos oceanPos, int width, int depth, World world) {
        int taper = Math.min(width/2, 10);
        for (int x = -width/2 - taper; x <= width/2 + taper; x++) {
            for (int y = 0; y < depth; y++) {
                BlockPos target = oceanPos.add(x, -y, 0);
                world.setBlockState(target, net.minecraft.block.Blocks.WATER.getDefaultState());
                if (y == 0) world.setBlockState(target.up(), net.minecraft.block.Blocks.SAND.getDefaultState());
            }
        }
    }

    private static void freezeSurface(BlockPos pos, int width, World world) {
        for (int x = -width/2; x <= width/2; x++) {
            BlockPos top = pos.add(x, 0, 0);
            world.setBlockState(top, net.minecraft.block.Blocks.ICE.getDefaultState());
        }
    }

    private static void waterRiceTerraces(BlockPos pos, World world) {
        // Water rice terraces logic
    }

    private static void spawnDeltaFeatures(BlockPos pos, World world) {
        // Mini islands, loot chests, fish spawn
    }

    private static void addAmbientParticles(BlockPos pos, World world) {
        // Mist, splash, firefly particles
    }

    private static boolean isOcean(BlockPos pos, World world) {
        return world.getBlockState(pos).getBlock() == net.minecraft.block.Blocks.WATER;
    }

    private static int randomSlopeX() { return (int)(Math.random()*3)-1; }
    private static int randomSlopeZ() { return (int)(Math.random()*3)-1; }

    private static int getBiomeDepth(Object biome) {
        // Return depth based on biome type
        return 15; // placeholder
    }

    private static int getBiomeWidth(Object biome) {
        // Return width based on biome type
        return 20; // placeholder
    }
}
