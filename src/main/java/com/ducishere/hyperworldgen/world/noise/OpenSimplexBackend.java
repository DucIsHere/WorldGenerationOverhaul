package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.Noise;
import com.flowpowered.noise.module.source.Perlin;

public class OpenSimplexBackend {

    private static final Noise noise = new Perlin(); // có thể đổi OpenSimplex nếu lib khác

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
