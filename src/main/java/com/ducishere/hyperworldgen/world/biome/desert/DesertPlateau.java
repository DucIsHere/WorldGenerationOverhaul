package com.hyperworldgen.biomes.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeBuilder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;

public class DesertPlateau {

    public static Biome createBiome() {
        // Cấu hình spawn mob
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.creatureGenerationProbability(0.05F); // spawn ít
        mobSpawnSettings.addSpawn(net.minecraft.world.entity.MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(
                net.minecraft.world.entity.monster.Husk.class, 10, 1, 2));

        // Cấu hình terrain + feature
        BiomeGenerationSettings.Builder biomeGenSettings = new BiomeGenerationSettings.Builder();

        // Hiệu ứng biome
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC0D8FF)
                .waterColor(0xE0A060)
                .waterFogColor(0xA08050)
                .skyColor(0xFFD27F)
                .ambientMoodSound(BiomeSpecialEffects.MoodSound.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_BADLANDS));

        return new BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.0F) // Nhiệt độ cao ban ngày
                .downfall(0.0F)
                .specialEffects(effects.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenSettings.build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }
}
