package com.ducishere.hyperworldgen.thirst;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.init.ModConfig;
import toughasnails.init.ModPackets;
import toughasnails.network.DrinkInWorldPacket;
import com.ducishere.hyperworldgen.seasons.SeasonManager;
import com.ducishere.hyperworldgen.farmerdelight.FDCompat;
import toughasnails.api.thirst.IThirst;

public class ThirstHandlerFullHardcore {

    private static final int IN_WORLD_DRINK_COOLDOWN = 3*20;
    private static int inWorldDrinkTimer = 0;

    public static void onPlayerTick(Player player){
        if(!ModConfig.thirst.enableThirst || player.level().isClientSide()) return;

        IThirst thirst = ThirstHelper.getThirst(player);

        double threshold = ModConfig.thirst.thirstExhaustionThreshold;
        if(thirst.getExhaustion() > threshold){
            thirst.addExhaustion((float)-threshold);
            if(thirst.getHydration() > 0) thirst.setHydration(Math.max(thirst.getHydration()-1F,0F));
            else thirst.setThirst(Math.max(thirst.getThirst()-1,0));
        }

        if(thirst.getThirst() <= 0){
            thirst.addTicks(1);
            if(thirst.getTickTimer() >= 80){
                if(player.getHealth() > 1.0F){
                    player.hurt(player.damageSources().source("thirst"),1.0F);
                }
                thirst.setTickTimer(0);
            }
        } else thirst.setTickTimer(0);
    }

    public static void onItemUseFinish(Player player, ItemStack drink){
        if(!ModConfig.thirst.enableThirst || player.level().isClientSide()) return;
        IThirst thirst = ThirstHelper.getThirst(player);

        if(FDCompat.isFDDrink(drink)){
            int drink_thirst = FDCompat.getThirstRestored(drink);
            float drink_hydration = FDCompat.getHydrationModifier(drink);
            thirst.drink(drink_thirst, drink_hydration);
            return;
        }

        if(drink.is(Items.POTION)){
            thirst.drink(4,1.0F);
        }
    }

    public static boolean canHandDrink(Player player, InteractionHand hand){
        return ModConfig.thirst.enableThirst &&
               ModConfig.thirst.enableHandDrinking &&
               hand == InteractionHand.MAIN_HAND &&
               player.getMainHandItem().isEmpty() &&
               player.isCrouching() &&
               ThirstHelper.getThirst(player).getThirst() < 20 &&
               inWorldDrinkTimer <= 0;
    }

    public static void tryDrinkWaterInWorld(Player player){
        Level world = player.level();
        BlockHitResult rayTraceResult = (BlockHitResult) player.pick(5.0D,0.0F,false);
        if(rayTraceResult.getType() != HitResult.Type.BLOCK) return;

        BlockPos pos = rayTraceResult.getBlockPos();
        if(world.getFluidState(pos).is(FluidTags.WATER)){
            inWorldDrinkTimer = IN_WORLD_DRINK_COOLDOWN;
            ModPackets.HANDLER.sendToServer(new DrinkInWorldPacket(pos));
            player.playSound(player.getDrinkingSound(Items.POTION),0.5F,1.0F);
            player.swing(InteractionHand.MAIN_HAND);
        }
    }

    public static void onClientTick(){
        if(inWorldDrinkTimer>0) inWorldDrinkTimer--;
    }
}