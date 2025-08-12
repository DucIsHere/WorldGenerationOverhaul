package com.hypergenworld.world.biome;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

import static com.hypergenworld.HyperGenWorld.MOD_ID;

public class HyperSnowBiomes {

    public static void register(Registry<Biome> registry) {
        registry.register(new ResourceLocation(MOD_ID, "silent_glacier"), silentGlacier());
        registry.register(new ResourceLocation(MOD_ID, "frostgrave_peaks"), frostgravePeaks());
        registry.register(new ResourceLocation(MOD_ID, "veil_of_white_death"), veilOfWhiteDeath());
        registry.register(new ResourceLocation(MOD_ID, "glaciarch_abyss"), glaciarchAbyss());
    }

    
    private static Biome silentGlacier() {
        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-5.2f)
            .downfall(3.4f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xBCCED8)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x88AADD)
                .build())
            .mobSpawnSettings(MobSpawnSettings.EMPTY)
            .generationSettings(BiomeGenerationSettings.EMPTY)
            .build();
    }

    
    private static Biome frostgravePeaks() {
        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-4.6f)
            .downfall(3.7f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xC9DAE0)
                .waterColor(0x4169E1)
                .waterFogColor(0x0A0A33)
                .skyColor(0x90B7F2)
                .build())
            .mobSpawnSettings(MobSpawnSettings.EMPTY)
            .generationSettings(BiomeGenerationSettings.EMPTY)
            .build();
    }

    
    private static Biome veilOfWhiteDeath() {
        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-4.8f)
            .downfall(4.0f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xFFFFFF)
                .waterColor(0x5A93C5)
                .waterFogColor(0x010101)
                .skyColor(0xDDEEFF)
                .build())
            .mobSpawnSettings(MobSpawnSettings.EMPTY)
            .generationSettings(BiomeGenerationSettings.EMPTY)
            .build();
    }

    
    private static Biome glaciarchAbyss() {
        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-5.0f)
            .downfall(3.5f)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0x222244)
                .waterColor(0x112244)
                .waterFogColor(0x000000)
                .skyColor(0x112233)
                .build())
            .mobSpawnSettings(MobSpawnSettings.EMPTY)
            .generationSettings(BiomeGenerationSettings.EMPTY)
            .build();
    }
}
