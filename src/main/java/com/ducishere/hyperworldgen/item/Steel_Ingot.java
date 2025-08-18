package com.ducishere.hyperworldgen.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelIngot extends Item {

    public static final SteelIngot INSTANCE = new SteelIngot();

    private SteelIngot() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "steel_ingot"), INSTANCE);
    }
}
