package com.ducishere.hyperworldgen.client.reward;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

public class RewardNetworkClient {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(
            com.ducishere.hyperworldgen.common.reward.RewardNetwork.REWARD_PACKET_ID,
            (client, handler, buf, responseSender) -> {
                ItemStack item = buf.readItemStack();
                String message = buf.readString();

                client.execute(() -> {
                    RewardHudOverlay.addPopup(new RewardPopup(item, message));
                    client.player.playSound(
                        net.minecraft.sound.SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                        1.0F, 1.0F
                    );
                });
            }
        );
    }
}
