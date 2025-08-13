package com.ducishere.hyperworldgen.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

public class ModEffects {
    public static final MobEffect FROSTBITE = new MobEffect(MobEffectCategory.HARMFUL, 0xADD8E6) {
        @Override
        public boolean isDurationEffectTick(int duration, int amplifier) {
            return true;
        }

        @Override
        public void applyEffectTick(net.minecraft.world.entity.LivingEntity entity, int amplifier) {
            if (!entity.level().isClientSide) {
                // Ví dụ: gây damage mỗi 2 giây
                if (entity.tickCount % 40 == 0) {
                    entity.hurt(entity.damageSources().freeze(), 1.0F);
                }
            }
        }
    };
}