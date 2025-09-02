package com.ducishere.hyperworldgen.world.noise;

import java.util.HashMap;
import java.util.Map;
import com.github.lunatrius.core.noise.FastNoiseLite;

public class AllBiomesNoiseProFull {

    private final FastNoiseLite biomeNoise, heightNoise, terraceNoise, warpNoise, ridgeNoise;

    private final double seaLevel = 75;
    private final double oceanFloor = -7500;

    private static class BiomeHeight {
        double min, avg, max;
        BiomeHeight(double min, double avg, double max) {
            this.min = min;
            this.avg = avg;
            this.max = max;
        }
    }

    private final Map<String, BiomeHeight> biomeHeights = new HashMap<>();
    private final Map<String, Double> biomeThresholds = new HashMap<>();
    private final Map<String, String[]> biomeBlocks = new HashMap<>();

    public AllBiomesNoiseProFull(long seed) {
        // ----- Noise setup -----
        biomeNoise = new FastNoiseLite((int) seed);
        biomeNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        biomeNoise.SetFrequency(0.01f);

        heightNoise = new FastNoiseLite((int) (seed + 1));
        heightNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        heightNoise.SetFrequency(0.02f);

        terraceNoise = new FastNoiseLite((int) (seed + 2));
        terraceNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        terraceNoise.SetFrequency(0.05f);

        warpNoise = new FastNoiseLite((int) (seed + 3));
        warpNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        warpNoise.SetFrequency(0.005f);

        ridgeNoise = new FastNoiseLite((int) (seed + 4));
        ridgeNoise.SetNoiseType(FastNoiseLite.NoiseType.RidgedMulti);
        ridgeNoise.SetFrequency(0.02f);

        // ----- Biome thresholds -----
        biomeThresholds.put("AbyssOceanBiome", -0.6);
        biomeThresholds.put("RiceTropical", -0.3);
        biomeThresholds.put("TeaField", 0.0);
        biomeThresholds.put("MeadowPlains", 0.2);
        biomeThresholds.put("BoneCrest", 0.4);
        biomeThresholds.put("BlizzardHell", 0.6);
        biomeThresholds.put("ThunderHills", 0.8);
        biomeThresholds.put("Volcano", 1.0);

        // ----- Biome block palettes -----
        biomeBlocks.put("AbyssOceanBiome", new String[]{"WATER", "SAND"});
        biomeBlocks.put("RiceTropical", new String[]{"RICE_CROP", "WATER", "DIRT"});
        biomeBlocks.put("TeaField", new String[]{"GRASS_BLOCK", "DIRT", "TEA_BUSH"});
        biomeBlocks.put("MeadowPlains", new String[]{"GRASS_BLOCK", "DIRT"});
        biomeBlocks.put("BoneCrest", new String[]{"TERRACOTTA", "RED_SAND"});
        biomeBlocks.put("BlizzardHell", new String[]{"SNOW_BLOCK", "ICE"});
        biomeBlocks.put("ThunderHills", new String[]{"GRASS_BLOCK", "STONE"});
        biomeBlocks.put("Volcano", new String[]{"BASALT", "MAGMA_BLOCK"});

        // ----- Biome heights (min/avg/max) -----
        biomeHeights.put("AbyssOceanBiome", new BiomeHeight(oceanFloor, (oceanFloor + seaLevel) / 2, seaLevel));
        biomeHeights.put("RiceTropical", new BiomeHeight(76, 150, 355));
        biomeHeights.put("TeaField", new BiomeHeight(76, 130, 250));
        biomeHeights.put("MeadowPlains", new BiomeHeight(75, 89, 120));
        biomeHeights.put("BoneCrest", new BiomeHeight(78, 579, 979));
        biomeHeights.put("BlizzardHell", new BiomeHeight(457, 638, 2803));
        biomeHeights.put("ThunderHills", new BiomeHeight(1500, 2560, 5300));
        biomeHeights.put("Volcano", new BiomeHeight(250, 750, 3000));
    }

    // ----- Get biome -----
    public String getBiome(double x, double z) {
        double n = biomeNoise.GetNoise((float)x, (float)z) + warpNoise.GetNoise((float)x, (float)z) * 0.5;
        for (Map.Entry<String, Double> entry : biomeThresholds.entrySet()) {
            if (n < entry.getValue()) return entry.getKey();
        }
        return "Volcano";
    }

    // ----- Get height -----
    public double getHeight(double x, double z, String biome) {
        BiomeHeight h = biomeHeights.getOrDefault(biome, new BiomeHeight(seaLevel, seaLevel+5, seaLevel+10));

        double noiseValue = heightNoise.GetNoise((float)x, (float)z); // -1 → 1
        double ridgeEffect = ridgeNoise.GetNoise((float)x, (float)z);
        double terraceEffect = terraceNoise.GetNoise((float)x, (float)z);

        double range = h.max - h.min;
        double rawHeight = h.avg + noiseValue * (range / 2) + ridgeEffect + terraceEffect;

        // Clamp với min/max
        if (rawHeight < h.min) rawHeight = h.min;
        if (rawHeight > h.max) rawHeight = h.max;

        return rawHeight;
    }

    // ----- Get block palette -----
    public String[] getBlocks(String biome) {
        return biomeBlocks.getOrDefault(biome, new String[]{"STONE"});
    }

    // ----- Optional: update thresholds or blocks dynamically -----
    public void setBiomeThreshold(String biome, double threshold) {
        biomeThresholds.put(biome, threshold);
    }

    public void setBlockPalette(String biome, String[] blocks) {
        biomeBlocks.put(biome, blocks);
    }

    public void setBiomeHeight(String biome, double min, double avg, double max) {
        biomeHeights.put(biome, new BiomeHeight(min, avg, max));
    }
}