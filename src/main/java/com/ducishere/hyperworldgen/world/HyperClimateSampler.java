package com.ducishere.hyperworldgen.world;

import com.ducishere.hyperworldgen.world.noise.NoiseBackendFunction;
import net.minecraft.world.biome.source.Climate;
import net.minecraft.world.gen.densityfunction.DensityFunction;

public class HyperClimateSampler {

    public static Climate.Sampler createSampler() {
        double biomeScale = 0.00005;

        return Climate.Sampler.of(
                new NoiseBackendFunction("climate", biomeScale),          // temperature
                new NoiseBackendFunction("biomeblend", biomeScale),       // humidity
                new NoiseBackendFunction("continentalness", biomeScale),  // continentalness
                new NoiseBackendFunction("erosion", biomeScale),          // erosion
                DensityFunction.constant(0.0),                             // depth
                new NoiseBackendFunction("hybrid", biomeScale),            // weirdness
                DensityFunction.constant(0.0)                              // offset
        );
    }
}
