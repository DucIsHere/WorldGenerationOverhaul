package com.ducishere.hyperworldgen.thirst;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.level.ServerPlayer;

public class HyperWorldGenThirst implements ModInitializer {

    @Override
    public void onInitialize() {
        // Server tick event
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerList().getPlayers().forEach(player -> ThirstHandler.onPlayerTick(player));
        });

        // Hand drinking
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ThirstHandler.onUseBlock(new PlayerInteractEvent.UseBlock(player, hand, hitResult));
            return null;
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            ThirstHandler.onUseEmpty(new PlayerInteractEvent.UseEmpty(player, hand));
            return null;
        });

        // Client tick for hand drinking cooldown
        ClientTickEvents.END_CLIENT_TICK.register(client -> ThirstHandler.onClientTick(null));

        // Optional: sync thirst on player join
        ServerLifecycleEvents.PLAYER_JOIN.register((player, server) -> {
            if (player instanceof ServerPlayer serverPlayer)
                ThirstHandler.syncThirst(serverPlayer);
        });

        System.out.println("[HyperWorldGenThirst] Thirst module initialized!");
    }
}
