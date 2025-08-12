package hypergenworld.worldgen.biome.snow;

import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;

public class BlizzardHellSpawns {

    public static SpawnSettings create() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();

        // Only allow cold-themed hostile mobs
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 100, 2, 4));
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.POLAR_BEAR, 15, 1, 2));
        // Cấm toàn bộ passive mob

        return builder.build();
    }
}
