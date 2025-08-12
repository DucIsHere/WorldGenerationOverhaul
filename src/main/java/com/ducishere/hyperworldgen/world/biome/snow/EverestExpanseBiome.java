package com.hypergenworld.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.Biome.TemperatureModifier;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class EverestExpanseBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // Không có mob passive, chỉ có vài mob như cáo tuyết hoặc custom mob sống ở cao nguyên

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        // Không có cây, chỉ có băng, tuyết và đá lởm chởm

        return new Biome.Builder()
            .precipitation(Precipitation.SNOW)
            .temperature(-4.0F)
            .downfall(1.0F)
            .temperatureModifier(TemperatureModifier.FROZEN)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xADCBE3)
                .skyColor(0xAACDFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x0F0F1E)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(generationSettings.build())
            .build();
    }
}
