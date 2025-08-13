package com.ducishere.hyperworldgen.thirst.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import com.example.thirst.client.ClientData;

public class PacketHandler {
    public static final Identifier SYNC_THIRST = new Identifier("modid", "sync_thirst");

    // Server gửi
    public static void sendThirst(ServerPlayerEntity player, int thirst) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(thirst);
        ServerPlayNetworking.send(player, SYNC_THIRST, buf);
    }

    // Client nhận
    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(SYNC_THIRST, (client, handler, buf, sender) -> {
            int thirst = buf.readInt();
            client.execute(() -> ClientData.thirst = thirst);
        });
    }
}
