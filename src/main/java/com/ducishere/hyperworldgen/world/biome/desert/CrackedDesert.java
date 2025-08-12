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

public class CrackedDesert {
    public static Biome create() {
        // Spawn settings
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 100, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 30, 1, 2));

        // Generation settings
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DESERT_WELL);
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.SURFACE_STRUCTURES, Features.DESERT_PYRAMID);

        // Special visual effects
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xD1B080)
                .waterColor(0xCCAA88)
                .waterFogColor(0xA08866)
                .skyColor(0xF4C87C)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));

        return new BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(3.5F) // Rất nóng, dễ gây sốc nhiệt
                .downfall(0.0F)
                .temperatureModifier(TemperatureModifier.NONE)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }
}
