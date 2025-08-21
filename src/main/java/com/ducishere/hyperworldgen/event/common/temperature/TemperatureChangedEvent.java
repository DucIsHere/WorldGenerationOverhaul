package com.ducishere.hyperworldgen.event.common.temperature;

import com.ducishere.hyperworldgen.api.util.TemperatureTrait;
import net.minecraft.world.entity.LivingEntity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface TemperatureChangedEvent {

    boolean onTemperatureChanged(LivingEntity entity, TemperatureTrait trait, double oldTemperature, MutableDouble newTemperature);

    Event<TemperatureChangedEvent> EVENT = EventFactory.createArrayBacked(TemperatureChangedEvent.class,
        listeners -> (entity, trait, oldTemp, newTemp) -> {
            for (TemperatureChangedEvent listener : listeners) {
                if (!listener.onTemperatureChanged(entity, trait, oldTemp, newTemp)) return false;
            }
            return true;
        });

    class MutableDouble {
        public double value;
        public MutableDouble(double value) { this.value = value; }
    }
}
