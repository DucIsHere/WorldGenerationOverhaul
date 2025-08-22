package com.ducishere.hyperworldgen.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static void registerBlocks() {
        

        Registry.register(
            Registries.ITEM,
            new Identifier("hyperworldgen", "soul_lantern"),
            new BlockItem(SOUL_LANTERN, new Item.Settings().group(ItemGroup.DECORATIONS))

    public static final Block CRYO_FURNACE = registerBlock("cryo_furnace",
        new CryoFurnaceBlock(FabricBlockSettings.of(Material.METAL).strength(5.0f, 6.0f).requiresTool()));

        );
    }
}
