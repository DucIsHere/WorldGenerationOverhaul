package com.ducishere.hyperworldgen.world.noise;

public class HybridBackend {

    public static double sampleAll(double x, double y, double z){
        double base = OpenSimplexBackend.sample(x,y,z);
        double mountain = RidgedBackend.sample(x,y,z);
        double cave = CellularBackend.sample(x,y,z);
        double warp = DomainWarpBackend.sample(x,y,z);
        double terrace = TerraceBackend.sample(x,y,z);
        double river = RiverBackend.sample(x,y,z);
        double fast = FastNoiseBackend.sample(x,y,z);
        // Blend tất cả, weight tùy chỉnh
        return base*0.3 + mountain*0.25 + cave*0.1 + warp*0.1 + terrace*0.1 + river*0.05 + fast*0.1;
    }
}
