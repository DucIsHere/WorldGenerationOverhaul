package toughasnails.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.thirst.ThirstHooks;

@Mixin(FoodData.class)
public abstract class MixinFoodData {

    @Inject(method="tick", at=@At("HEAD"), cancellable = true)
    private void onTick(Player player, CallbackInfo ci) {
        ThirstHooks.doFoodDataTick((FoodData)(Object)this, player);
        ci.cancel(); // tắt hunger vanilla, chỉ dùng Thirst
    }
}
