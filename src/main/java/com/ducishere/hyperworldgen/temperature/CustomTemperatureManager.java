package com.ducishere.hyperworldgen.temperature;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import cold_sweat.api.temperature.TemperatureHelper;
import cold_sweat.api.temperature.TemperatureLevel;
import com.ducishere.hyperworldgen.map.VietnamMap;

public class CustomTemperatureManager {

    public static void init() {
        TemperatureHelper.registerPositionalTemperatureModifier(CustomTemperatureManager::getTemperatureModifier);
    }

    public static TemperatureLevel getTemperatureModifier(Level level, BlockPos pos, TemperatureLevel current) {
        Biome biome = level.getBiome(pos).value();

        int y = pos.getY();
        if (VietnamMap.isFansipan(pos)) {
            return TemperatureLevel.ICE; // cực lạnh Fansipan
        } else if (y > 2000) {
            current = current.decrement(2);
        }

        if (VietnamMap.isTropical(biome)) current = current.increment(1);
        if (VietnamMap.isHighland(biome)) current = current.decrement(1);

        return current;
    }
}
