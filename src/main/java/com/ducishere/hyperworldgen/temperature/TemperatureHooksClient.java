package toughasnails.temperature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import toughasnails.api.player.ITANPlayer;
import toughasnails.temperature.Temperature;

public class TemperatureHooksClient {

    /**
     * Kiểm tra có nên override heart render hay không.
     * Chỉ override khi player ở trạng thái nhiệt độ nguy hiểm (Cold / Hot).
     */
    public static boolean shouldOverrideHearts() {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player == null) {
            return false; // chưa có player thì thôi
        }

        ITANPlayer tanPlayer = (ITANPlayer) player;

        // Lấy state nhiệt độ từ player
        Temperature.State state = tanPlayer.getTemperatureStats().getState();

        // Nếu đang ở trạng thái nguy hiểm -> override heart
        return state == Temperature.State.COLD || state == Temperature.State.HOT;
    }

    /**
     * Custom render heart (vanilla đã bị cancel trong mixin).
     * Tùy vào state sẽ render texture khác.
     */
    public static void heartBlit(
            net.minecraft.client.gui.GuiGraphics gui,
            net.minecraft.client.gui.Gui.HeartType heartType,
            int x, int y,
            boolean isHardcore, boolean isBlinking, boolean isHalf
    ) {
        Minecraft mc = Minecraft.getInstance();
        ITANPlayer tanPlayer = (ITANPlayer) mc.player;

        if (tanPlayer == null) return;

        Temperature.State state = tanPlayer.getTemperatureStats().getState();

        if (state == Temperature.State.COLD) {
            // TODO: render heart đóng băng (texture heart_freezing.png)
        } else if (state == Temperature.State.HOT) {
            // TODO: render heart cháy (texture heart_overheating.png)
        } else {
            // fallback: render vanilla heart (nếu cần)
        }
    }
}
