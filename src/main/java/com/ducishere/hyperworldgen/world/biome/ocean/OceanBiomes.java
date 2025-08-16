package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class OceanBiomes {

    // ===== COLD OCEAN =====
    public static Biome createColdOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Squid: hay có ở ocean lạnh
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 10, 2, 4));
        // Cod: cá tuyết sống trong lạnh
        spawnBuilder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.COD, 15, 3, 6));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x223366)
                .waterFogColor(0x112244)
                .skyColor(0x77aaff)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.2f)
                .downfall(0.3f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    // ===== WARM OCEAN =====
    public static Biome createWarmOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Tropical Fish: cá nhiệt đới sống nhiều
        spawnBuilder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 25, 4, 8));
        // Dolphin: cá heo ít gặp
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 2, 1, 2));
        // Pufferfish: cá nóc
        spawnBuilder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.PUFFERFISH, 5, 1, 2));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x1ca3ec)
                .waterFogColor(0x145374)
                .skyColor(0x88ddff)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(1.0f)
                .downfall(0.8f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    // ===== MEDIUM OCEAN =====
    public static Biome createMediumOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Squid: cơ bản
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 10, 2, 4));
        // Salmon: cá hồi sống biển vừa
        spawnBuilder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.SALMON, 12, 2, 5));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x336688)
                .waterFogColor(0x224466)
                .skyColor(0x99ccff)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.7f)
                .downfall(0.5f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    // ===== COAL OCEAN =====
    public static Biome createCoalOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Squid nhiều hơn
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 15, 3, 6));
        // Cod
        spawnBuilder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.COD, 20, 3, 6));
        // Drowned: quái dưới nước
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 8, 1, 2));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x222222) // đen xám
                .waterFogColor(0x111111)
                .skyColor(0x555555)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.4f)
                .downfall(0.6f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    // ===== DEEP OCEAN =====
    public static Biome createDeepOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Guardian xuất hiện ở ocean sâu
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GUARDIAN, 10, 1, 3));
        // Drowned nhiều
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 15, 2, 4));
        // Squid cũng xuất hiện
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 10, 2, 4));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x000033) // xanh đậm
                .waterFogColor(0x000022)
                .skyColor(0x222244)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.3f)
                .downfall(0.5f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }

    // ===== ABYSS OCEAN =====
    public static Biome createAbyssOcean() {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        // Guardian rất nhiều
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GUARDIAN, 20, 2, 4));
        // Elder Guardian hiếm nhưng có thể thêm
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ELDER_GUARDIAN, 1, 1, 1));
        // Drowned tràn lan
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.DROWNED, 20, 3, 6));
        // Squid ít
        spawnBuilder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 5, 1, 2));

        GenerationSettings.Builder genBuilder = new GenerationSettings.Builder();

        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x000000) // đen tuyệt đối
                .waterFogColor(0x000011)
                .skyColor(0x000000)
                .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.1f)
                .downfall(0.2f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(genBuilder.build())
                .build();
    }
}
