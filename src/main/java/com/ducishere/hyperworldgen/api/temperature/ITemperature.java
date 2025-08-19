package toughasnails.api.temperature;

import net.minecraft.nbt.CompoundTag;

public interface ITemperature {
    TemperatureLevel getLevel();
    void setLevel(TemperatureLevel level);

    int getExtremityDelayTicks();
    void setExtremityDelayTicks(int ticks);

    void readNbt(CompoundTag tag);
    void writeNbt(CompoundTag tag);

    void tick();
}
