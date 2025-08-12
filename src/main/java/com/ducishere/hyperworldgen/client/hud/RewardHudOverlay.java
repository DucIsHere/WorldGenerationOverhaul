package com.ducishere.hyperworldgen.client.reward;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RewardHudOverlay implements HudRenderCallback {
    private static final List<RewardPopup> popups = new ArrayList<>();

    public static void addPopup(RewardPopup popup) {
        popups.add(popup);
    }

    @Override
    public void onHudRender(MatrixStack matrices, float tickDelta) {
        Iterator<RewardPopup> iterator = popups.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            RewardPopup popup = iterator.next();
            popup.tick();
            popup.render(matrices, index++);
            if (popup.isExpired()) {
                iterator.remove();
            }
        }
    }
}
