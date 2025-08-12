package com.hypergenworld.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.BiomeBuilder;
import net.minecraft.world.level.biome.AmbientMoodSettings;

public class CrackedWastesDesert {
    public static Biome create() {
        // Spawn settings – không có mob thân thiện
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 30, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 10, 1, 2));

        // Generation settings – không cây, toàn đá vụn và nứt đất
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        // Có thể thêm custom crack hoặc patch đá gãy sau

        // Special effects
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC9B58C) // Khô khốc, bụi bặm
                .waterColor(0xE6D7A3)
                .waterFogColor(0xD6B984)
                .skyColor(0xFFDA8B)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_DISC_PIGSTEP)); // Gợi sự căng thẳng

        return new BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(2.9F) // Cực kỳ nóng
                .downfall(0.0F)
                .temperatureModifier(TemperatureModifier.NONE)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }
}
