package com.hypergenworld.common.weather;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hypergenworld.registry.ModBiomes.BLIZZARD_HELL;

@Mod.EventBusSubscriber
public class BlizzardOverrider {

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (!(event.world instanceof ServerLevel level)) return;

        Biome current = level.getBiome(level.getSharedSpawnPos()).value();
        if (current.equals(BLIZZARD_HELL.get())) {
            level.setWeatherParameters(0, 1000000, true, true);
        }
    }
}
