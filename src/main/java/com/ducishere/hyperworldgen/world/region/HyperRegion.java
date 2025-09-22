package com.ducishere.hyperworldgen.world.region;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.Climate;
import terrablender.api.Region;

import java.util.function.Consumer;

public class HyperRegion extends Region {

    public HyperRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(RegistryKey<Biome> registry, Consumer<Pair<Climate.ParameterPoint, RegistryKey<Biome>>> mapper) {
        Climate.ParameterPoint point = Climate.parameters(
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.span(-1.0F, 1.0F),
                Climate.Parameter.point(0.0F),
                Climate.Parameter.span(-1.0F, 1.0F)
        );
        mapper.accept(Pair.of(point, registry));
    }
}
