package toughasnails.temperature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.init.ModConfig;

public class TemperatureHooksClient {

    private static final ResourceLocation OVERHEATED_HEART = new ResourceLocation("toughasnails:hud/heart/overheated_full");

    public static void heartBlit(GuiGraphics gui, Gui.HeartType heartType, int x, int y, boolean isHardcore, boolean isBlinking, boolean isHalf) {
        var player = Minecraft.getInstance().player;
        if (TemperatureHelper.isFullyHyperthermic(player)) gui.blitSprite(gui.getShader(), OVERHEATED_HEART, x, y, 9, 9);
        else gui.blitSprite(gui.getShader(), heartType.getSprite(isHardcore, isHalf, isBlinking), x, y, 9, 9);
    }
}
