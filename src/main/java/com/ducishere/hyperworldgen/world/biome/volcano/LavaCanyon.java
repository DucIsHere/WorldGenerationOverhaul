package com.ducishere.hyperworldgen.common.biome.volcano;

import com.ducishere.hyperworldgen.common.feature.ModConfiguredFeatures;
import com.ducishere.hyperworldgen.common.feature.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;

public class LavaCanyonBiome {

    public static Biome create() {
        // Màu sắc / effects của biome
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0x7A2B0A)         // sương tro kiểu nâu đỏ
            .waterColor(0x2F1A0F)       // nước tối (nhiều than/lava gần đó)
            .waterFogColor(0x1C0E07)
            .skyColor(0x7F2F11)
            .build();

        // Generation settings: carve / features / ores / lava pools
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        // ví dụ: thêm ore, basalt columns, lava pockets, obsidian patches
        // (mày định nghĩa ModConfiguredFeatures / ModPlacedFeatures tương ứng)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.BASALT_VEIN);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModConfiguredFeatures.OBSIDIAN_PATCH);
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.LAVA_POCKET_PLACED);
        generationSettings.feature(GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.BASALT_COLUMN_CLUSTER);

        // Spawn settings: mob phù hợp với lava canyon
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 40, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 30, 1, 4));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 5, 1, 1)); // hiếm, nếu muốn

        // Tạo biome builder
        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.MESA)
            .depth(1.2F)         // base cao để tạo canyon sâu/hẻm
            .scale(0.9F)         // scale lớn -> địa hình gồ ghề/dốc
            .temperature(2.0F)
            .downfall(0.0F)
            .effects(effects)
            .generationSettings(generationSettings.build())
            .spawnSettings(spawnSettings.build())
            .build();
    }
}
