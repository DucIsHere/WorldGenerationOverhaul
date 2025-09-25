package com.ducishere.hyperworldgen.biome;

import net.minecraft.world.level.biome.Climate;

public class SkyValleyParameters {
    public static final Climate.ParameterPoint SKY_VALLEY = new Climate.ParameterPoint(
            Climate.Parameter.span(-0.25F, 0.25F),   // Temp trung bình
            Climate.Parameter.span(0.3F, 0.9F),      // Hơi ẩm
            Climate.Parameter.span(0.4F, 1.0F),      // Sâu trong đất liền
            Climate.Parameter.span(-1.0F, -0.5F),    // Erosion thấp (núi nhọn)
            Climate.Parameter.span(0.0F, 0.5F),      // Weirdness nhẹ
            Climate.Parameter.point(0.0F),           // Offset
            0                                        // Weight
    );
}
