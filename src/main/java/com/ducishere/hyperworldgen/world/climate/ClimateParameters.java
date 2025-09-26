package com.ducishere.hyperworldgen.world.climate;

import net.minecraft.world.biome.source.Climate;

public class ClimateParameters {
    // Nhiệt độ
    public static final Climate.Parameter FREEZING = Climate.Parameter.span(-1.0F, -0.5F);
    public static final Climate.Parameter COLD = Climate.Parameter.span(-0.5F, 0.0F);
    public static final Climate.Parameter TEMPERATE = Climate.Parameter.span(0.0F, 0.5F);
    public static final Climate.Parameter HOT = Climate.Parameter.span(0.5F, 1.0F);

    // Độ ẩm
    public static final Climate.Parameter ARID = Climate.Parameter.span(-1.0F, -0.5F);
    public static final Climate.Parameter DRY = Climate.Parameter.span(-0.5F, 0.0F);
    public static final Climate.Parameter HUMID = Climate.Parameter.span(0.0F, 0.5F);
    public static final Climate.Parameter WET = Climate.Parameter.span(0.5F, 1.0F);

    // Độ cao
    public static final Climate.Parameter DEEP = Climate.Parameter.span(-1.0F, -0.5F);
    public static final Climate.Parameter LOW = Climate.Parameter.span(-0.5F, 0.0F);
    public static final Climate.Parameter MID = Climate.Parameter.span(0.0F, 0.5F);
    public static final Climate.Parameter HIGH = Climate.Parameter.span(0.5F, 1.0F);

    // Erosion
    public static final Climate.Parameter ERODED = Climate.Parameter.span(-1.0F, -0.3F);
    public static final Climate.Parameter NORMAL = Climate.Parameter.span(-0.3F, 0.3F);
    public static final Climate.Parameter STEEP = Climate.Parameter.span(0.3F, 1.0F);

    // Temperature modifier
    public static final Climate.Parameter FROZEN = Climate.Parameter.point(-1.0F);
    public static final Climate.Parameter NONE = Climate.Parameter.point(0.0F);
    public static final Climate.Parameter HOT_MOD = Climate.Parameter.point(1.0F);
}
