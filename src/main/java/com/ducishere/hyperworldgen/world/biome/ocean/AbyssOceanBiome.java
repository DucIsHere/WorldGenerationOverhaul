package com.ducishere.hyperworldgen.world.biome.ocean;

import net.minecraft.world.biome.*;

public class AbyssOceanBiome {
    public static Biome create() {
        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.2f) // lạnh ngắt
                .downfall(1.0f) // mưa max
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x050533) // xanh đen siêu tối
                        .waterFogColor(0x01010A) // gần như đen tuyệt đối
                        .fogColor(0x000000) // sương mù đen
                        .skyColor(0x0A0A33) // trời cũng đen xanh
                        .build())
                .spawnSettings(new SpawnSettings.Builder()
                        // Abyss biome có thể spawn drowned, guardian, squid
                        .spawn(SpawnGroup.WATER_AMBIENT,
                                new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.SQUID, 10, 2, 4))
                        .spawn(SpawnGroup.MONSTER,
                                new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.DROWNED, 20, 1, 2))
                        .spawn(SpawnGroup.MONSTER,
                                new SpawnSettings.SpawnEntry(net.minecraft.entity.EntityType.GUARDIAN, 8, 1, 2))
                        .build())
                .generationSettings(new GenerationSettings.Builder()
                        // Abyss: đáy biển toàn deepslate, gravel, basalt?
                        // chừa trống cho m custom cấu trúc (cột dung nham, wreck ship chìm sâu,...)
                        .build())
                .build();
    }
}
