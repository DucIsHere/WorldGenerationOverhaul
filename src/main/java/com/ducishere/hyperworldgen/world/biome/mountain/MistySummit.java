package hypergenworld.worldgen.biome.mountain;

import net.minecraft.world.biome.*;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.registry.entry.RegistryEntry;

public class MistySummit {

    public static Biome create() {
        // Visual effects
        BiomeEffects effects = new BiomeEffects.Builder()
            .fogColor(0xA0A0A0)
            .waterColor(0x3F76E4)
            .waterFogColor(0x505050)
            .skyColor(0x80A0FF)
            .moodSound(BiomeMoodSound.CAVE)
            .build();

        BiomeSpawnSettings.Builder spawnBuilder = new BiomeSpawnSettings.Builder();
        // Ít mob, chủ yếu là mob sống sót trong điều kiện khắc nghiệt
        // Tùy bạn thêm custom mob

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

        return new Biome.Builder()
            .precipitation(Biome.Precipitation.NONE) // núi mưa thường
            .temperature(0.1f) // lạnh vừa
            .downfall(0.6f)   // rainfall vừa phải
            .effects(effects)
            .spawnSettings(IronridgeSpawns.create())      // mobs
            .generationSettings(IronridgeGeneration.create()) // ores + terrain features
            .build();
    }
}
