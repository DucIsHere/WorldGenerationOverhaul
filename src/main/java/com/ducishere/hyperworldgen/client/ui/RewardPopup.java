package com.ducishere.hyperworldgen.client.reward;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class RewardPopup {
    private final ItemStack item;
    private final String message;
    private int age; // Ticks tồn tại
    private final int maxAge = 60; // 3 giây
    private double yOffset;

    public RewardPopup(ItemStack item, String message) {
        this.item = item;
        this.message = message;
        this.age = 0;
        this.yOffset = 0;
    }

    public boolean isExpired() {
        return age >= maxAge;
    }

    public void tick() {
        age++;
        yOffset += 0.2; // Animation trượt lên
    }

    public void render(MatrixStack matrices, int index) {
        MinecraftClient client = MinecraftClient.getInstance();
        int x = 10;
        int y = 10 + (index * 20) - (int) yOffset;

        // Render icon item
        client.getItemRenderer().renderGuiItemIcon(item, x, y);

        // Render text bên cạnh
        client.textRenderer.draw(matrices, Text.literal(message), x + 20, y + 5, 0xFFFFFF);
    }
}
