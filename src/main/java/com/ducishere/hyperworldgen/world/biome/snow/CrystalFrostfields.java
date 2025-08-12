package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class CrystalFrostfields {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.5f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xCCF9FF)
                .skyColor(0xD9F9FF)
                .waterColor(0x77DDEE)
                .waterFogColor(0x55BBCC)
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.crystal"),
                    8000, 16000, false
                ))
                .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .generationSettings(new BiomeGenerationSettings.Builder().build())
            .build();
    }
}
