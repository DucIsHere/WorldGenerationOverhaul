package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.module.source.Cellular;

public class CaveBackend {

    private static final Cellular noise = new Cellular();

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
