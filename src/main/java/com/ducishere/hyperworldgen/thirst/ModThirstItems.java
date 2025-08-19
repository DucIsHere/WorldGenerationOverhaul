package toughasnails.thirst;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModThirstItems {

    public static Item WATER_BOTTLE;
    public static Item CANTEEN;

    public static void registerItems() {
        WATER_BOTTLE = Registry.register(Registry.ITEM,
                new Identifier("toughasnails", "water_bottle"),
                new ThirstItems(4).setRegistryName("water_bottle"));

        CANTEEN = Registry.register(Registry.ITEM,
                new Identifier("toughasnails", "canteen"),
                new ThirstItems(20).setRegistryName("canteen"));
    }
}
