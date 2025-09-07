package com.ducishere.hyperworldgen.world.noise;

import com.flowpowered.noise.module.modifier.DomainWarp;

public class DomainWarpBackend {

    private static final DomainWarp noise = new DomainWarp();

    public static double sample(double x, double y, double z){
        return noise.getValue(x, y, z);
    }
}
