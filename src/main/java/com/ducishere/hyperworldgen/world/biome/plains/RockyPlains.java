package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;

public class CrystalPlainsBiome {
    public static Biome create() {
        // Các hiệu ứng môi trường
        BiomeEffects effects = new BiomeEffects.Builder()
                .skyColor(0x77ADFF) // màu trời
                .fogColor(0xC0D8FF) // màu sương
                .waterColor(0x3F76E4) // màu nước
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.PLAINS)
                .build();

        // Cài đặt spawn mob
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.creatureSpawnProbability(0.1f);
        spawnSettings.spawn(SpawnGroup.CREATURE,
                new SpawnSetiigs.SpawnEntry(net.minecraft.entity.EtityType.COW, 10, 2, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE,
                new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.PIG, 10, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE,
                new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.CHICKEN, 10, 3, 5));
       

        // Cài đặt feature (cây, quặng…)
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addPlainsFeatures(generationSettings); // copy feature biome plains
        DefaultBiomeFeatures.addDefaultOres(generationSettings);

        // Build biome
        return new Biome.Builder()
                .precipitation(true) // có mưa
                .temperature(0.2f)
                .downfall(0.5)
                .effects(effects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
