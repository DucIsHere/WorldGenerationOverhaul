package com.hyperworldgen.biome.desert;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.*;

import java.util.Optional;

public class SandstormBasin {

    public static final ResourceKey<Biome> SANDSTORM_BASIN = ResourceKey.create(
            Registries.BIOME, new ResourceLocation("hyperworldgen", "sandstorm_basin"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(SANDSTORM_BASIN, createSandstormBasinBiome());
    }

    private static Biome createSandstormBasinBiome() {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC2B280) // màu cát nhạt
                .waterColor(0xE0CDA9)
                .waterFogColor(0xD2B48C)
                .skyColor(0xFFE4B5)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(new ResourceLocation("minecraft", "music.desert")))
                .build();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultDesertVegetation(generationSettings);

        // Tuỳ chọn thêm feature: cát lắng tụ / lớp bụi gió
        // generationSettings.addFeature(...);

        MobSpawnSettings.Builder mobSpawns = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobSpawns);
        BiomeDefaultFeatures.desertSpawns(mobSpawns);

        return new Biome.BiomeBuilder()
                .temperature(2.0F) // nóng cao
                .downfall(0.0F) // không mưa
                .specialEffects(effects)
                .mobSpawnSettings(mobSpawns.build())
                .generationSettings(generationSettings.build())
                .precipitation(Biome.Precipitation.NONE)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }
}
