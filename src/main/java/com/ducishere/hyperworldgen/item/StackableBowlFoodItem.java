package toughasnails.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class StackableBowlFoodItem extends Item
{
    public StackableBowlFoodItem(Properties properties)
    {
        super(properties.stacksTo(16)); // Cho phép stack lên tới 16 (tùy chỉnh)
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity)
    {
        Player player = entity instanceof Player ? (Player)entity : null;

        if (player != null)
        {
            if (player instanceof ServerPlayer serverPlayer)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild)
            {
                stack.shrink(1);

                if (stack.isEmpty())
                {
                    return new ItemStack(Items.BOWL);
                }

                player.getInventory().add(new ItemStack(Items.BOWL));
            }
        }

        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity)
    {
        return 32; // thời gian ăn = 32 ticks (giống đồ ăn mặc định)
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack)
    {
        return ItemUseAnimation.EAT; // Animation ăn
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
        return ItemUtils.startUsingInstantly(world, player, hand);
    }
}