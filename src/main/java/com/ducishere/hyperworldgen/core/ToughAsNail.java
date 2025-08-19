package toughasnails.core;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.util.Identifier;
import toughasnails.api.potion.TANEffects;

public class ToughAsNails implements ModInitializer {

    @Override
    public void onInitialize() {
        registerEffects();
    }

    private void registerEffects() {
        Registry.register(Registry.MOB_EFFECT, new Identifier("toughasnails", "ice_resistance"), TANEffects.ICE_RESISTANCE);
    }
}
