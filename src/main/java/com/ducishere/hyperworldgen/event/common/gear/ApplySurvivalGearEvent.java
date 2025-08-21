package com.ducishere.hyperworldgen.event.common.gear;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ApplySurvivalGearEvent {
    boolean onApply(ItemStack gearItem, ItemStack modifierItem, Player player);

    Event<ApplySurvivalGearEvent> EVENT = EventFactory.createArrayBacked(ApplySurvivalGearEvent.class,
        listeners -> (gearItem, modifierItem, player) -> {
            for (ApplySurvivalGearEvent listener : listeners) {
                if (!listener.onApply(gearItem, modifierItem, player)) return false;
            }
            return true;
        });
}
