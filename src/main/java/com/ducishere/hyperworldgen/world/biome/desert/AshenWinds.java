package com.hyperworldgen.biomes.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeClimateSettings;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biomes;

public class DesertedBasins {

    public static Biome createDesertedBasinsBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder mobSpawn = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Biome visual & climate effects
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xC6B07F) // Màu sương khô cằn
            .waterColor(0xE1D9B8)
            .waterFogColor(0xADA580)
            .skyColor(0xFFEBC1)
            .grassColorOverride(0xBFB26D)
            .foliageColorOverride(0xA9A05F)
            .ambientMoodSound(BiomeSpecialEffects.MoodSound.LEGACY_CAVE_SETTINGS)
            .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .build();

        // Thêm cấu trúc, cây cối hoặc đặc điểm riêng ở đây nếu muốn

        return new Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(2.7f)
            .downfall(0.0f)
            .specialEffects(effects)
            .mobSpawnSettings(mobSpawn.build())
            .generationSettings(generation.build())
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .build();
    }
}
