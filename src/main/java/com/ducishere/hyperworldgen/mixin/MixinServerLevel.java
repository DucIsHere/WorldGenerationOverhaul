package toughasnails.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.player.ITANPlayer;
import toughasnails.api.potion.TANEffects;
import toughasnails.init.ModConfig;

@Mixin(ServerLevel.class)
public class MixinServerLevel {

    /**
     * Inject vào addPlayer, để apply effect khi player join server.
     */
    @Inject(method = "addPlayer", at = @At("HEAD"))
    private void onAddPlayer(ServerPlayer player, CallbackInfo ci) {
        applyClimateClemency(player);
    }

    /**
     * Áp dụng hiệu ứng Climate Clemency nếu thỏa điều kiện.
     */
    private void applyClimateClemency(ServerPlayer player) {
        ITANPlayer tanPlayer = (ITANPlayer) player;

        // Kiểm tra cấu hình và trạng thái player
        boolean isTemperatureEnabled = ModConfig.temperature.enableTemperature;
        boolean hasClemencyDuration = ModConfig.temperature.climateClemencyDuration > 0;
        boolean notGrantedYet = !tanPlayer.getClimateClemencyGranted();
        boolean notCreative = !player.isCreative();

        if (isTemperatureEnabled && hasClemencyDuration && notGrantedYet && notCreative) {
            tanPlayer.setClimateClemencyGranted(true);

            // Thêm hiệu ứng CLIMATE_CLEMENCY vào player
            MobEffectInstance clemencyEffect = new MobEffectInstance(
                    TANEffects.CLIMATE_CLEMENCY,
                    ModConfig.temperature.climateClemencyDuration,
                    0,      // level
                    false,  // ambient
                    false,  // showParticles
                    true    // showIcon
            );

            player.addEffect(clemencyEffect);
        }
    }
}
