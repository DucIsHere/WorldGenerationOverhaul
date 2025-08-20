package toughasnails.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import toughasnails.api.thirst.ThirstHelper;

public class JuiceItem extends DrinkItem
{
    public JuiceItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canAlwaysDrink()
    {
        return false; // chỉ uống khi còn khát
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving)
    {
        if (entityLiving instanceof Player player)
        {
            if (!world.isClientSide)
            {
                // Thêm thirst points (ví dụ +6 nước)
                ThirstHelper.addThirst(player, 6, 0.8F);

                // Buff nhẹ khi uống nước ép
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0)); // 5 giây regen I
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0)); // 10 giây speed I
            }

            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild)
            {
                stack.shrink(1);

                if (stack.isEmpty())
                    return new ItemStack(Items.GLASS_BOTTLE);

                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity)
    {
        return 32;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack)
    {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
        if (ThirstHelper.canDrink(player, this.canAlwaysDrink()))
        {
            return ItemUtils.startUsingInstantly(world, player, hand);
        }

        return InteractionResult.FAIL;
    }
}