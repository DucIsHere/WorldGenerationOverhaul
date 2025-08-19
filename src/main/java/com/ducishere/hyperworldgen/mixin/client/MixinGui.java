package toughasnails.mixin.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.temperature.TemperatureHooksClient;

@Mixin(Gui.class)
public abstract class MixinGui {

    @Inject(method = "renderHeart", at = @At("HEAD"), cancellable = true)
    private void onRenderHeart(GuiGraphics gui, Gui.HeartType heartType, int x, int y, boolean isHardcore, boolean isBlinking, boolean isHalf, CallbackInfo ci) {

        // Kiểm tra xem hệ thống nhiệt độ có bật không và có nên override không
        if (TemperatureHooksClient.shouldOverrideHearts()) {
            TemperatureHooksClient.heartBlit(gui, heartType, x, y, isHardcore, isBlinking, isHalf);
            ci.cancel(); // chặn render vanilla
        }
        // Nếu không override thì để Minecraft render mặc định
    }
}
