package com.ducishere.hyperworldgen.server;

import com.ducishere.hyperworldgen.common.reward.RewardNetwork;
import com.ducishere.hyperworldgen.common.util.PlayerData;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ChallengeCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("challenge")
                .then(CommandManager.literal("test")
                    .executes(context -> {
                        ServerCommandSource source = context.getSource();
                        ServerPlayerEntity player = source.getPlayer();

                        if (player != null) {
                            // Láº¥y data cá»§a player
                            PlayerData data = PlayerData.load(player);

                            // ThÃªm coin & lÆ°u
                            data.addCryoCoins(10);
                            PlayerData.save(player, data);

                            // Gá»­i quÃ 
                            ItemStack rewardItem = new ItemStack(Items.DIAMOND, 3);
                            player.getInventory().insertStack(rewardItem);

                            // Gá»­i UI popup
                            RewardNetwork.sendRewardNotification(player, rewardItem, "Nháº­n 3 Kim CÆ°Æ¡ng!");

                            source.sendFeedback(Text.literal("Â§aÄÃ£ test reward popup!"), false);
                        }

                        return 1;
                    })
                )
            );
        });
    }
}
