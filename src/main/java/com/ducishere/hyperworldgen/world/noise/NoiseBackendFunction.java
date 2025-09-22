package com.ducishere.hyperworldgen.world.noise;

import net.minecraft.world.gen.densityfunction.DensityFunction;

public class NoiseBackendFunction implements DensityFunction {
    private final String noiseName;
    private final double scale;

    public NoiseBackendFunction(String noiseName, double scale) {
        this.noiseName = noiseName;
        this.scale = scale;
    }

    @Override
    public double compute(FunctionContext ctx) {
        double x = ctx.blockX() * scale;
        double y = ctx.blockY() * scale;
        double z = ctx.blockZ() * scale;
        return NoiseBackendManagerV2.sample(noiseName, x, y, z);
    }

    @Override
    public double[] fill(double[] densities, ContextProvider contextProvider) {
        for (int i = 0; i < densities.length; i++) {
            FunctionContext ctx = contextProvider.forIndex(i);
            densities[i] = compute(ctx);
        }
        return densities;
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(this);
    }
}
