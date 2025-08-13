package com.example.thirst.network;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import com.example.thirst.client.ClientData;

public class PacketHandler {
    public static final Identifier SYNC_STATUS = new Identifier("modid", "sync_status");

    // Server gửi cả thirst + temp
    public static void sendStatus(ServerPlayerEntity player, int thirst, int temp) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(thirst);
        buf.writeInt(temp);
        ServerPlayNetworking.send(player, SYNC_STATUS, buf);
    }

    // Client nhận
    public static void registerClientReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(SYNC_STATUS, (client, handler, buf, sender) -> {
            int thirst = buf.readInt();
            int temp = buf.readInt();
            client.execute(() -> {
                ClientData.thirst = thirst;
                ClientData.temperature = temp;
            });
        });
    }
}
