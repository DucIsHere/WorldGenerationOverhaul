package com.ducishere.hyperworldgen.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class SteelArmorUpgrade extends Item {

    public static final SteelArmorUpgrade INSTANCE = new SteelArmorUpgrade();

    private SteelArmorUpgrade() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(16)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "steel_armor_upgrade"), INSTANCE);
    }
}
