package com.ducishere.hyperworldgen.world.climate;

import net.minecraft.util.Identifier;
import terrablender.api.Regions;

public class ClimateManager {
    public static void registerRegions() {
        Regions.register(new HyperRegion(new Identifier("hyperworldgen", "overworld"), 5));
    }
}
