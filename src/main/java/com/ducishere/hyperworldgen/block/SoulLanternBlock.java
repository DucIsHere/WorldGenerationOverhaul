package com.ducishere.hyperworldgen.block;package com.ducishere.hyperworldgen.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoulLanternBlock extends Block {

    public SoulLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, World world, BlockPos pos) {
        // Cho phép treo trên Fence gỗ
        BlockState below = world.getBlockState(pos.down());
        return below.getBlock() == Blocks.OAK_FENCE
            || below.getBlock() == Blocks.SPRUCE_FENCE
            || below.getBlock() == Blocks.BIRCH_FENCE
            || super.canPlaceAt(state, world, pos);
    }
}

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoulLanternBlock extends Block {

    public SoulLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, World world, BlockPos pos) {
        // Nếu dưới block là fence → cho phép
        BlockState below = world.getBlockState(pos.down());
        return below.getBlock() == Blocks.OAK_FENCE || below.getBlock() == Blocks.SPRUCE_FENCE || super.canPlaceAt(state, world, pos);
    }
}
