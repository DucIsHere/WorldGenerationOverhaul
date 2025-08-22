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

    // Music / Block items
    public static Item FREE_LUCK_DISC_ITEM;

    // Steel progression items
    public static Item STEEL_ABYSS_ORE;
    public static Item STEEL_ABYSS_SCRAP;
    public static Item STEEL_BASIC;
    public static Item STEEL_ABYSS_INGOT;
    public static Item STEEL_REINFORCED;
    public static Item ICE_CRYSTAL;
    public static Item ICE_STEEL_REINFORCED;

    public static void registerItems() {

        

        // Music disc
        FREE_LUCK_DISC_ITEM = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "music_disc_free_luck"),
                new MusicDiscItem(10, ModSounds.FREE_LUCK_DISC, new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Abyss Ore
        STEEL_ABYSS_ORE = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_ore"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Abyss Scrap
        STEEL_ABYSS_SCRAP = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_scrap"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Basic
        STEEL_INGOT = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_basic"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Abyss Ingot
        STEEL_ABYSS_INGOT = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_abyss_ingot"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Steel Reinforced
        STEEL_REINFORCED_INGOT = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "steel_reinforced"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Ice Crystal
        ICE_SHARD = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "ice_crystal"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        // Ice Steel Reinforced
        ICE_STEEL_REINFORCED_INGOT = Registry.register(
                Registries.ITEM,
                new Identifier("hyperworldgen", "ice_steel_reinforced"),
                new Item(new Item.Settings().group(ItemGroup.MISC))
        );

        System.out.println("All ModItems registered");
    }
}
