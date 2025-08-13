package com.ducishere.hyperworldgen.client;

import com.ducishere.hyperworldgen.client.reward.RewardHudOverlay;
import com.ducishere.hyperworldgen.client.reward.RewardNetworkClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new RewardHudOverlay());
        HudRenderCallback.EVENT.register(new HudRenderer());

        // Đăng ký packet listener client-side
        RewardNetworkClient.register();
    }
}
