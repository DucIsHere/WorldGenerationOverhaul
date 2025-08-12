package com.hyperworldgen.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;

public class Bonecrest {

    public static Biome create() {
        // Hiệu ứng biome - tone xám cát nhạt
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xBBAA99)
                .waterColor(0xC2B280)
                .waterFogColor(0xB5A173)
                .skyColor(0xD9C2A1)
                .ambientMoodSound(BiomeSpecialEffects.AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        // Mob spawn setting: mob ít nhưng có thể thêm Skeleton hoặc Stray
        MobSpawnSettings mobSpawn = new MobSpawnSettings.Builder()
                .build();

        // Generation settings: cấu trúc hóa thạch, bãi đá, khe gió
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
                .build();

        return new Biome.BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(1.85F) // nóng vừa phải nhưng khô khốc
                .downfall(0.0F)
                .temperatureAdjustment(TemperatureModifier.NONE)
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawn)
                .generationSettings(generation)
                .biomeCategory(BiomeCategory.DESERT)
                .build();
    }
}
