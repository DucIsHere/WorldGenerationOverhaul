package com.ducishere.hyperworldgen.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelReinforcedIngot extends Item {

    public static final SteelReinforcedIngot INSTANCE = new SteelReinforcedIngot();

    private SteelReinforcedIngot() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "steel_reinforced_ingot"), INSTANCE);
    }
}
