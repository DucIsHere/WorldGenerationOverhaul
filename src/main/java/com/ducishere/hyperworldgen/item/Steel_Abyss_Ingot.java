package com.ducishere.hyperworldgen.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelAbyssIngot extends Item {

    public static final SteelAbyssIngot INSTANCE = new SteelAbyssIngot();

    private SteelAbyssIngot() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "steel_abyss_ingot"), INSTANCE);
    }
}
