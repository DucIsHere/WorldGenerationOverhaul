package com.ducishere.hyperworldgen.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CryoFurnaceBlockEntity extends BlockEntity {
    private int burnTime = 0;
    private final SimpleInventory inventory = new SimpleInventory(2); // slot 0: input, slot 1: output

    public CryoFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYO_FURNACE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CryoFurnaceBlockEntity blockEntity) {
        if (world.isClient) return;

        ItemStack input = blockEntity.inventory.getStack(0);
        if (!input.isEmpty()) {
            blockEntity.burnTime++;
            if (blockEntity.burnTime >= 200) { // 200 tick = 10s
                // TODO: replace với cryo furnace recipe system
                blockEntity.inventory.setStack(1, new ItemStack(ModItems.STEEL_ABYSS_SCRAP));
                input.decrement(1);
                blockEntity.burnTime = 0;
            }
        }
    }
}
