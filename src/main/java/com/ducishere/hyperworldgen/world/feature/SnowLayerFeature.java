package com.ducishere.hyperworldgen.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SnowLayerFeature extends Feature<NoneFeatureConfiguration> {

    public SnowLayerFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Random số layer chính (4-8)
        int baseLayers = 4 + random.nextInt(5); // 4–8

        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                BlockPos pos = origin.offset(dx, 0, dz);

                if (world.getBlockState(pos.below()).isSolid()) {
                    int variation = random.nextInt(3) - 1; // -1 đến +1
                    int layers = Math.max(1, Math.min(8, baseLayers + variation));

                    world.setBlock(pos, Blocks.SNOW.defaultBlockState()
                            .setValue(SnowLayerBlock.LAYERS, layers), 2);
                }
            }
        }
        return true;
    }
}