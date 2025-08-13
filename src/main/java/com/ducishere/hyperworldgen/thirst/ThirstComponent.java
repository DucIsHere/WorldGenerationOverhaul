package com.ducishere.hypergenworld.thirst;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ThirstCompnent {
  private int thirst = 20

  public int getThirst() {
    return thirst;
  }

  public void addThirst(int amount) {
    setThirst(this.thirst + amount);
  }

  public void tickDecrease() {
        if (thirst > 0) {
            thirst--;
        }
    }

    // Lưu vào NBT (để player thoát game vẫn nhớ)
    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("Thirst", thirst);
    }

    // Đọc từ NBT
    public void readFromNbt(NbtCompound nbt) {
        thirst = nbt.getInt("Thirst");
    }
  
}
