package toughasnails.api.temperature;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class TemperatureData implements ITemperature {
    private TemperatureLevel level = TemperatureLevel.NEUTRAL;
    private int extremityDelayTicks = 0;

    @Override
    public TemperatureLevel getLevel() { return level; }

    @Override
    public void setLevel(TemperatureLevel level) { this.level = level; }

    @Override
    public int getExtremityDelayTicks() { return extremityDelayTicks; }

    @Override
    public void setExtremityDelayTicks(int ticks) { extremityDelayTicks = ticks; }

    @Override
    public void readNbt(CompoundTag tag) {
        level = TemperatureLevel.valueOf(tag.getString("TempLevel"));
        extremityDelayTicks = tag.getInt("TempDelay");
    }

    @Override
    public void writeNbt(CompoundTag tag) {
        tag.putString("TempLevel", level.name());
        tag.putInt("TempDelay", extremityDelayTicks);
    }

    @Override
    public void tick() {
        if(extremityDelayTicks > 0) extremityDelayTicks--;
    }
}
