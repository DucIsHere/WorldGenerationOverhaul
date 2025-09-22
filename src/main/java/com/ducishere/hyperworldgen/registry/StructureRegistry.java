package com.ducishere.hyperworldgen.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class StructureRegistry {

    public static final StructureType<Structure> HYPER_TOWER =
            Registry.register(Registries.STRUCTURE_TYPE,
                    new Identifier("hyperworldgen", "hyper_tower"),
                    ctx -> new Structure(ctx) {});

    public static void init() {
        // thêm structure khác nếu cần
    }
}
