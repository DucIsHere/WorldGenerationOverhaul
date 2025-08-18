package com.ducishere.hyperworldgen.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemGroup;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelIngot extends Item {

    public static final SteelIngot INSTANCE = new SteelIngot();

    private SteelIngot() {
        super(new FabricItemSettings()
                .group(ItemGroup.MISC) // Hoặc custom creative tab
                .maxCount(32)
        );
    }

    public static void register() {
        net.minecraft.core.Registry.register(
                net.minecraft.core.Registry.ITEM,
                HyperWorldGen.id("steel_ingot"),
                INSTANCE
        );
    }
}
