package hypergenworld.worldgen.biome.snow;

import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.block.Blocks;

public class BlizzardHellGeneration {

    public static GenerationSettings create() {
        return new GenerationSettings.Builder()
            .surfaceBuilder(SurfaceBuilder.DEFAULT.withConfig(
                new TernarySurfaceConfig(
                    Blocks.SNOW_BLOCK.getDefaultState(),
                    Blocks.PACKED_ICE.getDefaultState(),
                    Blocks.BLUE_ICE.getDefaultState()
                )
            ))
            // Không có lake, cây, grass gì cả
            .build();
    }
}
