package com.ducishere.hyperworldgen.world.noise;

public class HybridBackend {

    @param x Y world
    @param y Y world
    @param z Z world
    @return

    public static double sampleAll(double x, double y, double z){
        double base = OpenSimplexBackend.sample(x,y,z);
        double mountain = RidgedBackend.sample(x,y,z);
        double cave = CellularBackend.sample(x,y,z);
        double warp = DomainWarpBackend.sample(x,y,z);
        double terrace = TerraceBackend.sample(x,y,z);
        double river = RiverBackend.sample(x,y,z);
        double fast = FastNoiseBackend.sample(x,y,z);
        double terra = TerraBlendBackend.sample(x,y,z);
        // Blend tất cả, weight tùy chỉnh

        double value = 0;
        value += base*0.2;
        value += mountain*0.25;
        value += warp*0.1;
        value += cave*0.1;
        value += terrace*0.1;
        value += river*0.05;
        value += fast*0.1;
        value += terra*0.1;

        return value;
        
    }
}
