package com.example.thirst;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import com.example.thirst.network.PacketHandler;

public class MainMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        ThirstManager.init();
    }

    @Override
    public void onInitializeClient() {
        PacketHandler.registerClientReceiver();
    }
}
