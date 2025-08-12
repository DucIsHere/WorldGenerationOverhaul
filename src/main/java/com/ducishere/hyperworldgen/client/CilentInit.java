package com.ducishere.hyperworldgen.client;

import com.ducishere.hyperworldgen.client.reward.RewardHudOverlay;
import com.ducishere.hyperworldgen.client.reward.RewardNetworkClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Đăng ký HUD render cho reward popup
        HudRenderCallback.EVENT.register(new RewardHudOverlay());

        // Đăng ký packet listener client-side
        RewardNetworkClient.register();
    }
}
