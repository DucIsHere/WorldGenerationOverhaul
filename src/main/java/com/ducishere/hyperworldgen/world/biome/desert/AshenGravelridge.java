package com.hyperworldgen.biome.desert;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.biome.BiomeMaker;

public class AshenGravelridge {

    public static Biome createBiome() {
        BiomeAmbience effects = new BiomeAmbience.Builder()
            .setFogColor(0xC9BFA6)
            .setWaterColor(0xA9BDB1)
            .setWaterFogColor(0x909F9A)
            .setSkyColor(0xD6D1C0)
            .setFoliageColorOverride(0xC7BA91)
            .setGrassColorOverride(0xCABD9E)
            .build();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
            .surfaceBuilder(() -> ConfiguredSurfaceBuilders.DESERT);

        // Tạo vài ngọn núi thấp và thưa, ít khe nứt
        generationSettings
            .feature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Features.HILLS_PILES)
            .feature(GenerationStage.Decoration.SURFACE_STRUCTURES, Features.FOSSIL);

        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder();
        // Thêm spawn mob bình thường (hoặc không có mob tùy logic sau)

        return (new Biome.Builder())
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.35F) // hơi gồ ghề nhẹ
            .scale(0.2F)  // không quá hiểm trở
            .temperature(2.2F)
            .downfall(0.0F)
            .specialEffects(effects)
            .mobSpawnSettings(mobSpawnInfo.build())
            .generationSettings(generationSettings.build())
            .build();
    }
}
