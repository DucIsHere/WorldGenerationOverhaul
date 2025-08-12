package com.hypergenworld.biome.desert;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.BiomeBuilder;
import net.minecraft.data.worldgen.Features;

public class GleamingMirageDesert {
    public static Biome create() {
        // Spawn settings – ít mob nhưng lạ
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 1));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 5, 1, 2));

        // Generation settings – cây sáng, thảm phát sáng (custom feature khuyên dùng sau này)
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
        genBuilder.addFeature(net.minecraft.data.worldgen.GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        // Có thể thêm custom plant sau: glowing cactus, shimmer grass...

        // Special effects
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xDCC6F8) // Fog tím ánh hồng
                .waterColor(0xB8E3F2)
                .waterFogColor(0xEAD1FA)
                .skyColor(0xFFDDFF)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Music.createGameMusic(SoundEvents.MUSIC_DISC_STRAD)); // Gợi cảm giác mộng ảo

        return new BiomeBuilder()
                .precipitation(Precipitation.NONE)
                .temperature(1.85F) // Vẫn nóng nhưng không cực đoan
                .downfall(0.0F)
                .temperatureModifier(TemperatureModifier.NONE)
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }
}
