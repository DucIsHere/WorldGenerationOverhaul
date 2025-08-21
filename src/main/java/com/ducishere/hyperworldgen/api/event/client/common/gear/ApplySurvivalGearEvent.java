package com.ducishere.hyperworldgen.api.event.common.gear;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired when a survival gear item is about to apply a custom effect.
 * Cancelling this event will prevent the effect from being applied.
 */
@Cancelable
public class ApplySurvivalGearEvent extends Event
{
    private final ItemStack gearItem;
    private ItemStack modifierItem;
    private final Player player;

    public ApplySurvivalGearEvent(ItemStack gearItem, ItemStack modifierItem, Player player)
    {
        this.gearItem = gearItem;
        this.modifierItem = modifierItem;
        this.player = player;
    }

    public ItemStack getGearItem() { return gearItem; }

    public ItemStack getModifierItem() { return modifierItem; }

    public void setModifierItem(ItemStack modifierItem) { this.modifierItem = modifierItem; }

    public Player getPlayer() { return player; }
}
