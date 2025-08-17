package com.ducishere.hyperworldgen.network;

import glitchcore.network.CustomPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class ExamplePacket implements CustomPacket<ExamplePacket>
{
    private final int someInt;
    private final float someFloat;

    // Constructor
    public ExamplePacket(int someInt, float someFloat) {
        this.someInt = someInt;
        this.someFloat = someFloat;
    }

    // Default constructor (nếu framework cần)
    public ExamplePacket() {
        this(0, 0.0F);
    }

    // Encode data -> buffer
    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.someInt);
        buf.writeFloat(this.someFloat);
    }

    // Decode data <- buffer (static cho chắc chắn)
    public static ExamplePacket decode(FriendlyByteBuf buf) {
        int i = buf.readInt();
        float f = buf.readFloat();
        return new ExamplePacket(i, f);
    }

    // Handle khi packet tới
    @Override
    public void handle(ExamplePacket packet, Context context) {
        // Nếu chỉ client xử lý → bỏ qua server
        if (context.isServerSide())
            return;

        context.getPlayer().ifPresent(player -> {
            // Đây là client player
            Player clientPlayer = player;
            // TODO: Viết logic ở đây
        });
    }
}
