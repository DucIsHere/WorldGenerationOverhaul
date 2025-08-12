package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class IcelitRidge {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.95f)
            .downfall(0.8f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.MOUNTAIN)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xE1FAFF)
                .skyColor(0xC2EEFF)
                .waterColor(0x4DB8D8)
                .waterFogColor(0x3D8CA5)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.icelit_ridge"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
