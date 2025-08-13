package com.example.thirst;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import com.example.thirst.network.PacketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ThirstManager {
    private static final Map<UUID, ThirstComponent> thirstMap = new HashMap<>();
    private static int tickCounter = 0;

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                thirstMap.putIfAbsent(player.getUuid(), new ThirstComponent());
                ThirstComponent comp = thirstMap.get(player.getUuid());

                // Giảm thirst mỗi 60s
                if (tickCounter % 1200 == 0) {
                    comp.addThirst(-1);
                    PacketHandler.sendThirst(player, comp.getThirst());
                }

                // Debuff khi thirst = 0
                if (comp.getThirst() <= 0) {
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.WEAKNESS, 40, 0, true, false
                    ));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.MINING_FATIGUE, 40, 4, true, false
                    ));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.SLOWNESS, 40, 1, true, false
                    ));
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.WITHER, 40, 0, true, false
                    ));
                }
            }
        });
    }
}
