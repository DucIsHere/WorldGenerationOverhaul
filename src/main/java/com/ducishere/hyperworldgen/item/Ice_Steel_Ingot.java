package com.ducishere.hyperworldgen.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class IceSteelIngot extends Item {

    public static final IceSteelIngot INSTANCE = new IceSteelIngot();

    private IceSteelIngot() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "ice_steel_ingot"), INSTANCE);
    }
}
