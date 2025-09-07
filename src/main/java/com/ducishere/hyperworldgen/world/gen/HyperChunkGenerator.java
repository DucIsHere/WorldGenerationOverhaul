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
       return 75;
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
  public void generateRiver(int chunkX, int chunkZ, Chunk chunk, NoiseConfig noiseConfig) {
    ChunkPos chunkPos = chunk.getPos();

    for (int x = 0; x < 16; x++) {
        for (int z = 0; z < 16; z++) {
            int worldX = chunkPos.getStartX() + x;
            int worldZ = chunkPos.getStartZ() + z;

            // Lấy biome
            var biome = chunk.getBiome(worldX, 0, worldZ);

            // Nếu biome cấm → không spawn river
            if (biome.equals(Biomes.OCEAN) || biome.equals(Biomes.DEEP_OCEAN) || 
                biome.getKey().getValue().toString().contains("volcano") || 
                biome.getKey().getValue().toString().contains("mountain")) {
                continue;
            }

            int riverWidth = 15 + RANDOM.nextInt(16); // 15-30 blocks
            int surfaceY = getHeight(worldX, worldZ, Heightmap.Type.WORLD_SURFACE, chunk, noiseConfig);

            for (int dx = -riverWidth/2; dx <= riverWidth/2; dx++) {
                for (int dz = -riverWidth/2; dz <= riverWidth/2; dz++) {
                    int bx = worldX + dx;
                    int bz = worldZ + dz;

                    // Lấy y của block xung quanh để mượt river
                    int by = getHeight(bx, bz, Heightmap.Type.WORLD_SURFACE, chunk, noiseConfig);

                    BlockPos pos = new BlockPos(bx, by, bz);

                    // Edge = bờ, middle = nước
                    if (Math.abs(dx) == riverWidth/2 || Math.abs(dz) == riverWidth/2) {
                        chunk.setBlockState(pos, Blocks.DIRT.getDefaultState(), false);
                    } else {
                        chunk.setBlockState(pos, Blocks.WATER.getDefaultState(), false);
                    }
                }
            }

            // Check biome tiếp theo, nếu mountain/volcano → kết thúc river mượt
            var nextBiome = chunk.getBiome(worldX+1, 0, worldZ+1);
            if (nextBiome.getKey().getValue().toString().contains("mountain") || 
                nextBiome.getKey().getValue().toString().contains("volcano")) {
                break; // river kết thúc
            }

            // Nếu gặp ocean → river kết thúc
            if (nextBiome == Biomes.OCEAN || nextBiome == Biomes.DEEP_OCEAN) {
                break;
            }
        }
    }
}

  @Override
    public void generateChunk(World world, int chunkX, int chunkZ){
        for(int x=0;x<16;x++){
            for(int z=0;z<16;z++){
                int worldX = chunkX*16 + x;
                int worldZ = chunkZ*16 + z;

                // Sample all noise
                double baseHeight = NoiseBackendManager.sample("opensimplex", worldX,0,worldZ)*1000;
                double mountain = NoiseBackendManager.sample("ridged", worldX,0,worldZ)*5000;
                double terrace = NoiseBackendManager.sample("terrace", worldX,0,worldZ)*500;
                double warp = NoiseBackendManager.sample("domainwarp", worldX,0,worldZ)*200;
                double hybrid = NoiseBackendManager.sample("hybrid", worldX,0,worldZ)*300;
                double cellular = NoiseBackendManager.sample("cellular", worldX,0,worldZ);
                double terrace = NoiseBackendManager.sample("terrablend", worldX,0,worldZ);
                double cave = NoiseBackendManager.sample("cave", worldX,0,worldZ);
                double fastnoise = NoiseBackendManager.sample("fastnoise", worldX,0,worldZ);
                double river = NoiseBackendManager.sample("river", worldX,0,worldZ);
              

                int finalHeight = (int)(baseHeight + mountain + terrace + warp + hybrid);

                // Fill terrain
                for(int y=0;y<=finalHeight;y++){
                    BlockPos pos = new BlockPos(worldX, y, worldZ);
                    world.setBlockState(pos, net.minecraft.block.Blocks.STONE.getDefaultState());
                }

                // Surface river
                double riverNoise = NoiseBackendManager.sample("river", worldX, finalHeight, worldZ);
                if(riverNoise>0.7){
                    HyperRiverGenerator.carveSurfaceRiver(world, worldX, finalHeight, worldZ);
                }

                // Cave
                double caveNoise = NoiseBackendManager.sample("cave", worldX, finalHeight, worldZ);
                if(caveNoise>0.65){
                    HyperCaveGenerator.carveCave(world, worldX, finalHeight, worldZ);
                }

                // Assign biome
                BiomePicker.assignBiome(world, worldX, finalHeight, worldZ);
            }
        }

        // Mountain waterfall source
        MountainGenerator.generateMountain(world, chunkX, chunkZ);
    }


  
  @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
      return CODEC;
    }
  
}
