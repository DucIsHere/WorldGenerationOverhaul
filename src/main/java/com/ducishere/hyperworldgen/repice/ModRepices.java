package com.ducishere.hyperworldgen.recipe;

import net.fabricmc.fabric.api.recipe.v1.FabricRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import hyperworldgen.item.ModItems;

public class ModRecipes {

    public static void registerRecipes() {

        // 1️⃣ Furnace Smelting: Steel Abyss Ore -> Steel Abyss Scrap
        FabricRecipeBuilder.createSmelting(
                RecipeCategory.MISC,
                ModItems.STEEL_ABYSS_ORE,    // input
                ModItems.STEEL_ABYSS_SCRAP, // output
                1.0f,                       // exp
                160000                       // cook time in ticks (~2h13p)
        ).build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "steel_abyss_smelting"));

        // 2️⃣ Crafting Alloy: 8 Scrap + 1 Steel Basic -> 1 Steel Abyss Ingot
        FabricRecipeBuilder.createShaped(
                RecipeCategory.MISC,
                ModItems.STEEL_ABYSS_INGOT,
                """
                SSS
                SBS
                SSS
                """
        )
        .input('S', ModItems.STEEL_ABYSS_SCRAP)
        .input('B', ModItems.STEEL_BASIC)
        .build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "steel_abyss_ingot"));
    }
}
