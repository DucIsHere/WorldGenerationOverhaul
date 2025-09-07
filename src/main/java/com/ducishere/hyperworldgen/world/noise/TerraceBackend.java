package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.module.modifier.Terrace;

public class TerraceBackend {

    private static final Terrace noise = new Terrace();

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
