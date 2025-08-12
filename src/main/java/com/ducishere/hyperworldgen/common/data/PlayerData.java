package com.ducishere.hyperworldgen.common.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerData {
    private int cryoCoins;
    private int currentChapter;
    private int currentLevel;

    public PlayerData() {
        this.cryoCoins = 0;
        this.currentChapter = 1;
        this.currentLevel = 1;
    }

    // Getters
    public int getCryoCoins() { return cryoCoins; }
    public int getCurrentChapter() { return currentChapter; }
    public int getCurrentLevel() { return currentLevel; }

    // Setters
    public void addCryoCoins(int amount) { cryoCoins += amount; }
    public void setCurrentChapter(int chapter) { this.currentChapter = chapter; }
    public void setCurrentLevel(int level) { this.currentLevel = level; }

    // Save & Load từ NBT
    public NbtCompound writeNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putInt("CryoCoins", cryoCoins);
        nbt.putInt("CurrentChapter", currentChapter);
        nbt.putInt("CurrentLevel", currentLevel);
        return nbt;
    }

    public void readNbt(NbtCompound nbt) {
        this.cryoCoins = nbt.getInt("CryoCoins");
        this.currentChapter = nbt.getInt("CurrentChapter");
        this.currentLevel = nbt.getInt("CurrentLevel");
    }

    // Lưu vào player data
    public static void save(ServerPlayerEntity player, PlayerData data) {
        NbtCompound nbt = data.writeNbt();
        player.getPersistentData().put("HyperPlayerData", nbt);
    }

    // Load từ player data
    public static PlayerData load(ServerPlayerEntity player) {
        NbtCompound nbt = player.getPersistentData().getCompound("HyperPlayerData");
        PlayerData data = new PlayerData();
        data.readNbt(nbt);
        return data;
    }
}
