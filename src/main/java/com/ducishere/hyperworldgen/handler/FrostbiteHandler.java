package com.ducishere.hyperworldgen.handler;

import com.ducishere.hyperworldgen.effect.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FrostbiteHandler {
    private static final Map<UUID, Integer> snowTime = new HashMap<>();
    private static final int TIME_THRESHOLD = 20 * 60 * 5; // 5 phút

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.level().getBiome(player.blockPosition()).value().coldEnoughToSnow(player.blockPosition())) {
                    snowTime.put(player.getUUID(), snowTime.getOrDefault(player.getUUID(), 0) + 1);
                    if (snowTime.get(player.getUUID()) >= TIME_THRESHOLD) {
                        player.addEffect(new MobEffectInstance(ModEffects.FROSTBITE, 20 * 10, 0, false, true, true));
                    }
                } else {
                    snowTime.put(player.getUUID(), 0); // reset khi rời snow biome
                }
            }
        });
    }
}
