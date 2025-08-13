package com.ducishere.hyperworldgen.thirst;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import com.ducishere.hyperworldgen.thirst.network.PacketHandler;
importt com.ducishere.hyperworldgen.thirst.system.StatusManager;

public class MainMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        ThirstManager.init();
        StatusManager.init();
    }

    @Override
    public void onInitializeClient() {
        PacketHandler.registerClientReceiver();
    }
}
