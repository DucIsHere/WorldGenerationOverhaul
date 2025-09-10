package com.ducishere.hyperworldgen.world.pipeline;

import com.ducishere.hyperworldgen.world.noise.backends.*;

public class MacroPipeline {
    public static double sample(double x, double y, double z, long seed) {
        // Continentalness: -1 (ocean) → +1 (land)
        double cont = new ContinentalnessBackend(seed, 0.0015, 1.0).sample(x, y, z);
        double erosion = new ErosionBackend(seed, 0.002, 0.8).sample(x, y, z);
        double macro = new MacroTerrainBackend(seed, 0.004, 0.5).sample(x, y, z);

        double baseHeight = (cont + macro - erosion) * 20000; // map về -25000 ~ 50000
        return baseHeight;
    }
}
