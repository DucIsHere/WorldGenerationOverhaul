package toughasnails.temperature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;

public class TemperatureOverlayRenderer {

    public static void renderTemperature(GuiGraphics gui, int width, int height) {
        if (!TemperatureHelper.getImpl().isTemperatureEnabled()) return;

        var player = Minecraft.getInstance().player;
        TemperatureLevel temp = TemperatureHelper.getTemperatureForPlayer(player);

        int x = width / 2;
        int y = height - 52;
        // blit overlay based on temp
        gui.blit(null, x, y, temp.ordinal() * 16, 0, 16, 16);
    }
}
