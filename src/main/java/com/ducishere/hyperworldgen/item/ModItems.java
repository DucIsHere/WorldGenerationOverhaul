package com.ducishere.hyperworldgen.item;

import com.ducishere.hyperworldgen.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item CRYO_LANTERN_ITEM;

    public static void registerItems() {
        CRYO_LANTERN_ITEM = Registry.register(Registries.ITEM, new Identifier("hyperworldgen", "music_disc_free_luck"),
                new BlockItem(ModBlocks.CRYO_LANTERN, new Item.Settings().group(ItemGroup.DECORATIONS)));

        FREE_LUCK_DISC_ITEM = Registry.register(Registries.ITEM, new Identifier("hyperworldgen", "music_disc_free_luck"),
                new MusicDiscItem(10, ModSounds.FREE_LUCK_DISC, new Item.Settings().group(ItemGroup.MISC)));
        System.out.println("Items registered");
    }
}
