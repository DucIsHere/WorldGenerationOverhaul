package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomePrecipitation;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class GrowthOldBirch {

    public static Biome createGrowthOldBirch() {
        // 1. Thêm mob spawn settings
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnSettings.SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.SHEEP, 12, 4, 4));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.ZOMBIE, 95, 4, 4));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.CHICKEN, 15, 5, 7));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.COW, 5, 7, 7));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.CREEPER, 7, 8, 2));
        spawnSettings.spawn(SpawnSettings.SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.SPIDER, 75, 9, 5));

        // 2. Thêm terrain & feature generation
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings); // cát, sỏi
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings);

        // 3. Thêm effects
        BiomeEffects effects = new BiomeEffects.Builder()
                .skyColor(7907327) // màu trời
                .waterColor(4159204) // màu nước
                .waterFogColor(329011) // màu fog dưới nước
                .fogColor(12638463) // màu fog
                .moodSound(BiomeMoodSound.CAVE) // âm thanh
                .build();

        // 4. Build biome
        return new Biome.Builder()
                .precipitation(BiomePrecipitation.RAIN) // kiểu mưa: RAIN/SNOW/NONE
                .category(Biome.Category.PLAINS) // loại biome
                .depth(0.125f) // độ cao đất
                .scale(0.05f) // độ gập ghềnh
                .temperature(0.8f) // nhiệt độ
                .downfall(0.4f) // lượng mưa
                .effects(effects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
