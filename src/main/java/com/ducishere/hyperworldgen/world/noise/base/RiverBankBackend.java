package com.ducishere.hyperworldgen.world.noise.base;

// AUTO-GENERATED template for RiverBankBackend noise backend.
// Replace the sample(...) implementation with a real noise algorithm (OpenSimplex, FastNoiseLite, Simplex, Worley, etc.).
// These templates intentionally use simple deterministic math to allow immediate compile/testing; swap for real libraries later.

public class RiverBankBackend {
    /**
     * Sample the noise at (x, y, z).
     * NOTE: Replace this with a proper noise function. The current function is a placeholder.
     */
    public static double sample(double x, double y, double z) {
        // lightweight deterministic placeholder that is cheap to compute and stable for previews.
        double v = Math.sin(x * 0.0007 + 0.1234) + Math.cos(z * 0.0009 - 0.4321) * 0.5;
        v += Math.sin((x + z) * 0.0003 + y * 0.0001) * 0.25;
        // small class-specific tweak to help visually differentiate layers in previews
        v += 6 * 0.01;
        return v;
    }
}
