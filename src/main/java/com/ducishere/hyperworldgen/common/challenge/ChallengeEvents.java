package com.ducishere.hyperworldgen.common.challenge;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;

public class ChallengeEvents {

    private final ChallengeManager challengeManager;

    public ChallengeEvents(ChallengeManager challengeManager) {
        this.challengeManager = challengeManager;
        registerListeners();
    }

    private void registerListeners() {
        // Bắt sự kiện giết mob
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killer) -> {
            if (killer instanceof ServerPlayerEntity && entity instanceof LivingEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) killer;
                EntityType<?> killedType = entity.getType();

                // Ví dụ: nếu giết Husk, gọi complete challenge "kill_husk"
                if (killedType == EntityType.HUSK) {
                    challengeManager.completeChallenge("kill_husk");
                }

                // Thêm các loại mob hoặc điều kiện khác ở đây
            }
        });

        // Bắt sự kiện phá block (thu thập item)
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            if (player instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

                // Ví dụ: khi phá block đá quý, hoàn thành challenge "mine_rare_ore"
                if (state.getBlock().getTranslationKey().contains("ore")) {
                    challengeManager.completeChallenge("mine_rare_ore");
                }

                // Thêm các block khác tùy ý
            }
        });
    }
}