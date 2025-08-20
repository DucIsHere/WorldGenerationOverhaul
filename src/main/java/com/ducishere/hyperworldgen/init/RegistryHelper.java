package toughasnails.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import toughasnails.core.ToughAsNails;

public class RegistryHelper {

    public static <T> T register(String name, T entry, net.minecraft.core.Registry<T> registry) {
        return net.minecraft.core.Registry.register(
                registry,
                ResourceLocation.fromNamespaceAndPath(ToughAsNails.MOD_ID, name),
                entry
        );
    }

    public static <T extends net.minecraft.world.level.block.entity.BlockEntity> BlockEntityType<T> registerBlockEntity(
            String name,
            BlockEntityType.BlockEntitySupplier<T> supplier,
            net.minecraft.world.level.block.Block... blocks
    ) {
        BlockEntityType<T> type = BlockEntityType.Builder.of(supplier, blocks).build(null);
        return register(name, type, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }
}
