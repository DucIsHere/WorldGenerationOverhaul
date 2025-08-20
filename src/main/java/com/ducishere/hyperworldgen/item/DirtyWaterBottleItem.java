package toughasnails.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundEvents;

public class DirtyWaterBottleItem extends DrinkItem {

    public DirtyWaterBottleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAlwaysDrink() {
        return false; // chỉ uống khi khát
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // gọi mặc định (cho hồi thirst nếu hệ thống thirst có hook trong DrinkItem)
        super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide) {
            // thêm hiệu ứng xấu
            entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20 * 15, 0)); // 15s Hunger
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 8, 0)); // 8s Nausea
        }

        // trả lại chai rỗng
        return ItemUtils.createFilledResult(stack, entity, new ItemStack(Items.GLASS_BOTTLE));
    }
}