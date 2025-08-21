package toughasnails.init;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;
import toughasnails.core.ToughAsNails;

public class ModCompatibility {

    public static void init() {
        // Không check Serene Seasons nữa
        ToughAsNails.LOGGER.info("Initializing TAN temperature modifiers (no Serene Seasons).");
        TemperatureHelper.registerPositionalTemperatureModifier(ModCompatibility::temperatureModifier);
    }

    private static TemperatureLevel temperatureModifier(Level level, BlockPos pos, TemperatureLevel current) {
        Biome biome = level.getBiome(pos).value();

        // Altitude check
        int highestY = level.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ());
        if (pos.getY() <= ModConfig.temperature.environmentalModifierAltitude && pos.getY() < highestY) {
            return current;
        }

        // Biome blacklist check
        if (ModTags.Biomes.BLACKLISTED_BIOMES.contains(biome)) {
            return current;
        }

        // Có thể thêm logic custom khác, ví dụ: tropical biome tăng 1 level
        if (ModTags.Biomes.TROPICAL_BIOMES.contains(biome)) {
            current = current.increment(1);
        }

        return current;
    }
}
