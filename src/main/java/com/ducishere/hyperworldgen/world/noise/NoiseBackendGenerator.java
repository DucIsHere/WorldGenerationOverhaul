package com.ducishere.hyperworldgen.world.noise;

public class NoiseBackendManager {

    public static double sample(String noiseName, double x, double y, double z) {
        switch(noiseName) {
            case "opensimplex": return OpenSimplexBackend.sample(x, y, z);
            case "ridged":     return RidgedBackend.sample(x, y, z);
            case "cellular":   return CellularBackend.sample(x, y, z);
            case "warp":       return DomainWarpBackend.sample(x, y, z);
            case "terrace":    return HybridBackend.sampleTerrace(x, y, z);
            case "river":      return HybridBackend.sampleRiver(x, y, z);
            case "cave":       return HybridBackend.sampleCave(x, y, z);
            case "ocean":      return TerraBlendBackend.sample(x, y, z);
            default: return 0.0;
        }
    }
}
