package hypergenworld.worldgen.biome.snow;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.registry.entry.RegistryEntry;

public class BlizzardHellBiome {

    public static Biome create() {
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xB9CFE0)
            .waterColor(0x3A5068)
            .waterFogColor(0x2C3E50)
            .skyColor(0x94BFFF)
            .moodSound(BiomeMoodSound.CAVE)
            .build();

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.SNOW)
            .temperature(-5.8f)
            .downfall(4.0f)
            .effects(effects)
            .spawnSettings(BlizzardHellSpawns.create())
            .generationSettings(BlizzardHellGeneration.create())
            .build();
    }
}