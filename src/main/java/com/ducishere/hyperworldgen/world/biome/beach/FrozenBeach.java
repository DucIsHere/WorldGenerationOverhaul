package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomePrecipitation;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class FrozenBeach {

    public static Biome createFrozenBeach() {
        // 1. Mob spawn settings
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnSettings.SpawnGroup.CREATURE, 
            new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.RABBIT, 5, 2, 5));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.MONSTER, 
            new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.ZOMBIE, 10, 2, 4));

        // 2. Terrain & feature generation
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings); // cát, sỏi
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);
        DefaultBiomeFeatures.addBeachGrass(generationSettings); // cỏ ven biển

        // 3. Effects
        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(7907327)
                .build();

        // 4. Build biome
        return new Biome.Builder()
                .precipitation(BiomePrecipitation.RAIN)
                .category(Biome.Category.BEACH)
                .depth(0.0f)
                .scale(0.025f)
                .temperature(-0.5f)
                .downfall(0.4f)
                .effects(effects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
