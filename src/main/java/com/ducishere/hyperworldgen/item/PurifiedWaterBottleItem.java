package toughasnails.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.thirst.ThirstHelper;

public class PurifiedWaterBottleItem extends DrinkItem {

    public PurifiedWaterBottleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAlwaysDrink() {
        return false; // chỉ uống khi khát
    }

    @Override
    protected void onDrink(Level world, Player player) {
        if (!world.isClientSide) {
            // Hồi thirst nhiều hơn nước bẩn
            ThirstHelper.addThirst(player, 6);

            // Có thể thêm hiệu ứng tốt nhẹ
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 3, 0)); // regen 3s
        }
    }
}