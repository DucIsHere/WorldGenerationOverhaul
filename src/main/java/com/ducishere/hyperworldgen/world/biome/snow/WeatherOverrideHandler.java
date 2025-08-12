package com.hypergenworld.weather;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.BlockPos;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class PlayerWeatherHandler {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ServerWorld world = player.getServerWorld();
                BlockPos pos = player.getBlockPos();
                Biome biome = world.getBiome(pos).value();
                float temp = biome.getTemperature();

                if (temp < -3.5f) {
                    // Force snowing for player using fake rain particles
                    world.setWeather(0, 6000, true, true);
                }
            }
        });
    }
}
