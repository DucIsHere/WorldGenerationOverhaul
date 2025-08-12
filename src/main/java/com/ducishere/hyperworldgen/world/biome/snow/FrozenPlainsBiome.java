package com.hypergenworld.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeWeather;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.Biome.TemperatureModifier;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.registry.Holder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.ColorHelper;

public class FrozenPlainsBiome {

    public static Biome create() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // Thêm mob spawn ở đây nếu cần
        // spawnSettings.spawn(...);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        // Thêm cấu trúc, cây, terrain ở đây nếu cần
        // generationSettings.feature(...);

        return new Biome.Builder()
            .precipitation(Precipitation.SNOW)
            .temperature(-2.0F)
            .downfall(0.9F)
            .temperatureModifier(TemperatureModifier.NONE)
            .effects(new BiomeEffects.Builder()
                .fogColor(0xC0D8FF)
                .skyColor(0x77ADFF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .moodSound(BiomeMoodSound.CAVE)
                .build())
            .spawnSettings(spawnSettings.build())
            .generationSettings(generationSettings.build())
            .build();
    }
}
