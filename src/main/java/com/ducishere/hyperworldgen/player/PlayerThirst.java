package com.ducishere.hyperworldgen.player;

import net.minecraft.world.entity.player.Player;

public class PlayerThirst {
    private int thirst = 100; // 0 - 100

    public void tick(Player player) {
        // giảm theo biome + di chuyển
        int biomeRate = getBiomeThirstRate(player);
        thirst -= biomeRate;
        if (player.isSprinting()) thirst -= 1;

        thirst = Math.max(0, Math.min(thirst, 100)); // clamp
    }

    public void addThirst(int value) { thirst = Math.min(thirst + value, 100); }
    public int getThirst() { return thirst; }

    private int getBiomeThirstRate(Player player) {
        float temp = player.level.getBiome(player.blockPosition()).getTemperature();
        if (temp > 30) return 2; // nóng → mất nhanh
        if (temp < 0) return 1;  // lạnh → mất ít
        return 1;
    }
}
