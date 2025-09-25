package com.ducishere.hyperworldgen.world;

import com.ducishere.hyperworldgen.world.noise.NoiseBackendFunction;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.noise.NoiseRouter;

public class HyperNoiseRouter {

    public static NoiseRouter createRouter() {
        double terrainScale = 0.0003;
        double biomeScale = 0.00005;

        DensityFunction mountain = DensityFunctions.ridgedNoise(bigNoiseParams).mul(180);
        DensityFunction small = DensityFunctions.ridgedNoise(smallNoiseParams).mul(80);
        DensityFunction valley = DensityFunctions.constant(1.0)
            .sub(DensityFunctions.abs(DensityFunctions.noise(valleyNoiseParams)))
            .mul(60);

        DensityFunction height = DensityFunctions.add(mountain, small)
            .add(valley.negate())
            .clamp(-10000, 20000);


        DensityFunction ridged = new NoiseBackendFunction("ridged", terrainScale);
        DensityFunction tectonic = new NoiseBackendFunction("tectonic", terrainScale);
        DensityFunction mountain = new NoiseBackendFunction("fractalridged", terrainScale);
        DensityFunction dune = new NoiseBackendFunction("dune", terrainScale);

        DensityFunction base = DensityFunction.add(ridged, tectonic);
        DensityFunction terrain = DensityFunction.add(base, mountain);
        DensityFunction finalTerrain = DensityFunction.add(terrain, dune);

        return new NoiseRouter(
                DensityFunction.constant(0.0),      // barrier
                DensityFunction.constant(0.0),      // fluid level floodedness
                DensityFunction.constant(0.0),      // fluid level spread
                DensityFunction.constant(0.0),      // lava
                finalTerrain,                        // final density
                finalTerrain,
                finalTerrain,
                tectonic,                             // continentalness
                new NoiseBackendFunction("erosion", terrainScale),
                mountain,                              // depth
                new NoiseBackendFunction("hybrid", terrainScale), // weirdness
                DensityFunction.constant(0.0)
        );
    }
}
