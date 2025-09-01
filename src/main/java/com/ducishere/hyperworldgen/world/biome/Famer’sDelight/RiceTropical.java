package com.ducishere.hyperworldgen.world.biome.Famer'sDelight;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public static Biome createRiceTropical() {
    BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder();

    // Thêm wild rice vào vegetation
    generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
        ModPlacedFeatures.WILD_RICE_PLACED);

    return new Biome.BiomeBuilder()
            .temperature(0.8F)
            .downfall(0.9F)
            .precipitation(Biome.Precipitation.RAIN)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .waterColor(0x3F76E4)
                    .waterFogColor(0x050533)
                    .fogColor(0xC0D8FF)
                    .skyColor(0x77ADFF)
                    .build())
            .generationSettings(generation.build())
            .mobSpawnSettings(new MobSpawnSettings.Builder().build())
            .build();
}

}
