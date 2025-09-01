package com.ducishere.hyperworldgen.world.noise;

import com.github.yellowstonegames.fastnoise.FastNoiseLite;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AllBiomesNoise {
    private FastNoiseLite biomeNoise, heightNoise, terraceNoise, warpNoise, ridgeNoise;
    private Random random;
    private Map<String,String[]> biomeBlocks = new HashMap<>();
    private Map<Long,Double> heightCache = new HashMap<>(); // chunk-based cache

    public AllBiomesNoise(long seed){
        biomeNoise = new FastNoiseLite((int)seed);
        heightNoise = new FastNoiseLite((int)(seed+1));
        terraceNoise = new FastNoiseLite((int)(seed+2));
        warpNoise = new FastNoiseLite((int)(seed+3));
        ridgeNoise = new FastNoiseLite((int)(seed+4));
        random = new Random(seed);

        // Configure noise types
        biomeNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        heightNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        warpNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        ridgeNoise.SetNoiseType(FastNoiseLite.NoiseType.RidgedMulti);

        // Blocks palette
        biomeBlocks.put("Plains", new String[]{"GRASS_BLOCK","DIRT"});
        biomeBlocks.put("Mesa", new String[]{"TERRACOTTA","RED_SAND"});
        biomeBlocks.put("Volcano", new String[]{"BASALT","MAGMA_BLOCK"});
        biomeBlocks.put("SnowMountain", new String[]{"SNOW_BLOCK","ICE"});
        biomeBlocks.put("ThunderHills", new String[]{"GRASS_BLOCK","STONE"});
        biomeBlocks.put("RiceTropical", new String[]{"RICE_CROP","WATER","DIRT"});
        biomeBlocks.put("TeaField", new String[]{"GRASS_BLOCK","DIRT","TEA_BUSH"});
    }

    // --------------------- Biome selection with domain warp ---------------------
    public String getBiome(double x,double z){
        float warpX = x + warpNoise.GetNoise((float)(x/1000),(float)(z/1000)) * 1000;
        float warpZ = z + warpNoise.GetNoise((float)(x/1000+100),(float)(z/1000+100)) * 1000;
        float n = biomeNoise.GetNoise(warpX/50000,0,warpZ/50000);

        if(n<-0.6) return "Ocean";
        if(n<-0.3) return "RiceTropical";
        if(n<0) return "TeaField";
        if(n<0.2) return "Plains";
        if(n<0.4) return "Mesa";
        if(n<0.6) return "SnowMountain";
        if(n<0.8) return "ThunderHills";
        return "Volcano";
    }

    // --------------------- Height map with ridge/fBM hybrid + terrace ---------------------
    public double getHeight(double x,double z){
        long chunkKey = ((long)(x/16)<<32)|((long)(z/16)&0xFFFFFFFFL);
        if(heightCache.containsKey(chunkKey)) return heightCache.get(chunkKey);

        String biome = getBiome(x,z);
        double base = 75;
        double n = heightNoise.GetNoise((float)(x/200),0,(float)(z/200));
        double ridge = ridgeNoise.GetNoise((float)(x/100),(float)(z/100));

        double height = base;
        switch(biome){
            case "RiceTropical":
                double step = terraceNoise.GetNoise((float)(x/50),(float)(z/50),0);
                height = base + Math.floor(step*5)*10 + n*20;
                break;
            case "TeaField":
                height = base + n*150;
                break;
            case "Plains":
                height = base + n*50;
                break;
            case "Mesa":
                height = 500 + ridge*500 + n*200;
                break;
            case "Volcano":
                height = 3500 + ridge*15000 + n*1500;
                break;
            case "SnowMountain":
                height = 5000 + ridge*12500 + n*5000;
                break;
            case "ThunderHills":
                height = 150 + ridge*100 + n*50;
                break;
            default: height = base;
        }

        heightCache.put(chunkKey,height);
        return height;
    }

    // --------------------- River / Canal system ---------------------
    public void generateRiver(double x,double z,String biome){
        // slope ≤2
        // RiceTropical: canals connecting paddies
        // TeaField: irrigation channels
    }

    // --------------------- Vegetation ---------------------
    public void generateVegetation(double x,double y,double z,String biome){
        switch(biome){
            case "RiceTropical":
                // spawn rice crops + sparse palms
                break;
            case "TeaField":
                // spawn tea bushes + small trees
                break;
            case "Plains":
                // grass + trees
                break;
            case "Mesa":
                // cacti + sparse shrubs
                break;
            case "SnowMountain":
                // snow + pine trees
                break;
        }
    }

    // --------------------- Volcano ---------------------
    public void generateVolcano(double x,double z,String biome){
        if(!biome.equals("Volcano")) return;
        // magma rift 1–2 blocks, basalt ring
    }

    // --------------------- Get block palette ---------------------
    public String[] getBlocks(String biome){
        return biomeBlocks.getOrDefault(biome,new String[]{"STONE"});
    }
}
