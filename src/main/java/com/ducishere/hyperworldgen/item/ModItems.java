package com.ducishere.hyperworldgen.item;

import com.ducishere.hyperworldgen.block.ModBlocks;
import com.ducishere.hyperworldgen.sound.ModSounds;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Music disc example
    public static Item CRYO_LANTERN_ITEM;
    public static Item FREE_LUCK_DISC_ITEM;

    // Steel Abyss items
    public static Item STEEL_ABYSS_ORE;
    public static Item STEEL_ABYSS_SCRAP;
    public static Item STEEL_BASIC;
    public static Item STEEL_ABYSS_INGOT;

    public static void registerItems() {
        // Example block item
        CRYO_LANTERN_ITEM = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "cryo_lantern"),
                new BlockItem(ModBlocks.CRYO_LANTERN, new Item.Settings().group(ItemGroup.DECORATIONS))
        );

        // Music disc example
        FREE_LUCK_DISC_ITEM = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "music_disc_free_luck"),
                new MusicDiscItem(10, ModSounds.FREE_LUCK_DISC, new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Abyss items
        STEEL_ABYSS_ORE = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_ore"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        STEEL_ABYSS_SCRAP = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_scrap"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        STEEL_BASIC = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_basic"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        STEEL_ABYSS_INGOT = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_ingot"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        System.out.println("Items registered");
    }
}
