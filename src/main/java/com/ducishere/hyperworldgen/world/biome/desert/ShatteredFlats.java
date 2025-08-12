package com.hypergen.biomes.desert;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeatures;

public class ShatteredFlats {

    public static final ResourceKey<Biome> SHATTERED_FLATS = ResourceKey.create(
            Registries.BIOME,
            new ResourceLocation("hypergen", "shattered_flats")
    );

    public static Biome create() {
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

        // Add default desert features
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacedFeatures.DEAD_BUSH);
        generation.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, PlacedFeatures.DESERT_WELL);

        // 🎲 Add custom structure / fantasy:
        // Giả sử m đã đăng ký trong data/placed_features
        generation.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES,
                PlacedFeature.placedFeature(new ResourceLocation("hypergen", "fissure_totem")));

        generation.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES,
                PlacedFeature.placedFeature(new ResourceLocation("hypergen", "obsidian_shard")));

        // Mob spawn
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 70, 2, 4));
        spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMITE, 15, 1, 2)); // fantasy
        spawns.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 10, 1, 2));

        // Nếu có custom entity: AncientScorpion, DustGolem thì add tại đây

        // Biome Special Effects
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC59C71)
                .waterColor(0xC0B283)
                .waterFogColor(0xA6884B)
                .skyColor(0xEAC57E)
                .grassColorOverride(0xB57E3A)
                .foliageColorOverride(0x9C6E32)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(null) // Có thể thêm music fantasy riêng
                .build();

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(3.05f)
                .downfall(0.0f)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }
}
