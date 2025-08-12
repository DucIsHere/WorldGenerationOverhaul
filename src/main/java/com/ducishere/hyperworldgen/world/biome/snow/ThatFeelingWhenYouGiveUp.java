package com.hypergenworld.worldgen.biomes.Snow;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.*;

public class ThatFeelingWhenYouGiveUp {

    /**
     * Biome "Bất Lực Thật Rồi"
     * Một biome đặc biệt tượng trưng cho trạng thái kiệt sức, cô lập, tâm lý rơi tự do.
     * Dùng để đặt ẩn trong hệ worldgen – hoặc làm biome thử thách riêng.
     */
    public static Biome create() {
        // Ambient visuals & mood
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xAABBDD)                // sương màu xám xanh
            .skyColor(0xDDEEFF)                // trời nhạt
            .waterColor(0x445566)              // nước xám tro
            .waterFogColor(0x223344)           // sương dưới nước
            .ambientMoodSound(BiomeSpecialEffects.MoodSound.AMBIENT_CAVE)
            .backgroundMusic(new Music(
                new ResourceLocation("hypergenworld", "music.batluc"), // nhớ khai báo trong sounds.json
                0, 24000, false
            ))
            .build();

        // Worldgen settings – trống để thể hiện sự "chết"
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build();

        // Spawn settings – không có mob
        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
            .build();

        // Build final biome object
        return new Biome.BiomeBuilder()
            .temperature(-0.99f) // cực lạnh
            .downfall(1.0f)      // tuyết rơi liên tục
            .precipitation(Biome.Precipitation.SNOW)
            .biomeCategory(Biome.BiomeCategory.EXTREME_HILLS)
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .specialEffects(effects)
            .mobSpawnSettings(spawns)
            .generationSettings(generation)
            .build();
    }
}
