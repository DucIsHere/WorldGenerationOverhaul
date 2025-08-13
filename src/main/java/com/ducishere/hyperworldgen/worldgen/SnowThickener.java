package com.ducihsere.hyperworldgen.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SnowThickener extends Feature<NoneFeatureConfiguration> {

    public SnowThickener() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        LevelAccessor world = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        // Khu vực chunk hiện tại
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                BlockPos pos = world.getHeightmapPos(ChunkGenerator.Heightmap.WORLD_SURFACE, origin.offset(x, 0, z));

                BlockState stateBelow = world.getBlockState(pos.below());
                if (stateBelow.is(Blocks.SNOW_BLOCK) || stateBelow.is(Blocks.GRASS_BLOCK) || stateBelow.is(Blocks.DIRT)) {
                    int thickness = 6 + random.nextInt(3); // từ 6–8 layer
                    world.setBlock(pos, Blocks.SNOW.defaultBlockState()
                            .setValue(SnowLayerBlock.LAYERS, Math.min(8, thickness)), 2);
                }
            }
        }
        return true;
    }
}