package com.ducishere.hyperworldgen.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColdAura {

    public static void tickSoulLantern(World world, BlockPos pos) {
        if (world.isClient) return;

        int radius = 5; // bán kính 5 block
        for (PlayerEntity player : world.getPlayers()) {
            if (player.getBlockPos().isWithinDistance(pos, radius)) {
                // Áp hiệu ứng slowness + giảm nhiệt độ (custom stat nếu có)
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1, true, false));
                // Nếu có system temperature: giảm nhiệt độ player ở đây
            }
        }
    }
}
