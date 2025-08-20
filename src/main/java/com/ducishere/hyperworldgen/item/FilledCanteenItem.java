package toughasnails.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import toughasnails.api.item.TANItems;
import toughasnails.api.thirst.ThirstHelper;

public abstract class FilledCanteenItem extends Item {
    protected final int tier;

    public FilledCanteenItem(int tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (ThirstHelper.canDrink(player, this.canAlwaysDrink())) {
            return ItemUtils.startUsingInstantly(level, player, hand);
        }
        return InteractionResult.FAIL;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (!(entityLiving instanceof Player player)) return stack;

        player.awardStat(Stats.ITEM_USED.get(this));

        if (!world.isClientSide && !player.getAbilities().instabuild) {
            stack.hurtAndBreak(1, (ServerPlayer) player, (p) -> {
                // Khi vỡ thì trả empty canteen
                ItemStack emptyStack = new ItemStack(getEmptyCanteen());
                if (stack.hasTag()) emptyStack.setTag(stack.getTag().copy());
                p.setItemInHand(p.getUsedItemHand(), emptyStack);
            });
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 32;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.DRINK;
    }

    public boolean canAlwaysDrink() {
        return false;
    }

    public Item getEmptyCanteen() {
        return switch (this.tier) {
            default -> TANItems.EMPTY_LEATHER_CANTEEN;
            case 1 -> TANItems.EMPTY_COPPER_CANTEEN;
            case 2 -> TANItems.EMPTY_IRON_CANTEEN;
            case 3 -> TANItems.EMPTY_GOLD_CANTEEN;
            case 4 -> TANItems.EMPTY_DIAMOND_CANTEEN;
            case 5 -> TANItems.EMPTY_NETHERITE_CANTEEN;
        };
    }

    /** Mỗi subclass override logic riêng (nước bẩn, nước sạch...) */
    public abstract void onDrank(Player player, Level level);
}