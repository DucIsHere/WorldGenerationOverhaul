package com.ducishere.hyperworldgen.api.event.client;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.Event;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Fired when custom HyperWorldGen items are about to be added to a mod-specific tab.
 * This event is fired for each type of item (armor, tools, consumables, etc.) being added.
 */
public class HyperWorldGenTabBuildEvent extends Event
{
    private final Collection<Map.Entry<Item, Object>> items; // Object có thể thay bằng custom ItemData

    public HyperWorldGenTabBuildEvent(Collection<Map.Entry<Item, Object>> items)
    {
        this.items = items;
    }

    public Collection<Map.Entry<Item, Object>> getItems()
    {
        return items;
    }

    /**
     * Add a filter to remove items that don't pass the predicate.
     * @param predicate receives (Item, Object) and returns true to keep, false to remove.
     */
    public void addCheck(BiPredicate<Item, Object> predicate)
    {
        items.removeIf(entry -> !predicate.test(entry.getKey(), entry.getValue()));
    }
}
