package com.ducishere.hyperworldgen.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public class PlayerTemperature {
    private float temperature = 37f; // cơ thể

    public void tick(Player player) {
        Biome biome = player.level.getBiome(player.blockPosition());
        float biomeTemp = biome.getTemperature();

        float tempChange = (biomeTemp - temperature) * 0.05f;

        // kiểm tra block gần player
        if (nearCryoLantern(player)) tempChange -= 5f;
        if (nearFire(player)) tempChange += 5f;

        temperature += tempChange;

        // apply effect
        if (temperature < 0) applyFrostbite(player);
        if (temperature > 40) applyHeatExhaustion(player);
    }

    private void applyFrostbite(Player player) { /* ModEffects.FROSTBITE */ }
    private void applyHeatExhaustion(Player player) { /* ModEffects.HEAT_EXHAUSTION */ }
}
