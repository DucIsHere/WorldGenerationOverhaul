package com.ducishere.hyperworldgen.world.pipeline;

import com.ducishere.hyperworldgen.registry.HyperPipelineRegistry;

public class HyperPipelineNoise {
    public double getHeight(double x, double y, double z, long seed) {
        return HyperPipelineRegistry.get("desert")
                .apply(new HyperPipelineRegistry.PipelineContext(x, y, z, seed));
    }
}
