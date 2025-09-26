package com.hypergenworld.world.region;

import com.hypergenworld.world.biome.ModBiomes;
import com.hypergenworld.world.parameters.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.Climate;
import terrablender.api.Region;

import java.util.function.Consumer;

public class HyperRegion extends Region {

    public HyperRegion() {
        super(new net.minecraft.util.Identifier("hypergenworld", "overworld"), RegionType.OVERWORLD, 5);
    }

    @Override
    public void addBiomes(RegistryKey<Biome> registry, Consumer<Pair<Climate.ParameterPoint, RegistryKey<Biome>>> mapper) {
        // Gắn từng parameter với từng biome
        mapper.accept(Pair.of(GlacialPinewoodParameters.PARAMETER, ModBiomes.GLACIAL_PINEWOOD));
        mapper.accept(Pair.of(BlizzardHellParameters.PARAMETER, ModBiomes.BLIZZARD_HELL));
        mapper.accept(Pair.of(PlainsBirchParameters.PARAMETER, ModBiomes.PLAINS_BIRCH));
        // ... add thêm ở đây
    }
}
