package com.ducishere.hyperworldgen.world.gen;

import com.ducishere.hyperworldgen.world.noise.NoiseBackendManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HyperCaveGenerator {

    public static void generateCaves(World world, int chunkX, int chunkZ){
        for (int x=0;x<16;x++){
            for (int z=0;z<16;z++){
                int worldX=chunkX*16+x;
                int worldZ=chunkZ*16+z;
                for (int y=20;y<5000;y+=5){ // example cave layer
                    double caveNoise = NoiseBackendManager.sample("cave", worldX, y, worldZ);
                    if (caveNoise>0.65){
                        BlockPos pos = new BlockPos(worldX, y, worldZ);
                        world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState());
                        HyperRiverGenerator.generateUndergroundRiver(world, worldX, y, worldZ);
                    }
                }
            }
        }
    }
}
