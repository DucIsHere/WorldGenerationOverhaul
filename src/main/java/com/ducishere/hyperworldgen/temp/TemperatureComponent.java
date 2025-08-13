package com.ducishere.hyperworldgen.temp;

import net.minecraft.nbt.NbtCompound;

public class TemperatureComponent {
    private int temperature = 50; // Min 0, Max 100

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = Math.max(0, Math.min(temperature, 100));
    }

    public void addTemperature(int amount) {
        setTemperature(this.temperature + amount);
    }

    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("Temperature", temperature);
    }

    public void readFromNbt(NbtCompound nbt) {
        temperature = nbt.getInt("Temperature");
    }
}
