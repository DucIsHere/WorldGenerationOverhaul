package com.hypergenworld.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.Biome.TemperatureModifier;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class NepalMountainBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // Spawn gấu tuyết, sói tuyết, hoặc custom mob sinh tồn ở độ cao cực lạnh
        // spawnSettings.spawn(...);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        // Thêm terrain núi cao, cây kim nhọn, băng, tuyết sâu
        // generationSettings.feature(...);

        return new Biome.Builder()
            .precipitation(Precipitation.SNOW)
            .temperature(-6.5F) // Khí hậu lạnh cực đại
            .downfall(1.0F)
            .temperatureModifier(TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xA0C8FF)
                .skyColor(0x88CCFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x0A0A2A)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(generationSettings.build())
            .build();
    }
