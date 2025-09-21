package com.ducishere.hyperworldgen.world.pipeline;

import com.ducishere.hyperworldgen.world.noise.base.*;

public class AshenMirage {
    public static double sample(double x, double y, double z, long seed) {
        // Base desert height - smooth dunes
        double base = OpenSimplexBackend.sample(x * 0.002, y, z * 0.002) * 0.6;
        
        // Terrace dunes - tạo sóng cát
        double dunes = TerraceBackend.sample(x * 0.0015, y, z * 0.0015) * 0.3;
        
        // Domain warp để không bị quá đều
        double warp = DomainWarpBackend.sample(x * 0.0008, y, z * 0.0008) * 0.1;
        
        // Ridged nhỏ để thêm chút gợn cát
        double ridged = RidgedBackend.sample(x * 0.004, y, z * 0.004) * 0.05;

        // Tổng hợp chiều cao desert
        double height = (base + dunes + warp + ridged) * 0.8;

        // Clamp cho chắc - desert không được quá cao
        return Math.max(-0.3, Math.min(0.7, height));
    }
}
