package com.hypergenworld.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeClimateSettings;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;

public class SandyFlat {
    public static Biome create() {
        // Spawn settings
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.creatureGenerationProbability(0.07F);
        // Add desert mobs
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 2, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 12, 2, 3));

        // Generation settings
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        genBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DESERT_WELL);

        // Climate / Effects
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xCDBF99)
                .waterColor(0xE3D5A5)
                .waterFogColor(0xB2A178)
                .skyColor(0xFFDD8D)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.0F) // Rất nóng ban ngày
                .downfall(0.0F)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .build();
    }
}
