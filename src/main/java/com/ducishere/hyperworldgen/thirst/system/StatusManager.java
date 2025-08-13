package com.ducishere.hyperworldgen.thirst.system;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import com.ducishere.hyperworldgen.thirst.ThirstComponent;
import com.ducishere.hyperworldgen.temp.TemperatureComponent;
import com.ducishere.hyperworldgen.thirst.network.PacketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatusManager {
    private static final Map<UUID, ThirstComponent> thirstMap = new HashMap<>();
    private static final Map<UUID, TemperatureComponent> tempMap = new HashMap<>();
    private static int tickCounter = 0;

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            tickCounter++;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                thirstMap.putIfAbsent(player.getUuid(), new ThirstComponent());
                tempMap.putIfAbsent(player.getUuid(), new TemperatureComponent());

                ThirstComponent thirstComp = thirstMap.get(player.getUuid());
                TemperatureComponent tempComp = tempMap.get(player.getUuid());

                // Giảm thirst
                if (tickCounter % 1200 == 0) { // 60s
                    thirstComp.addThirst(-1);
                }

                // Thay đổi nhiệt độ (ví dụ test: mỗi 30s tăng 1)
                if (tickCounter % 600 == 0) {
                    tempComp.addTemperature(1);
                }

                // Debuff nếu thirst = 0
                if (thirstComp.getThirst() <= 0) {
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

                // Debuff nhiệt độ cao
                if (tempComp.getTemperature() > 80) {
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.WEAKNESS, 40, 0, true, false
                    ));
                }

                // Debuff nhiệt độ thấp
                if (tempComp.getTemperature() < 20) {
                    player.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                        net.minecraft.entity.effect.StatusEffects.SLOWNESS, 40, 0, true, false
                    ));
                }

                // Sync về client
                PacketHandler.sendStatus(player, thirstComp.getThirst(), tempComp.getTemperature());
            }
        });
    }
}
