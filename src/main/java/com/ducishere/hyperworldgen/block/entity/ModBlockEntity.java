package com.ducishere.hyperworldgen.block.entity;

import com.ducishere.hyperworldgen.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<CryoFurnaceBlockEntity> CRYO_FURNACE_BE;

    public static void registerBlockEntities() {
        CRYO_FURNACE_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("hyperworldgen", "cryo_furnace"),
                FabricBlockEntityTypeBuilder.create(CryoFurnaceBlockEntity::new, ModBlocks.CRYO_FURNACE).build(null)
        );
    }
}
