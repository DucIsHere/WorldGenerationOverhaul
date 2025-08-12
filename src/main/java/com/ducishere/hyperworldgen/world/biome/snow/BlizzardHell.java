package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class BlizzardHell {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-4.55f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xF0F8FF)
                .skyColor(0xCFEFFF)
                .waterColor(0x80D0F0)
                .waterFogColor(0x4DA7C3)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.blizzard_hell"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
