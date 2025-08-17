package com.ducishere.hyperworldgen.thirst;

import glitchcore.event.TickEvent;
import glitchcore.event.client.RenderGuiEvent;
import glitchcore.util.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.potion.TANEffects;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.init.ModConfig;

import java.util.Random;

public class ThirstOverlayRenderer
{
    private static final Random RANDOM = new Random();
    public static final ResourceLocation OVERLAY = new ResourceLocation("toughasnails:textures/gui/icons.png");
    private static int updateCounter;

    public static void onClientTick(TickEvent.Client event)
    {
        if (event.getPhase() == TickEvent.Phase.END && !Minecraft.getInstance().isPaused())
            updateCounter++;
    }

    public static void onBeginRender(RenderGuiEvent.Pre event)
    {
        if (event.getType() != RenderGuiEvent.Type.AIR || !ModConfig.thirst.enableThirst)
            return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || mc.options.hideGui || !GuiUtils.shouldDrawSurvivalElements())
            return;

        RANDOM.setSeed(updateCounter * 312871L);
        IThirst thirst = ThirstHelper.getThirst(player);

        int rowTop = event.getRowTop();
        drawThirst(event.getGuiGraphics(), event.getScreenWidth(), rowTop, thirst.getThirst(), thirst.getHydration());
        event.setRowTop(rowTop - 10);
    }

    public static void drawThirst(GuiGraphics guiGraphics, int screenWidth, int rowTop, int thirstLevel, float thirstHydrationLevel)
    {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        int left = screenWidth / 2 + 91 + ModConfig.client.thirstLeftOffset;
        int top = rowTop + ModConfig.client.thirstTopOffset;

        for (int i = 0; i < 10; i++)
        {
            int dropletHalf = i * 2 + 1;
            int iconIndex = 0;
            int startX = left - i * 8 - 9;
            int startY = top;
            int backgroundU = 0;

            if (player.hasEffect(TANEffects.THIRST))
            {
                iconIndex += 4;
                backgroundU += 117;
            }

            if (thirstHydrationLevel <= 0.0F && updateCounter % (thirstLevel * 3 + 1) == 0)
                startY = top + (RANDOM.nextInt(3) - 1);

            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, OVERLAY, startX, startY, backgroundU, 32, 9, 9, 256, 256);

            if (thirstLevel > dropletHalf)
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, OVERLAY, startX, startY, (iconIndex + 4) * 9, 32, 9, 9, 256, 256);
            else if (thirstLevel == dropletHalf)
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, OVERLAY, startX, startY, (iconIndex + 5) * 9, 32, 9, 9, 256, 256);
        }
    }
}
