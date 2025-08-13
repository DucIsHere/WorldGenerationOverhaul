package com.ducishere.hyperworldgen.util;

import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnowUtils {

    /**
     * Set độ dày tuyết tại vị trí chỉ định.
     * @param world Thế giới
     * @param pos Tọa độ block
     * @param layers Số layer tuyết (1–8), 0 sẽ xóa tuyết
     */
    public static void setSnowLayer(World world, BlockPos pos, int layers) {
        if (world == null || pos == null) return;

        if (layers <= 0) {
            // Xóa tuyết nếu layer <= 0
            if (world.getBlockState(pos).getBlock() instanceof SnowBlock) {
                world.removeBlock(pos, false);
            }
            return;
        }

        if (layers > 8) layers = 8;

        world.setBlockState(pos, Blocks.SNOW.getDefaultState()
                .with(SnowBlock.LAYERS, layers));
    }
}