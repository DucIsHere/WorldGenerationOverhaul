package com.ducishere.hyperworldgen.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.ducishere.hyperworldgen.HyperWorldGen;

public class GlacialIceShard extends Item {

    public static final GlacialIceShard INSTANCE = new GlacialIceShard();

    private GlacialIceShard() {
        super(new Item.Settings()
                .group(ItemGroup.MISC)
                .maxCount(64)
        );
    }

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(HyperWorldGen.MODID, "glacial_ice_shard"), INSTANCE);
    }
}
