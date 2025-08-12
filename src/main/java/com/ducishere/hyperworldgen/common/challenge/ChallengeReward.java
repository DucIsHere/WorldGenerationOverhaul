package com.ducishere.hyperworldgen.common.challenge;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class ChallengeReward {

    private final ItemStack itemReward;
    private final int xpReward;
    private final int cryoCoins; // Ví dụ: tiền tệ riêng của mod

    public ChallengeReward(ItemStack itemReward, int xpReward, int cryoCoins) {
        this.itemReward = itemReward;
        this.xpReward = xpReward;
        this.cryoCoins = cryoCoins;
    }

    public void giveToPlayer(ServerPlayerEntity player) {
        if (itemReward != null && !itemReward.isEmpty()) {
            player.giveItemStack(itemReward.copy());
        }
        if (xpReward > 0) {
            player.addExperience(xpReward);
        }
        if (cryoCoins > 0) {
            // TODO: tích hợp hệ thống tiền của mày, ví dụ lưu vào PlayerData
            player.sendMessage(
                net.minecraft.text.Text.literal("Bạn nhận được " + cryoCoins + " Cryo Coin!"), false
            );
        }
    }
}