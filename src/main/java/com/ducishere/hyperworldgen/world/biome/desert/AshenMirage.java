package com.hyperworldgen.hypergen.biomes.desert;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.Builder;
import net.minecraft.data.worldgen.features.ConfiguredSurfaceBuilders;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

import yourmod.registry.ModEntities;
import yourmod.registry.ModPlacedFeatures;
import yourmod.registry.ModSounds;
import yourmod.world.carver.Carver;

public class AshenMirage {
    public static Biome create() {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
            .surfaceBuilder(ConfiguredSurfaceBuilders.DESERT)
            .addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.ASHEN_MIRAGE_TEMPLE)
            .addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.WITHERED_BUSH_PATCH)
            .addCarver(GenerationStep.Carver.AIR, Carver.ASHEN_CANYON);

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.creatureGenerationProbability(0.1F);
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 80, 2, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.SAND_SHADE, 20, 1, 2));

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .temperature(2.25F)
            .downfall(0.0F)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .waterColor(0x44403c)
                .waterFogColor(0x1e1b1a)
                .fogColor(0x7a6d64)
                .skyColor(0xffd8b5)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(ModSounds.MIRAGE_WIND)
                .build())
            .mobSpawnSettings(spawnSettings.build())
            .generationSettings(generationSettings.build())
            .build();
    }
}
