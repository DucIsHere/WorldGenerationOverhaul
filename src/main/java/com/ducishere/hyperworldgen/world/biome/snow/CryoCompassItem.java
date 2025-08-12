package com.hypergenworld.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.text.Text;
import net.minecraft.client.item.TooltipContext;

import java.util.List;

import org.jetbrains.annotations.Nullable;

public class CryoCompassItem extends Item {

    public CryoCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient || !(entity instanceof PlayerEntity player)) return;

        Biome biome = world.getBiome(player.getBlockPos()).value();
        float temp = biome.getTemperature();

        if (temp < -4.0f) {
            stack.setCustomName(Text.literal("🧭 Cryo Compass — Stable"));
            // Có thể thêm chỉ hướng về toạ độ nào đó nếu muốn
        } else {
            stack.setCustomName(Text.literal("🧭 Cryo Compass — Inactive"));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§bWorks only in freezing biomes (< -4.0°C)"));
        tooltip.add(Text.literal("§7Immune to magnetic interference"));
    }
}
