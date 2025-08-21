package com.ducishere.hyperworldgen.event.common.insulation;

import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.Map;

public interface InsulationTickEvent {
    boolean onInsulationTick(Player player, Map<String, Double> insulation);

    Event<InsulationTickEvent> EVENT = EventFactory.createArrayBacked(InsulationTickEvent.class,
        listeners -> (player, insulation) -> {
            for (InsulationTickEvent listener : listeners) {
                if (!listener.onInsulationTick(player, insulation)) return false;
            }
            return true;
        });
}
