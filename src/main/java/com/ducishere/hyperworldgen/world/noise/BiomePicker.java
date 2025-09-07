package com.ducishere.hyperworldgen.world.gen;

import com.ducishere.hyperworldgen.world.biome.NoiseBackendManager;
import net.minecraft.world.World;

public class BiomePicker {

    public static void assignBiome(World world,int x,int y,int z){
        double temp = NoiseBackendManager.sample("opensimplex", x,y,z);
        double humidity = NoiseBackendManager.sample("ridged", x,y,z);
        double cont = NoiseBackendManager.sample("terrablend", x,y,z);
        double erosion = NoiseBackendManager.sample("opensimplex", x,y,z);
        double weirdness = NoiseBackendManager.sample("domainwarp", x,y,z);

        // Example: pseudo pick
        if(temp>0.6 && humidity>0.7) world.setBiome(x,y,z,"farmers_delight");
        else if(temp<0.0 && humidity>0.5) world.setBiome(x,y,z,"blizzard_hell");
        else if(erosion<-0.8) world.setBiome(x,y,z,"everest_summit");
        else world.setBiome(x,y,z,"plains");
    }
}
