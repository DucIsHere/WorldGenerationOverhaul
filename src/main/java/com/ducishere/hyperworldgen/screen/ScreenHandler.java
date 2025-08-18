package com.ducishere.hyperworldgen.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class CryoFurnaceScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public CryoFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
        super(ModScreenHandlers.CRYO_FURNACE_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.propertyDelegate = delegate;

        // slot 0 = input, 1 = fuel, 2 = output
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new Slot(inventory, 1, 56, 53));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 2, 116, 35));

        // player inventory
        int m;
        int l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

        addProperties(delegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 2) {
                if (!this.insertItem(stack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(stack, itemstack);
            } else if (index != 1 && index != 0) {
                if (!this.insertItem(stack, 0, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(stack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return itemstack;
    }
}
