package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VegetableWildBiome {

    public static final RegistryKey<Biome> VEGETABLE_WILD = RegistryKey.of(RegistryKeys.BIOME, new Identifier("modid", "vegetable_wild"));

    public static Biome createVegetableWild() {
        // Biome builder
        Biome.Builder builder = new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN)
            .category(Biome.Category.PLAINS)
            .depth(0.125F)
            .scale(0.05F)
            .temperature(0.7F)
            .downfall(0.8F)
            .effects(new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(calculateSkyColor(0.7F))
                .grassColorModifier(BiomeEffects.GrassColorModifier.NONE)
                .build()
            );

        // Add default terrain feature: dirt, grass
        builder.generationSettings(
            new GenerationSettings.Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        Blocks.DIRT.getDefaultState(),
                        Blocks.DIRT.getDefaultState()
                ))
                // Add vegetable patches
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, 
                    Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.createVegetationPatchConfig(
                        Blocks.CARROTS.getDefaultState(), 64))
                    .decorate(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new CountPlacementModifier(5)))
                )
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, 
                    Feature.RANDOM_PATCH.configure(DefaultBiomeFeatures.createVegetationPatchConfig(
                        Blocks.POTATOES.getDefaultState(), 64))
                    .decorate(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new CountPlacementModifier(4)))
                )
                .feature(GenerationStep.Feature.VEGETAL_DECORATION, 
                    Feature.FLOWER.configure(DefaultBiomeFeatures.createRandomFlowerPatch())
                    .decorate(Placement.COUNT_HEIGHTMAP_32.configure(new CountPlacementModifier(10)))
                )
                .build()
        );

        // Spawn entities
        builder.spawnSettings(
            new SpawnSettings.Builder()
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 2, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 5, 1, 2))
                .build()
        );

        return builder.build();
    }

    private static int calculateSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
}