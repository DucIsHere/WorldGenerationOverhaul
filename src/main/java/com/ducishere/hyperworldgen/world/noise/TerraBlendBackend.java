package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.module.source.Perlin;

public class TerraBlendBackend {

    private static final Perlin noise = new Perlin();

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
