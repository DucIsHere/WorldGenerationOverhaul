package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.world.level.biome.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;

public class BlizzardCrown {
    public static Biome create() {
        return new Biome.BiomeBuilder()
            .temperature(-0.95f)
            .downfall(1.0f)
            .precipitation(Biome.Precipitation.SNOW)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .biomeCategory(Biome.BiomeCategory.ICY)
            .specialEffects(new BiomeSpecialEffects.Builder()
                .fogColor(0xA0AFC0)               // Xám xanh đậm, giảm tầm nhìn
                .skyColor(0xA0B8C0)               // Trời xám mờ
                .waterColor(0x405060)             // Màu nước lạnh, tối
                .waterFogColor(0x304050)          // Màu sương nước
                .backgroundMusic(new Music(
                    new ResourceLocation("hypergenworld", "music.blizzard"),
                    6000, 12000, false
                ))
                .build())
            .mobSpawnSettings(mobSettings())
            .generationSettings(genSettings())
            .build();
    }

    private static MobSpawnSettings mobSettings() {
        return new MobSpawnSettings.Builder()
            .build(); // Có thể thêm stray hoặc custom mob tuyết sau
    }

    private static BiomeGenerationSettings genSettings() {
        return new BiomeGenerationSettings.Builder()
            .build(); // Tạm thời trống, sau có thể thêm snow layer, cây phủ tuyết, particle
    }
}
