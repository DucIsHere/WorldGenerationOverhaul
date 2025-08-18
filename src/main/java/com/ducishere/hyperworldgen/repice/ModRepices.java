package com.ducishere.hyperworldgen.recipe;

import com.ducishere.hyperworldgen.item.ModItems;
import net.fabricmc.fabric.api.recipe.v1.FabricRecipeBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void registerRecipes() {

        // 1️⃣ Furnace Smelting: Steel Abyss Ore -> Steel Abyss Scrap
        FabricRecipeBuilder.createSmelting(
                RecipeCategory.MISC,
                ModItems.STEEL_ABYSS_ORE,
                ModItems.STEEL_ABYSS_SCRAP,
                1.0f,
                160000
        ).build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "steel_abyss_smelting"));

        // 2️⃣ Crafting Alloy: 8 Scrap + 1 Steel Basic -> Steel Abyss Ingot
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
        .input('B', ModItems.STEEL_INGOT)
        .build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "steel_abyss_ingot"));

        // 3️⃣ Crafting Steel Reinforced: 1 Abyss Ingot + 8 Steel Basic -> Steel Reinforced
        FabricRecipeBuilder.createShaped(
                RecipeCategory.MISC,
                ModItems.STEEL_REINFORCED_INGOT,
                """
                BBB
                BIB
                BBB
                """
        )
        .input('I', ModItems.STEEL_ABYSS_INGOT)
        .input('B', ModItems.STEEL_INGOT)
        .build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "steel_reinforced"));

        // 4️⃣ Crafting Ice Steel Reinforced: 1 Steel Reinforced + 8 Ice Crystal -> Ice Steel Reinforced
        FabricRecipeBuilder.createShaped(
                RecipeCategory.MISC,
                ModItems.ICE_STEEL_REINFORCED_INGOT,
                """
                EIE
                IRI
                EIE
                """
        )
        .input('I', ModItems.ICE_SHARD)
        .input('R', ModItems.STEEL_REINFORCED_INGOT)
        .input('E', ModItems.GOLD_INGOT)
        .build(Registry.RECIPE_SERIALIZER, new Identifier("hyperworldgen", "ice_steel_reinforced"));
    }
}
