package com.hypergenworld.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.BiomeBuilder;

public class RedDuneBasin {
    public static Biome create() {
        // Spawn settings
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 90, 2, 3));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 20, 1, 2));

        // Generation settings
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DESERT_WELL);
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS, Features.FOSSIL_LOWER);

        // Special visual effects
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xAA4A2E) // Cát đỏ – hiệu ứng bụi nóng
                .waterColor(0xB86E4B)
                .waterFogColor(0xAA5A3F)
                .skyColor(0xFF9966)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_BADLANDS));

        return new BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(2.4F) // Cực nóng, ban ngày có thể gây hiệu ứng cháy da nếu đứng lâu
                .downfall(0.0F)
                .temperatureModifier(TemperatureModifier.NONE)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }
}
