package com.ducishere.hyperworldgen.world.density;

import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;

// Static helper class: wrap vanilla fn cho dễ gọi
public class DensityFunctionsHelper {

    // Plains: relatively flat
    public static DensityFunction plains() {
        return DensityFunctions.constant(0.1F);
    }

    // Hills: simple noise based
    public static DensityFunction hills(long seed) {
        return DensityFunctions.mul(
            DensityFunctions.noise(seed, 0.05D, 0.1D), // frequency, amplitude
            DensityFunctions.constant(1.5F)
        );
    }

    // Mountains: steep ridged noise
    public static DensityFunction mountains(long seed) {
        return DensityFunctions.mul(
            DensityFunctions.ridgedNoise(seed, 0.7D, 2.0D),
            DensityFunctions.constant(4.0F)
        );
    }

    // Valleys: inverted noise
    public static DensityFunction valleys(long seed) {
        return DensityFunctions.add(
            DensityFunctions.constant(-1.5F),
            DensityFunctions.noise(seed, 0.08D, 0.2D)
        );
    }

    // Floating islands (Sky Valley kiểu biome)
    public static DensityFunction floatingIslands(long seed) {
        return DensityFunctions.add(
            DensityFunctions.ridgedNoise(seed, 0.5D, 1.2D),
            DensityFunctions.constant(2.0F)
        );
    }
}
