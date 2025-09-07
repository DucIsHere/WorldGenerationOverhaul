package com.ducishere.hyperworldgen.world;

import com.ducishere.hyperworldgen.HyperWorldGen;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.NoiseSettings;
import net.minecraft.world.gen.NoiseSamplingSettings;

public class ModNoiseSettings {
    public static final RegistryKey<NoiseSettings> CUSTOM_OVERWORLD = RegistryKey.of(
            RegistryKeys.NOISE_SETTINGS,
            new Identifier(HyperWorldGen.MOD_ID, "overworld")
    );

    public static void bootstrap(Registerable<NoiseSettings> context) {
        NoiseSettings custom = new NoiseSettings(
                -25000,   // min_y
                50000,   // height
                1,     // size_horizontal
                2,     // size_vertical
                new NoiseSamplingSettings(
                        0.0000333, // xz_scale -> ~30k block biome
                        0.0000333, // y_scale
                        80.0,      // xz_factor
                        160.0      // y_factor
                ),
                1, 2, // top slide target + size
                1, 2, // bottom slide target + size
                true,  // randomDensityOffset
                true,  // islandNoiseOverride
                false, // amplify
                false  // largeBiomes
        );

        context.register(CUSTOM_OVERWORLD, custom);
    }
}
