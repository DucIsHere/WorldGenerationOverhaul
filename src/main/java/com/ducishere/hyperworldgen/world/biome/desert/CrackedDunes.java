package com.hyperworldgen.biomes.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeBuilder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Spider;

public class CrackedDunes {

    public static Biome createBiome() {
        // Cấu hình spawn mob
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.creatureGenerationProbability(0.03F); // rất ít mob
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(
                Spider.class, 8, 1, 2));

        // Cấu hình địa hình và feature
        BiomeGenerationSettings.Builder biomeGenSettings = new BiomeGenerationSettings.Builder();

        // Hiệu ứng môi trường
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xFFD6A3) // màu sương nắng sa mạc
                .waterColor(0xE0B874)
                .waterFogColor(0xC69C6D)
                .skyColor(0xFFBA75)
                .ambientMoodSound(BiomeSpecialEffects.MoodSound.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));

        return new BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.1F)
                .downfall(0.0F)
                .specialEffects(effects.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenSettings.build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }
}
