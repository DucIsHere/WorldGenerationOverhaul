package com.ducishere.hyperworldgen.block;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;

public class ColdAura {

    public static void tick(World world, BlockPos pos){
        if(world.isClient) return;

        int radius = 5;
        for(PlayerEntity player : world.getPlayers()){
            if(player.getBlockPos().isWithinDistance(pos, radius)){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 1, true, false));
                // Particle lạnh
                world.addParticle(ParticleTypes.SNOWFLAKE, player.getX(), player.getY() + 1, player.getZ(), 0, 0.01, 0);
            }
        }
    }
}
