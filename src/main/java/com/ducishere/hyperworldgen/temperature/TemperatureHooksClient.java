package toughasnails.temperature;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.player.LocalPlayer;
import toughasnails.api.player.ITANPlayer;

/**
 * Client-side hook cho temperature system:
 * - Quyết định có override heart không
 * - Render heart custom theo nhiệt độ
 */
public class TemperatureHooksClient {

    // Texture custom
    private static final ResourceLocation HEART_FREEZING = new ResourceLocation("toughasnails", "textures/gui/heart_freezing.png");
    private static final ResourceLocation HEART_OVERHEATING = new ResourceLocation("toughasnails", "textures/gui/heart_overheating.png");

    /**
     * Kiểm tra có nên override heart render hay không.
     * Chỉ override khi player ở trạng thái nhiệt độ nguy hiểm (COLD / HOT).
     */
    public static boolean shouldOverrideHearts() {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player == null) {
            return false;
        }

        ITANPlayer tanPlayer = (ITANPlayer) player;

        Temperature.State state = tanPlayer.getTemperatureStats().getState();

        return state == Temperature.State.COLD || state == Temperature.State.HOT;
    }

    /**
     * Render custom heart thay thế heart vanilla.
     * Nếu COLD → heart xanh băng.
     * Nếu HOT → heart đỏ rực.
     */
    public static void heartBlit(
            GuiGraphics gui,
            net.minecraft.client.gui.Gui.HeartType heartType,
            int x, int y,
            boolean isHardcore, boolean isBlinking, boolean isHalf
    ) {
        Minecraft mc = Minecraft.getInstance();
        ITANPlayer tanPlayer = (ITANPlayer) mc.player;
        if (tanPlayer == null) return;

        Temperature.State state = tanPlayer.getTemperatureStats().getState();

        ResourceLocation texture;
        if (state == Temperature.State.COLD) {
            texture = HEART_FREEZING;
        } else if (state == Temperature.State.HOT) {
            texture = HEART_OVERHEATING;
        } else {
            return; // nếu NORMAL thì không render custom
        }

        // Bind texture và vẽ vào HUD
        RenderSystem.setShaderTexture(0, texture);

        // Kích thước mỗi heart 9x9 (chuẩn vanilla)
        int u = isHalf ? 9 : 0; // half heart → offset
        int v = 0;

        gui.blit(texture, x, y, u, v, 9, 9, 18, 9);
    }
}
