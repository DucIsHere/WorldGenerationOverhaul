package com.ducishere.hyperworldgen.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HudRenderer implements HudRenderCallback {
    private static final Identifier THIRST_FULL = new Identifier("modid", "textures/gui/thirst_full.png");
    private static final Identifier THIRST_EMPTY = new Identifier("modid", "textures/gui/thirst_empty.png");
    private static final Identifier TEMP_ICON = new Identifier("modid", "textures/gui/temp_icon.png");

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Lấy dữ liệu thirst + temperature từ component (m tự viết)
        int thirst = ThirstData.get(client.player); 
        int temp = TempData.get(client.player);     

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        // ===== Vẽ thanh thirst =====
        int xThirst = screenWidth / 2 + 91; // vị trí trên food bar
        int yThirst = screenHeight - 49;    // cao hơn food 1 chút

        for (int i = 0; i < 10; i++) {
            int iconX = xThirst - i * 8;
            context.drawTexture(THIRST_EMPTY, iconX, yThirst, 0, 0, 9, 9, 9, 9);
            if (i < thirst / 2) {
                context.drawTexture(THIRST_FULL, iconX, yThirst, 0, 0, 9, 9, 9, 9);
            }
        }

        // ===== Vẽ thanh nhiệt độ =====
        int xTemp = screenWidth / 2 - 9; // giữa máu và food
        int yTemp = screenHeight - 39;
        context.drawTexture(TEMP_ICON, xTemp, yTemp, 0, 0, 9, 9, 9, 9);
    }
}
