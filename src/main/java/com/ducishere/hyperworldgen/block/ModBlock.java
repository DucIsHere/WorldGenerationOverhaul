package com.ducishere.hyperworldgen.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static Block CRYO_LANTERN;

    public static void registerBlocks() {
        CRYO_LANTERN = Registry.register(Registries.BLOCK,
                new Identifier("hyperworldgen", "cryo_lantern"),
                new Block(Block.Settings.of(Material.METAL).strength(3.0f).luminance(15)));
        System.out.println("Blocks registered");
    }
}
