package com.ducishere.hyperworldgen.world.pipeline;

import com.ducishere.hyperworldgen.world.noise.backends.*;

public class AshenGorgePipeline {
    public static double sample(double x, double y, double z, long seed) {
        double dune = new DuneBackend(seed, 0.03, 0.8).sample(x, y, z);
        double ridged = new RidgedBackend(seed, 0.05, 0.4).sample(x, y, z);
        double quantized = new QuantizeBackend(3).apply(dune + ridged);
        double patch = new PatchBackend(seed, 0.15).sample(x, y, z);
        double sand = new SandPatchBackend(seed, 0.5).sample(x, y, z);
        return quantized + patch * 0.5 + sand * 0.3;
    }
}
