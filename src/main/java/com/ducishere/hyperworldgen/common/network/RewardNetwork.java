package com.ducishere.hyperworldgen.common.reward;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class RewardNetwork {
    public static final Identifier REWARD_PACKET_ID = new Identifier("hyperworldgen", "reward_popup");

    public static void sendRewardNotification(ServerPlayerEntity player, ItemStack item, String message) {
        PacketByteBuf buf = new PacketByteBuf(io.netty.buffer.Unpooled.buffer());
        buf.writeItemStack(item);
        buf.writeString(message);
        ServerPlayNetworking.send(player, REWARD_PACKET_ID, buf);
    }
}
