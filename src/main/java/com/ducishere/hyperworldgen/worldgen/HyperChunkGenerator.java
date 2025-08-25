package com.ducishere.hyperworldgen.worldgen;

import net.fabricmc.fabric.api.biome.v2.BiomeModifications;
import net.fabricmc.fabric.api.biome.v2.BiomeSelectors;
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

  
}
