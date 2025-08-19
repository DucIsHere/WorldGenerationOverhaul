package toughasnails.api.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class TANEffects {
    public static final MobEffect ICE_RESISTANCE = new MobEffect(MobEffectCategory.BENEFICIAL, 0x99ccff);

    public static void registerEffects() {
        // Registry.register(Registry.MOB_EFFECT, new Identifier("toughasnails", "ice_resistance"), ICE_RESISTANCE);
    }
}
