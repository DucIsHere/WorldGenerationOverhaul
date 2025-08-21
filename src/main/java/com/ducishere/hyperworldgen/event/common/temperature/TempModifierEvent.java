package com.ducishere.hyperworldgen.event.common.temperature;

import com.ducishere.hyperworldgen.api.util.TemperatureTrait;
import com.ducishere.hyperworldgen.temperature.TempModifier;
import net.minecraft.world.entity.LivingEntity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.util.function.Function;

public interface TempModifierEvent {

    boolean onAdd(LivingEntity entity, TemperatureTrait trait, TempModifier modifier);
    boolean onRemove(LivingEntity entity, TemperatureTrait trait, TempModifier modifier);
    boolean onCalculatePre(LivingEntity entity, TemperatureTrait trait, TempModifier modifier, MutableDouble temperature, Function<Double, Double> newFunction);
    void onCalculatePost(LivingEntity entity, TemperatureTrait trait, TempModifier modifier, MutableDouble temperature, Function<Double, Double> newFunction);

    Event<TempModifierEvent> ADD_EVENT = EventFactory.createArrayBacked(
        TempModifierEvent.class, listeners ->
            (entity, trait, modifier) -> {
                for (TempModifierEvent l : listeners) if (!l.onAdd(entity, trait, modifier)) return false;
                return true;
            });

    Event<TempModifierEvent> REMOVE_EVENT = EventFactory.createArrayBacked(
        TempModifierEvent.class, listeners ->
            (entity, trait, modifier) -> {
                for (TempModifierEvent l : listeners) if (!l.onRemove(entity, trait, modifier)) return false;
                return true;
            });

    Event<TempModifierEvent> CALCULATE_PRE_EVENT = EventFactory.createArrayBacked(
        TempModifierEvent.class, listeners ->
            (entity, trait, modifier, temperature, newFunction) -> {
                for (TempModifierEvent l : listeners)
                    if (!l.onCalculatePre(entity, trait, modifier, temperature, newFunction)) return false;
                return true;
            });

    Event<TempModifierEvent> CALCULATE_POST_EVENT = EventFactory.createArrayBacked(
        TempModifierEvent.class, listeners ->
            (entity, trait, modifier, temperature, newFunction) -> {
                for (TempModifierEvent l : listeners) l.onCalculatePost(entity, trait, modifier, temperature, newFunction);
            });

    class MutableDouble {
        public double value;
        public MutableDouble(double value) { this.value = value; }
    }
}
