package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registerable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.structure.StructureSpawnOverride;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import java.util.List;
import java.util.function.Supplier;

public class Frozen_Ocean {
  public static Biome create() {
    SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
    spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 5, 1, 2));
    spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GURDIAN, 3, 1, 2));
    spawnBuilder.spawn(SpawnGrouo.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE,4, 1, 2,));

    GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();
    genBuilder.surfaceBuilder(ConfiguredSurfaceBuilders.OCEAN);

    return Biome.Builder()
      .precipitaion(Biome.Precipitation.NONE)
      .temperature(0.5F)
      .downfall(1.0F)
      .effects(new BiomeEffects.Builder()
          .waterColor(4020182)
          .waterFogColor(329011)
          .build())
      .spawnSettings(spawnBuilder.build())
      .generationSettings(genBuilder.build())
      .build();
    
      }
}
