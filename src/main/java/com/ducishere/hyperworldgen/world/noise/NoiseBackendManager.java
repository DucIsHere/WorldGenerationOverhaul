package com.ducishere.hyperworldgen.world.noise;

public class NoiseBackendManager {

    public static double sample(String name, double x, double y, double z) {
        switch(name) {
            case "opensimplex": return OpenSimplexBackend.sample(x,y,z);
            case "ridged": return RidgedBackend.sample(x,y,z);
            case "cellular": return CellularBackend.sample(x,y,z);
            case "domainwarp": return DomainWarpBackend.sample(x,y,z);
            case "terrace": return TerraceBackend.sample(x,y,z);
            case "river": return RiverBackend.sample(x,y,z);
            case "cave": return CaveBackend.sample(x,y,z);
            case "fastnoise": return FastNoiseBackend.sample(x,y,z);
            case "terrablend": return TerraBlendBackend.sample(x,y,z);
            case "hybrid": return HybridBackend.sampleAll(x,y,z); // blend tất cả
            default: return 0;
        }
    }
}
