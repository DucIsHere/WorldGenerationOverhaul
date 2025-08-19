package toughasnails.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipePropertySet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.crafting.WaterPurifierRecipe;
import toughasnails.init.ModRecipePropertySets;

import java.util.Map;
import java.util.Optional;

@Mixin(RecipeManager.class)
public class MixinRecipeManager {

    // Shadow field để truy cập map static private trong RecipeManager
    @Shadow
    private static Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> RECIPE_PROPERTY_SETS;

    /**
     * Inject vào static initializer của RecipeManager.
     * Mục đích: thêm custom recipe property set cho WaterPurifierRecipe
     */
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onInit(CallbackInfo ci) {
        // Tạo một map mới từ map cũ, thêm entry cho WATER_PURIFYING
        RECIPE_PROPERTY_SETS = ImmutableMap.<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor>builder()
                .putAll(RECIPE_PROPERTY_SETS) // giữ nguyên tất cả property set gốc
                .put(ModRecipePropertySets.WATER_PURIFYING, recipe -> {
                    // Nếu recipe là WaterPurifierRecipe, trả về input ingredient
                    if (recipe instanceof WaterPurifierRecipe waterRecipe) {
                        return Optional.of(Ingredient.of(waterRecipe.input().getItem()));
                    }
                    // Nếu không phải, trả về empty
                    return Optional.empty();
                })
                .build();
    }
}
