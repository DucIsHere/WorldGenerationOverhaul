package toughasnails.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;
import toughasnails.api.potion.TANEffects;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity implements Attackable {

    public MixinLivingEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    // Redirect vanilla setTicksFrozen for mobs only
    @Redirect(method="aiStep", at=@At(value="INVOKE",
            target="Lnet/minecraft/world/entity/LivingEntity;setTicksFrozen(I)V"))
    private void redirectSetTicksFrozen(LivingEntity instance, int ticks) {
        if (!(this instanceof Player)) {
            this.setTicksFrozen(ticks);
        }
    }

    // Inject custom frozen ticks for players
    @Inject(method="aiStep", at=@At(value="INVOKE",
            target="Lnet/minecraft/world/entity/LivingEntity;getTicksFrozen()I"))
    private void injectGetTicksFrozen(CallbackInfo ci) {
        if (!(this instanceof Player)) return;

        Player player = (Player) (Object) this;
        ITemperature data = TemperatureHelper.getTemperatureData(player);
        int prevTicksFrozen = player.getTicksFrozen();

        if (!player.hasEffect(TANEffects.ICE_RESISTANCE)) {
            if (!player.isCreative() && !player.isSpectator()
                    && data.getLevel() == TemperatureLevel.ICY
                    && data.getExtremityDelayTicks() == 0) {
                player.setTicksFrozen(Math.min(player.getTicksRequiredToFreeze() + 2,
                        player.getTicksFrozen() + 2));
            } else if (player.isInPowderSnow && player.canFreeze()) {
                player.setTicksFrozen(Math.min(player.getTicksRequiredToFreeze(),
                        player.getTicksFrozen() + 1));
            }
        } else {
            // ICE_RESISTANCE active → reset frozen
            player.setTicksFrozen(0);
        }

        // Melting if ticksFrozen unchanged
        if (prevTicksFrozen == player.getTicksFrozen()) {
            player.setTicksFrozen(Math.max(0, player.getTicksFrozen() - 2));
        }
    }
}
