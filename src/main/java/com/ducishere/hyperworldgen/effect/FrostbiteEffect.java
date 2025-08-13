package com.ducishere.hyperworldgen.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class FrostbiteEffect extends StatusEffect {
    public FrostbiteEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // mỗi 40 tick (2 giây) sẽ gọi applyUpdateEffect
        return duration % 40 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient) {
            // gây damage lạnh
            ServerWorld world = (ServerWorld) entity.getWorld();
            DamageSource frostDamage = new DamageSource(world.getRegistryManager()
                    .get(Registries.DAMAGE_TYPE)
                    .entryOf(DamageTypes.GENERIC));

            entity.damage(frostDamage, 1.0F + amplifier); // 1 máu + amplifier
        }
    }
}
