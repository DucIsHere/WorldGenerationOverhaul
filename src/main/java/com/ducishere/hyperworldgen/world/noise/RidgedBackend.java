package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.module.source.RidgedMulti;

public class RidgedBackend {

    private static final RidgedMulti noise = new RidgedMulti();

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
