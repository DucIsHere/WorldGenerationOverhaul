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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public class MixinServerLevel {

    @Inject(method = "addPlayer", at = @At("HEAD"))
    private void onAddPlayer(ServerPlayer player, CallbackInfo ci) {
        applyJoinEffects(player);
    }

    /**
     * Áp dụng tất cả các effect khi player join theo config.
     */
    private void applyJoinEffects(ServerPlayer player) {
        ITANPlayer tanPlayer = (ITANPlayer) player;

        // Danh sách các effect sẽ apply (condition, effect)
        List<EffectRule> effects = new ArrayList<>();

        // --- Climate Clemency ---
        effects.add(new EffectRule(
                // Điều kiện áp dụng
                p -> ModConfig.temperature.enableTemperature
                        && ModConfig.temperature.climateClemencyDuration > 0
                        && !tanPlayer.getClimateClemencyGranted()
                        && !p.isCreative(),
                // Cách tạo effect
                () -> new MobEffectInstance(
                        TANEffects.CLIMATE_CLEMENCY,
                        ModConfig.temperature.climateClemencyDuration,
                        0, false, false, true
                ),
                // Callback sau khi apply
                () -> tanPlayer.setClimateClemencyGranted(true)
        ));

        // --- Sau này chỉ cần add thêm effects khác vào đây ---
        // effects.add(new EffectRule(...));

        // Thực thi tất cả effect thỏa điều kiện
        for (EffectRule rule : effects) {
            if (rule.condition.test(player)) {
                player.addEffect(rule.effect.get());
                rule.afterApply.run();
            }
        }
    }

    /**
     * Rule cho một effect: condition, effect factory, callback sau khi apply.
     */
    private record EffectRule(Predicate<ServerPlayer> condition,
                              Supplier<MobEffectInstance> effect,
                              Runnable afterApply) {}
}
