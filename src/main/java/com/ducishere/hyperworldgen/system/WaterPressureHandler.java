package com.ducishere.hyperworldgen.system;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WaterPressureHandler {

    // Gọi hàm này mỗi tick cho player
    public static void applyPressure(PlayerEntity player) {
        if (!player.isAlive()) return;

        // Chỉ hoạt động khi ở trong nước
        if (!player.isSubmergedInWater()) return;

        int depth = Math.max(0, -player.getBlockY()); // Y âm mới tính áp lực
        int pressureLevel = depth / 20; // mỗi 20 block sâu = +1 áp lực

        if (pressureLevel > 0) {
            // Mệt mỏi khai thác
            player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.MINING_FATIGUE, 40, pressureLevel - 1, true, false));

            // Chậm di chuyển
            player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SLOWNESS, 40, Math.min(pressureLevel / 2, 4), true, false));

            // Nếu quá sâu bắt đầu gây damage
            if (pressureLevel >= 5) {
                player.damage(player.getWorld().getDamageSources().drown(), pressureLevel - 4);
            }

            // Siêu sâu (-500) => Wither luôn
            if (depth >= 500) {
                player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.WITHER, 40, 0, true, false));
            }
        }
    }
}