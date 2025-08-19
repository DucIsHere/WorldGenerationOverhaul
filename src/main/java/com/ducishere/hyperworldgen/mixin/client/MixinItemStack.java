package toughasnails.mixin.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toughasnails.api.item.TANItems;

import javax.annotation.Nullable;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {

    @Shadow public abstract Item getItem();

    @Inject(method="inventoryTick", at=@At("TAIL"))
    public void onTick(Level level, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
        if (!level.isClientSide()) return;

        Item item = this.getItem();

        // Check armor là Leaf Armor → set màu theo foliage biome
        if (item == TANItems.LEAF_BOOTS || item == TANItems.LEAF_LEGGINGS
                || item == TANItems.LEAF_CHESTPLATE || item == TANItems.LEAF_HELMET) {
            int foliageColor = BiomeColors.getAverageFoliageColor(level, entity.blockPosition());
            ((ItemStack)(Object)this).getOrCreateTag().putInt("CustomModelColor", foliageColor);
        }
    }
}
