package com.ducishere.hyperworldgen.thirst;

import glitchcore.event.TickEvent;
import glitchcore.event.entity.LivingEntityUseItemEvent;
import glitchcore.event.player.PlayerEvent;
import glitchcore.event.player.PlayerInteractEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import toughasnails.api.damagesource.TANDamageTypes;
import toughasnails.api.item.TANItems;
import toughasnails.api.potion.TANEffects;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.core.ToughAsNails;
import toughasnails.init.ModConfig;
import toughasnails.init.ModPackets;
import toughasnails.init.ModTags;
import toughasnails.network.DrinkInWorldPacket;
import toughasnails.network.UpdateThirstPacket;
import toughasnails.temperature.TemperatureData;
import com.ducishere.hyperworldgen.SeasonManager;
import com.ducishere.hyperworldgen.SeasonManager.Season;

public class ThirstHandler {

    // ------------------- Tick logic -------------------
    public static void onPlayerTick(Player player) {
        if (!ModConfig.thirst.enableThirst || player.level().isClientSide())
            return;

        IThirst thirst = ThirstHelper.getThirst(player);
        Difficulty difficulty = player.level().getDifficulty();
        double exhaustionThreshold = ModConfig.thirst.thirstExhaustionThreshold;

        // Tweak theo mùa
        float seasonalModifier = 1.0f;
        Season currentSeason = SeasonManager.getSeason(player.level());
        if (currentSeason == Season.SUMMER) seasonalModifier = 1.5f; // mùa hè mất thirst nhanh
        if (currentSeason == Season.WINTER) seasonalModifier = 0.7f; // mùa đông mất chậm

        if (thirst.getExhaustion() > exhaustionThreshold) {
            thirst.addExhaustion((float) -exhaustionThreshold);

            if (thirst.getHydration() > 0.0F) {
                thirst.setHydration(Math.max(thirst.getHydration() - (1.0F * seasonalModifier), 0.0F));
            } else if (difficulty != Difficulty.PEACEFUL) {
                thirst.setThirst(Math.max(thirst.getThirst() - (1 * seasonalModifier), 0));
            }
        }

        // Damage khi cạn bar
        if (thirst.getThirst() <= 0) {
            thirst.addTicks(1);
            int tickLimit = (int) (80 / seasonalModifier);

            if (thirst.getTickTimer() >= tickLimit) {
                float damage = 1.0f * seasonalModifier;
                if (player.getHealth() > 10.0F || difficulty == Difficulty.HARD
                        || player.getHealth() > 1.0F && difficulty == Difficulty.NORMAL)
                    player.hurt(player.damageSources().source(TANDamageTypes.THIRST), damage);

                thirst.setTickTimer(0);
            }
        } else {
            thirst.setTickTimer(0);
        }

        // Peaceful regen
        if (difficulty == Difficulty.PEACEFUL
                && ((ServerLevel) player.level()).getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
            if (thirst.isThirsty() && player.tickCount % 10 == 0) {
                thirst.setThirst(thirst.getThirst() + 1);
            }
        }
    }

    // ------------------- Sync -------------------
    public static void syncThirst(ServerPlayer player) {
        IThirst thirst = ThirstHelper.getThirst(player);
        ModPackets.HANDLER.sendToPlayer(new UpdateThirstPacket(thirst.getThirst(), thirst.getHydration()), player);
        thirst.setLastThirst(thirst.getThirst());
        thirst.setLastHydrationZero(thirst.getHydration() == 0.0F);
    }

    // ------------------- Drink item -------------------
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        if (!ModConfig.thirst.enableThirst || !(event.getEntity() instanceof Player)
                || event.getEntity().level().isClientSide())
            return;

        Player player = (Player) event.getEntity();
        ItemStack drink = event.getItem();
        IThirst thirst = ThirstHelper.getThirst(player);

        if (drink.is(ModTags.Items.DRINKS)) {
            int drink_thirst = ModTags.Items.getThirstRestored(drink);
            float drink_hydration = 0.0F;
            float drink_poison_chance = 0.0F;

            if (drink.is(ModTags.Items.TEN_HYDRATION_DRINKS))
                drink_hydration = 0.1F;
            if (drink.is(ModTags.Items.TWENTY_HYDRATION_DRINKS))
                drink_hydration = 0.2F;
            if (drink.is(ModTags.Items.THIRTY_HYDRATION_DRINKS))
                drink_hydration = 0.3F;
            if (drink.is(ModTags.Items.FOURTY_HYDRATION_DRINKS))
                drink_hydration = 0.4F;
            if (drink.is(ModTags.Items.FIFTY_HYDRATION_DRINKS))
                drink_hydration = 0.5F;
            if (drink.is(ModTags.Items.SIXTY_HYDRATION_DRINKS))
                drink_hydration = 0.6F;
            if (drink.is(ModTags.Items.SEVENTY_HYDRATION_DRINKS))
                drink_hydration = 0.7F;
            if (drink.is(ModTags.Items.EIGHTY_HYDRATION_DRINKS))
                drink_hydration = 0.8F;
            if (drink.is(ModTags.Items.NINETY_HYDRATION_DRINKS))
                drink_hydration = 0.9F;
            if (drink.is(ModTags.Items.ONE_HUNDRED_HYDRATION_DRINKS))
                drink_hydration = 1.0F;

            // Tweak: tăng thêm 10% hydration cho các món đặc biệt
            drink_hydration *= 1.1F;

            if (drink.is(ModTags.Items.TWENTY_FIVE_POISON_CHANCE_DRINKS))
                drink_poison_chance = 0.25F;
            if (drink.is(ModTags.Items.FIFTY_POISON_CHANCE_DRINKS))
                drink_poison_chance = 0.5F;
            if (drink.is(ModTags.Items.SEVENTY_FIVE_POISON_CHANCE_DRINKS))
                drink_poison_chance = 0.75F;
            if (drink.is(ModTags.Items.ONE_HUNDRED_POISON_CHANCE_DRINKS))
                drink_poison_chance = 1.0F;

            thirst.drink(drink_thirst, drink_hydration);

            if (player.level().random.nextFloat() < drink_poison_chance) {
                player.addEffect(new MobEffectInstance(TANEffects.THIRST, 600));
            }
        }
    }

    // ------------------- Hand drinking -------------------
    private static final int IN_WORLD_DRINK_COOLDOWN = 2 * 20;
    private static int inWorldDrinkTimer = 0;

    public static void onUseBlock(PlayerInteractEvent.UseBlock event) {
        if (canHandDrink() && canHandDrinkInWorld(event.getPlayer(), event.getHand()))
            tryDrinkWaterInWorld(event.getPlayer());
    }

    public static void onUseEmpty(PlayerInteractEvent.UseEmpty event) {
        if (canHandDrink() && canHandDrinkInWorld(event.getPlayer(), event.getHand()))
            tryDrinkWaterInWorld(event.getPlayer());
    }

    public static void onClientTick(TickEvent.Client event) {
        if (inWorldDrinkTimer > 0)
            inWorldDrinkTimer--;
    }

    private static boolean canHandDrink() {
        return ModConfig.thirst.enableThirst && ModConfig.thirst.enableHandDrinking;
    }

    private static boolean canHandDrinkInWorld(Player player, InteractionHand hand) {
        return InteractionHand.MAIN_HAND == hand && player.getMainHandItem().isEmpty() && player.isCrouching()
                && ThirstHelper.getThirst(player).getThirst() < 20 && player.level().isClientSide()
                && inWorldDrinkTimer <= 0;
    }

    private static void tryDrinkWaterInWorld(Player player) {
        Level world = player.level();
        BlockHitResult rayTraceResult = Item.getPlayerPOVHitResult(player.level(), player, ClipContext.Fluid.SOURCE_ONLY);

        if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) rayTraceResult).getBlockPos();

            if (ThirstHelper.canDrink(player, false) && world.mayInteract(player, pos)
                    && world.getFluidState(pos).is(FluidTags.WATER)) {
                inWorldDrinkTimer = IN_WORLD_DRINK_COOLDOWN;
                ModPackets.HANDLER.sendToServer(new DrinkInWorldPacket(pos));
                player.playSound(SoundEvents.GENERIC_DRINK.value(), 0.5f, 1.0f);
                player.swing(InteractionHand.MAIN_HAND);
            }
        }
    }
}
