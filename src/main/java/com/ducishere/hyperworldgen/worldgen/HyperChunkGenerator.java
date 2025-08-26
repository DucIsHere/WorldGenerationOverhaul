package com.ducishere.hyperworldgen.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class HyperChunkGenerator extends ChunkGenerator {
  public static final Codec<HyperChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    Codec.INT.fieldOf("maxHeight").forGetter(cg -> cg.maxHeight),
    Codec.INT.fieldOf("minHeight").forGetter(cg -> cg.minHeight)
    ).apply(instance, HyperChunkGenerator::new)
);

private final int maxHeight;
private final int minHeight;

public ExampleChunkGenerator(int maxHeight, int minHeight) {
    this.maxHeight = maxHeight;
    this.minHeight = minHeight;
}

  @Override
    public int getWorldHeight() {
      return 50000;
  }

  @Override
    public int getMinimumY() {
      return -25000;
  }

  @Override
     public int seaLevel() {
       return 500;
  }

  @Override
    public CompletableFuture<Chunk> populateBiomes(
        Registry<Biome> biomeRegistry,
        Executor executor,
        NoiseConfig noiseConfig,
        Blender blender,
        StructureAccessor structureAccessor,
        Chunk chunk
) {
    return super.populateBiomes(biomeRegistry, executor, noiseConfig, blender, structureAccessor, chunk);
}


  @Override
public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {
    // Lọc bước carve
    if(carverStep == GenerationStep.Carver.AIR) {
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                int y = getHeightAt(x, z, noiseConfig); // lấy cao độ noise
                if(shouldCarve(x, y, z, seed)) { // kiểm tra carve theo noise
                    chunk.setBlockState(x, y, z, Blocks.AIR.getDefaultState()); // carve thành air
                }
            }
        }
    }
}


  @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
      NoiseChunk noiseChunk = new NoiseChunk(
        noiseConfig,
        chunk.getHeightLimitView();
        chunk.getPos(),
        blender
        0
      );

    noiseChunk.fillFromNoise(chunk);

    return CompletableFuture.completedFuture(chunk);
        
    }

  @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        switch (heightmap) {
          case WORLD_SURFACE:
            return 500;
          case OCEAN_FLOOR:
            return -7500;
          default:
            return 0;
        }
    }

  @Override
public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
    int minY = world.getMinBuildHeight();       // -2700
    int maxY = world.getMaxBuildHeight();       // 500
    int surfaceY = getHeight(x, z, Heightmap.Type.WORLD_SURFACE, world, noiseConfig);
    int oceanFloorY = getHeight(x, z, Heightmap.Type.OCEAN_FLOOR, world, noiseConfig);

    BlockState[] blocks = new BlockState[maxY - minY + 1];

    for (int y = minY; y <= maxY; y++) {
        int index = y - minY;
        if (y <= oceanFloorY) {
            // Dưới đáy biển
            blocks[index] = Blocks.STONE.getDefaultState();
        } else if (y < surfaceY) {
            // Dưới mặt đất
            blocks[index] = Blocks.DIRT.getDefaultState();
        } else if (y == surfaceY) {
            // Mặt đất
            blocks[index] = Blocks.GRASS_BLOCK.getDefaultState();
        } else if (y <= 500) {
            // Nước biển (sea level 500)
            blocks[index] = Blocks.WATER.getDefaultState();
        } else {
            // Trên mặt nước
            blocks[index] = Blocks.AIR.getDefaultState();
        }
    }

    return new VerticalBlockSample(minY, blocks);
}


  @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
      return CODEC;
    }
  
}
