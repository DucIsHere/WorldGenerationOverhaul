package toughasnails.init;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;
import java.util.function.IntFunction;

public class ModArmorMaterials
{
    private static EnumMap<ArmorType, Integer> allPieces(int value) {
        return Util.make(new EnumMap<>(ArmorType.class), map -> {
            map.put(ArmorType.BOOTS, value);
            map.put(ArmorType.LEGGINGS, value);
            map.put(ArmorType.CHESTPLATE, value);
            map.put(ArmorType.HELMET, value);
        });
    }

    public static final ArmorMaterial WOOL = new ArmorMaterial(
            5, // durability multiplier
            allPieces(1), // defense values
            1, // enchantment value
            SoundEvents.ARMOR_EQUIP_LEATHER, // equip sound
            0.0F, // toughness
            0.0F, // knockback resistance
            ItemTags.WOOL, // repair ingredient
            ModEquipmentAssets.WOOL // equipment asset
    );

    public static final ArmorMaterial LEAF = new ArmorMaterial(
            5,
            allPieces(1),
            1,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F,
            0.0F,
            ItemTags.LEAVES,
            ModEquipmentAssets.LEAF
    );
}
