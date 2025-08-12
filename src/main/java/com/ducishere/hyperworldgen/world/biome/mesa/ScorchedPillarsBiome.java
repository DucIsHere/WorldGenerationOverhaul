 package com.ducishere.hyperworldgen.common.biome.mesa;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class ScorchedPillarsBiome {
    
    public static Biome createBiome() {
        // Hiệu ứng màu sắc biome
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xCC6600)          // Màu sương mù cam cháy
            .waterColor(0xA06020)        // Màu nước nâu đỏ
            .waterFogColor(0x503020)     // Màu sương nước tối
            .build();

        // Cấu hình generation (ore, cây, cấu trúc)
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // TODO: Thêm ore terracotta, red sand, cây cactus, dead bush
        // generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.CACTUS);

        // Cấu hình spawn mob
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        // TODO: Thêm mob bình thường và mob hardcore đặc trưng

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE) // Không mưa
            .category(Biome.Category.DESERT)          // Loại biome desert
            .depth(1.0F)                              // Độ cao base cao, tạo địa hình dốc
            .scale(0.6F)                              // Độ biến đổi địa hình dốc hơn
            .temperature(1.8F)                        // Nhiệt độ cao nóng bỏng
            .downfall(0.0F)                          // Không mưa
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}