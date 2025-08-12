package com.hyperworldgen.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;

public class ScorchedPillars {

    public static Biome create() {
        // Hiệu ứng đặc biệt của biome
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC97F4E) // màu bụi mờ
                .waterColor(0xE08030)
                .waterFogColor(0xC45A25)
                .skyColor(0xFF9966)
                .ambientMoodSound(BiomeSpecialEffects.AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        // Mob spawn: giữ mức thấp nhưng vẫn có để người chơi cảnh giác
        MobSpawnSettings mobSpawn = new MobSpawnSettings.Builder()
                .build();

        // Địa hình: sa mạc bị gió bào mòn, tạo cột đá
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
                // Sẽ thêm cấu trúc trụ đá ở bước sau
                .build();

        return new Biome.BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(2.15F) // nhiệt độ rất cao ban ngày
                .downfall(0.0F)
                .temperatureAdjustment(TemperatureModifier.NONE)
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawn)
                .generationSettings(generation)
                .biomeCategory(BiomeCategory.DESERT)
                .build();
    }
}
