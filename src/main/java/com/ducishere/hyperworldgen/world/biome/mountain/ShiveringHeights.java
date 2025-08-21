package hypergenworld.worldgen.biome.mountain;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.registry.entry.RegistryEntry;

public class IronridgePeaksBiome {

    public static Biome create() {
        // Visual effects
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xA0A0A0)
            .waterColor(0x3F76E4)
            .waterFogColor(0x505050)
            .skyColor(0x80A0FF)
            .moodSound(BiomeMoodSound.CAVE)
            .build();

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.RAIN) // núi mưa thường
            .temperature(-0.5f) // lạnh vừa
            .downfall(0.6f)   // rainfall vừa phải
            .effects(effects)
            .spawnSettings(IronridgeSpawns.create())      // mobs
            .generationSettings(IronridgeGeneration.create()) // ores + terrain features
            .build();
    }
}
