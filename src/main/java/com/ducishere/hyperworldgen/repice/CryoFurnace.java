package com.ducishere.hyperworldgen.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import hyperworldgen.item.ModItems;

public class ModRecipes {
    public static void registerRecipes() {
        // Furnace Smelting: Steel Abyss Ore -> Steel Abyss Scrap
        SmeltingRecipe steelAbyssSmelting = new SmeltingRecipe(
            new Identifier("hyperworldgen", "steel_abyss_smelting"),
            "", // group
            new ItemStack(ModItems.STEEL_ABYSS_SCRAP),
            ModItems.STEEL_ABYSS_ORE.getDefaultStack(),
            90000.0f, // experience
            160000  // cook time in ticks
        );

        Registry.register(Registry.RECIPE_SERIALIZER, 
                          new Identifier("hyperworldgen", "steel_abyss_smelting"), 
                          RecipeSerializer.SMELTING);
    }
}
