package com.hyperworldgen.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;

public class DuneCrater {

    public static Biome create() {
        // Hiệu ứng biome – sắc cam vàng cháy nắng + sương bụi
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xD1A760)
                .waterColor(0xD6B15C)
                .waterFogColor(0xC49E4D)
                .skyColor(0xE6C49F)
                .ambientMoodSound(BiomeSpecialEffects.AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        // Mob spawn – rất ít, chủ yếu là ambient hoặc custom desert mob
        MobSpawnSettings mobSpawn = new MobSpawnSettings.Builder()
                .build();

        // Generation – có thể thêm crater structure, đá sụp, hóa thạch
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
                .build();

        return new Biome.BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(1.95F) // nóng hơn Bonecrest
                .downfall(0.0F)
                .temperatureAdjustment(TemperatureModifier.NONE)
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawn)
                .generationSettings(generation)
                .biomeCategory(BiomeCategory.DESERT)
                .build();
    }
}
