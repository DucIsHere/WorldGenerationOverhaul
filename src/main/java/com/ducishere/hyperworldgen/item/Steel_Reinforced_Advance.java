package com.ducishere.hyperworldgen.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelReinforcedNetherite extends Item {

    public static final SteelReinforcedNetherite INSTANCE = new SteelReinforcedNetherite();

    private SteelReinforcedNetherite() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "steel_reinforced_netherite"), INSTANCE);
    }
}
