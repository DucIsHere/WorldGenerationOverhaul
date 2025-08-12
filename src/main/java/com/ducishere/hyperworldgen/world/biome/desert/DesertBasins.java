package com.hyperworldgen.biomes.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeBuilder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Husk;

public class DesertedBasins {

    public static Biome createBiome() {
        // Mob: Husk hiếm
        MobSpawnSettings.Builder mobSpawnSettings = new MobSpawnSettings.Builder();
        mobSpawnSettings.creatureGenerationProbability(0.02F); // rất hiếm mob
        mobSpawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(
                Husk.class, 6, 1, 1)); // xuất hiện đơn độc

        // Terrain: có thể thêm basin structure sau
        BiomeGenerationSettings.Builder biomeGenSettings = new BiomeGenerationSettings.Builder();

        // Visual & Ambience
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xD9C6A5)
                .waterColor(0xC1A264)
                .waterFogColor(0xA78E6D)
                .skyColor(0xD4AF7F)
                .ambientMoodSound(BiomeSpecialEffects.MoodSound.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_END));

        return new BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(4.5F)
                .downfall(0.0F)
                .specialEffects(effects.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(biomeGenSettings.build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }
}
