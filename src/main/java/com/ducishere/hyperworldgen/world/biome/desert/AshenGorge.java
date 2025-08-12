package com.ducdev.hyperworldgen.biomes.desert;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BiomeSpawnSettings;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.registry.Registry;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryEntry;

public class HyperWorldGenDesert {

    public static final RegistryKey<Biome> ASHEN_GORGE = RegistryKey.of(Registry.BIOME_KEY, new Identifier("hyperworldgen", "ashen_gorge"));

    public static Biome createAshenGorge() {
        BiomeEffects effects = new BiomeEffects.Builder()
                .fogColor(0x3A2C2C)              // tro bụi màu xám nâu
                .waterColor(0x8C5E3C)
                .waterFogColor(0x5C3B26)
                .skyColor(0x7A5A3A)
                .grassColor(0x9E6D4A)
                .foliageColor(0xA87C58)
                .moodSound(BiomeMoodSound.CAVE)
                .build();

        BiomeSpawnSettings.Builder spawnBuilder = new BiomeSpawnSettings.Builder();
        // Ít mob, chủ yếu là mob sống sót trong điều kiện khắc nghiệt
        // Tùy bạn thêm custom mob

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.3F) // rất nóng
                .downfall(0.0F)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
