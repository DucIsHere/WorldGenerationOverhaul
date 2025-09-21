package com.ducishere.hyperworldgen.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.NoiseConfig;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunk;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.structure.StructureAccessor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class HyperChunkGenerator extends ChunkGenerator {

    public static final Codec<HyperChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("maxHeight").forGetter(cg -> cg.maxHeight),
            Codec.INT.fieldOf("minHeight").forGetter(cg -> cg.minHeight)
    ).apply(instance, HyperChunkGenerator::new));

    private final int maxHeight;
    private final int minHeight;

    public HyperChunkGenerator(int maxHeight, int minHeight) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
    }

    @Override
    public int getWorldHeight() {
        return 20000; // ví dụ 20000
    }

    @Override
    public int getMinimumY() {
        return -10000; // ví dụ -10000
    }

    @Override
    public int getSeaLevel() {
        return 75;
    }

    @Override
    public CompletableFuture<Chunk> populateBiomes(Registry<Biome> biomeRegistry,
                                                   Executor executor,
                                                   NoiseConfig noiseConfig,
                                                   Blender blender,
                                                   StructureAccessor structureAccessor,
                                                   Chunk chunk) {
        return super.populateBiomes(biomeRegistry, executor, noiseConfig, blender, structureAccessor, chunk);
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender,
                                                  NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        NoiseChunk noiseChunk = new NoiseChunk(
                noiseConfig,
                chunk.getHeightLimitView(),
                chunk.getPos(),
                blender,
                0
        );
        noiseChunk.fillFromNoise(chunk);
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        switch (heightmap) {
            case WORLD_SURFACE:
                return 17500; // Peak núi
            case OCEAN_FLOOR:
                return -7500; // đáy biển
            default:
                return 0;
        }
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        int minY = world.getMinBuildHeight();
        int maxY = world.getMaxBuildHeight();
        int surfaceY = getHeight(x, z, Heightmap.Type.WORLD_SURFACE, world, noiseConfig);
        int oceanFloorY = getHeight(x, z, Heightmap.Type.OCEAN_FLOOR, world, noiseConfig);

        BlockState[] blocks = new BlockState[maxY - minY + 1];

        for (int y = minY; y <= maxY; y++) {
            int index = y - minY;
            if (y <= oceanFloorY) {
                blocks[index] = Blocks.STONE.getDefaultState();
            } else if (y < surfaceY) {
                blocks[index] = Blocks.DIRT.getDefaultState();
            } else if (y == surfaceY) {
                blocks[index] = Blocks.GRASS_BLOCK.getDefaultState();
            } else if (y <= getSeaLevel()) {
                blocks[index] = Blocks.WATER.getDefaultState();
            } else {
                blocks[index] = Blocks.AIR.getDefaultState();
            }
        }

        return new VerticalBlockSample(minY, blocks);
    }

    @Override
    public void generateChunk(World world, int chunkX, int chunkZ) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                // Noise sample
                double baseHeight = NoiseBackendManager.sample("opensimplex", worldX, 0, worldZ) * 1000;
                double mountain = NoiseBackendManager.sample("ridged", worldX, 0, worldZ) * 12000; // scale mạnh
                double terrace = NoiseBackendManager.sample("terrace", worldX, 0, worldZ) * 2000;
                double warp = NoiseBackendManager.sample("domainwarp", worldX, 0, worldZ) * 800;
                double hybrid = NoiseBackendManager.sample("hybrid", worldX, 0, worldZ) * 500;

                int finalHeight = (int) (baseHeight + mountain + terrace + warp + hybrid);

                // clamp theo minY / maxY
                if (finalHeight > maxHeight) finalHeight = maxHeight;
                if (finalHeight < minHeight) finalHeight = minHeight;

                for (int y = minHeight; y <= finalHeight; y++) {
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    world.setBlockState(pos, Blocks.STONE.getDefaultState());
                }
            }
        }
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }
}
